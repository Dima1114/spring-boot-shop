package com.freeride.shop.service.impl;

import com.freeride.shop.service.SliderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SliderServiceImpl implements SliderService {

    private static final int DEFAULT_SLIDE = 0;

    private String imagePath;

    public SliderServiceImpl(@Value("${images.slide.path}") String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public byte[] getSlide(int slideNumber) {
        byte[] img = null;
        Path path = Paths.get(imagePath, "slide" + slideNumber + ".jpg");
        try {
            if (Files.exists(path)) {
                img = Files.readAllBytes(path);
            } else {
                img = Files.readAllBytes(Paths.get(imagePath, "slide" + 0 + ".jpg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
