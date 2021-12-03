package com.edn.base.controller;

import com.edn.base.resizeImage.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("image-resize")
public class ImageResizeController {

    private final ImageService imageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAndResizeImage(@RequestParam(required = false, defaultValue = "false") Boolean resize,
                                                       @RequestParam("file") MultipartFile file) {
        System.out.println(resize);
        MultipartFile newFile = imageService.changeFormatAndSize(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

}
