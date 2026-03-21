package com.visionforge.infrastructure.processing;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageProcessingService {

    public String applyGrayscaleFilter(String originalImagePath) {
        try {
            File inputFile = new File(originalImagePath);
            BufferedImage originalImage = ImageIO.read(inputFile);

            if (originalImage == null) {
                throw new RuntimeException("Could not read image: " + originalImagePath);
            }

            BufferedImage processedImage = new BufferedImage(
                    originalImage.getWidth(),
                    originalImage.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY);

            Graphics2D graphics = processedImage.createGraphics();
            graphics.drawImage(originalImage, 0, 0, null);
            graphics.dispose();

            Path originalPath = Paths.get(originalImagePath);
            String processedFileName = "processed_" + originalPath.getFileName().toString();
            Path processedPath = originalPath.getParent().resolve(processedFileName);

            File outputFile = processedPath.toFile();
            ImageIO.write(processedImage, "jpg", outputFile);

            return processedPath.toString();

        } catch (IOException e) {
            throw new RuntimeException("Error processing image", e);
        }
    }
}
