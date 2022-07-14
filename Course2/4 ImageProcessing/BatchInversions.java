
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int outRed = 255 - inPixel.getRed();
            int outGreen = 255 - inPixel.getGreen();
            int outBlue = 255 -inPixel.getBlue();
            pixel.setRed(outRed);
            pixel.setGreen(outGreen);
            pixel.setBlue(outBlue);
        }
        return outImage;
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String inName = f.getName();
            String outName = "inverted-" + inName;
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInversion(inImage);
            outImage.draw();
            outImage.setFileName(outName);
            outImage.save();
        }
    }
            
}
