package com.edn.base.controller;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("processar")
public class TestUploadController {

    private final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private final String FILENAME = "relatorio-repasse.xlsx";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void conciliarRepasses(@RequestParam MultipartFile file, HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        workbook.write(output);

        attachFileToResponse(response, output.toByteArray(), FILENAME, CONTENT_TYPE);
    }

    public static void attachFileToResponse(HttpServletResponse response, byte[] excelFile, String filename, String contentType) throws IOException {
        response.setContentType(contentType);
        response.setHeader("Content-disposition", String.format("attachment; filename=%s", filename));
        ServletOutputStream output = response.getOutputStream();
        output.write(excelFile);
    }

}

