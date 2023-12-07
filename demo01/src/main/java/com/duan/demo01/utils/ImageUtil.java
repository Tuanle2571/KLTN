package com.duan.demo01.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


public class ImageUtil {

    public static String uploadFile(InputStream inputStream, String storage) {
        URL resourceUrl = ImageUtil.class.getResource("/images");
        if (resourceUrl != null) {
            try {
                Path storagePath = Paths.get(resourceUrl.toURI()).resolve(storage);
                if (!Files.exists(storagePath)) {
                    try {
                        Files.createDirectories(storagePath);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to create storage directory", e);
                    }
                }

                String generatedFilename = UUID.randomUUID().toString().replace("-", "") + ".png";
                Path destinationFilePath = storagePath.resolve(generatedFilename).normalize().toAbsolutePath();

                if (!destinationFilePath.getParent().equals(storagePath.toAbsolutePath())) {
                    throw new RuntimeException("Cannot store file outside current directory");
                }

                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                return generatedFilename;
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Resource '/images' not found.");
        }
    }

    public static byte[] getImage(String filename, String storage){
        URL resourceUrl = ImageUtil.class.getResource("/images");
        if (resourceUrl != null) {
            try {
                Path storageFolder = Paths.get(resourceUrl.toURI()).resolve(storage);
                Path file = storageFolder.resolve(filename);
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                    return bytes;
                } else {
                    throw new RuntimeException("Could not read file: " + filename);
                }
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException("Could not read file: " + filename, e);
            }
        }
        return null;
    }

    public static boolean deleteFile(String filename, String storage){
        URL resourceUrl = ImageUtil.class.getResource("/images");
        try {
            Path storageFolder = Paths.get(resourceUrl.toURI()).resolve(storage);
            Path file = storageFolder.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public static byte[] generateQR(String url){
        byte[] QR = null;
        return QR;
    }



}