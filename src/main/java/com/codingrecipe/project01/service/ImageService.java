package com.codingrecipe.project01.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ImageService {

    public URL convertImagePathToFileURL(String imagePath) throws MalformedURLException {
        File file = new File(imagePath);
        return file.toURI().toURL();
    }
}
