package com.visionforge.infrastructure.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileStorageService {

    private final Path uploadDirectory = Paths.get("uploads");

    public LocalFileStorageService() {
        try {
            Files.createDirectories(uploadDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar a pasta de uploads", e);
        }
    }

    public String saveImage(MultipartFile file, String fileName) {
        try {
            Path targetLocation = this.uploadDirectory.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao guardar o ficheiro " + fileName, e);
        }
    }
}