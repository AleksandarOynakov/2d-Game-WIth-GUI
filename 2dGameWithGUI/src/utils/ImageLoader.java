package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    private ImageLoader(){}

    public static BufferedImage load(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
        }catch (IOException e){
            throw new RuntimeException("Cannot load image: " + path);
        }
    }
}
