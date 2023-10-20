package com.duan.demo01.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


public class ImageUtil {

    public static String uploadFile(InputStream inputStream, String storage) {
        Path storageFolder = Paths.get(storage);
        if (!Files.exists(storageFolder)) {
            try {
                Files.createDirectories(storageFolder);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create storage directory", e);
            }
        }
        String generatedFilename = UUID.randomUUID().toString().replace("-", "");
        generatedFilename = generatedFilename + "." + "png";
        Path destinationFilePath = storageFolder.resolve(Paths.get(generatedFilename)).normalize().toAbsolutePath();

        if (!destinationFilePath.getParent().equals(storageFolder.toAbsolutePath())) {
            throw new RuntimeException("Cannot store file outside current directory");
        }
        try {
            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            return generatedFilename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}