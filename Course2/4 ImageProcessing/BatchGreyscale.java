
/**
 * Write a description of BatchGreyscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class BatchGreyscale {
    public ImageResource makeGrey(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return  outImage;
    }
    
    public void selectAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String inName = f.getName();
            String greyName = "grey-" + inName;
            ImageResource inImage = new ImageResource(f);
            ImageResource greyImage = makeGrey(inImage);
            greyImage.setFileName(greyName);
            greyImage.draw();
            greyImage.save();
        }
    }
            
}
