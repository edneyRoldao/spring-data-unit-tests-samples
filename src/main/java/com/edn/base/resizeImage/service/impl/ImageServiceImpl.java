package com.edn.base.resizeImage.service.impl;

import com.edn.base.resizeImage.exception.InvalidImageFormatException;
import com.edn.base.resizeImage.exception.InvalidImageSizeException;
import com.edn.base.resizeImage.service.ImageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static java.awt.image.BufferedImage.TYPE_INT_BGR;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${app.image-resize.width}")
    private int defaultWidth;

    @Value("${app.image-resize.height}")
    private int defaultHeight;

    @Value("${app.image-resize.min-width}")
    private int minWidth;

    @Value("${app.image-resize.min-height}")
    private int minHeight;

    @Value("${app.image-resize.default-format}")
    private String defaultFormat;

    @Value("${app.image-resize.width-proportion}")
    private int minWidthProportion;


    @Override
    @SneakyThrows
    public MultipartFile changeFormatAndSize(MultipartFile multipartFile) {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

        if (Objects.isNull(bufferedImage)) {
            throw new InvalidImageFormatException();
        }

        imageSizeValidator(bufferedImage);
        File file = convertAndResizeImage(multipartFile, bufferedImage);
        MultipartFile newMultipartFile = fileToMultipartFile(multipartFile, file);

        Files.delete(Paths.get(file.getName()));

        return newMultipartFile;
    }

    @SneakyThrows
    private void imageSizeValidator(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int widthProportion = getWidthProportionally(width, height);

        if (width < minWidth || height < minHeight) {
            String error = String.format("Min width allowed: %s and min height allowed: %s", minWidth, minHeight);
            throw new InvalidImageSizeException(error);
        }

        if (widthProportion < minWidthProportion) {
            BigDecimal divisor = new BigDecimal(100);
            BigDecimal imageSize = new BigDecimal(minWidthProportion);
            BigDecimal percentage = imageSize.divide(divisor, 2, RoundingMode.CEILING);
            String error = String.format("Image proportion is not allowed. Width should be greater than height at least: %s percent", percentage);
            throw new InvalidImageSizeException(error);
        }
    }

    @SneakyThrows
    private File convertAndResizeImage(MultipartFile multipartFile, BufferedImage originalImage) {
        String filename = String.format("%s.%s" , getImageNameWithoutExtension(multipartFile), defaultFormat);
        File fileTarget = new File(filename);

        BufferedImage newImage = new BufferedImage(defaultWidth, defaultHeight, TYPE_INT_BGR);
        Graphics2D imageGraphics = newImage.createGraphics();
        imageGraphics.drawImage(originalImage, 0, 0, defaultWidth, defaultHeight, null);
        imageGraphics.dispose();

        ImageIO.write(newImage, defaultFormat, fileTarget);

        return fileTarget;
    }

    @SneakyThrows
    private MultipartFile fileToMultipartFile(MultipartFile oldMultipartFile, File file) {
        String name = file.getName();
        String originalFileName = file.getName();
        String contentType = String.format("image/%s", defaultFormat);
        byte[] content = Files.readAllBytes(file.toPath());
        return new MockMultipartFile(name, originalFileName, contentType, content);
    }

    private String getImageNameWithoutExtension(MultipartFile file) {
        String filename = Objects.nonNull(file.getOriginalFilename()) ? file.getOriginalFilename() : file.getName();

        try {
            String[] token = filename.split("\\.");
            return filename.split("\\." + token[token.length -1])[0];

        } catch (ArrayIndexOutOfBoundsException e) {
            return filename;
        }
    }

    private int getWidthProportionally(int width, int height) {
        BigDecimal dividend = new BigDecimal(width * 100);
        BigDecimal divisor = new BigDecimal(width + height);
        BigDecimal result = dividend.divide(divisor, 2, RoundingMode.CEILING);
        return result.multiply(new BigDecimal(100)).intValue();
    }

}
