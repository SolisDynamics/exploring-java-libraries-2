package a7_java.awt;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

/* Educational example demonstrating BufferedImageOp interface methods  */
public class b52_BufferedImageOpExample {

    public static void main(String[] args) {
        try {
            // Load source image  
            File inputFile = new File("C:\\Users\\Pc\\OneDrive\\Masaüstü\\Forest.jpeg");
            BufferedImage sourceImage = ImageIO.read(inputFile);
            System.out.println("Source image loaded successfully!");

            // DEMO 1: Image Filtering with ConvolveOp (Method 2: filter)  
            // Creating a blur effect using 3x3 kernel  
            float[] blurKernel = {
                1/9f, 1/9f, 1/9f,
                1/9f, 1/9f, 1/9f,
                1/9f, 1/9f, 1/9f
            };
            BufferedImageOp blurOperation = new ConvolveOp(new Kernel(3, 3, blurKernel));
            BufferedImage blurredImage = blurOperation.filter(sourceImage, null);
            System.out.println("Method 2 (filter): Blur effect applied successfully");

            // DEMO 2: Creating Compatible Destination Image (Method 1: createCompatibleDestImage)  
            // This method ensures the destination image is compatible with the source  
            ColorModel cm = sourceImage.getColorModel();
            BufferedImage compatibleImage = blurOperation.createCompatibleDestImage(sourceImage, cm);
            System.out.println("Method 1 (createCompatibleDestImage): Compatible image created");

            // DEMO 3: Getting Bounding Box (Method 3: getBounds2D)  
            // Useful for determining the dimensions of the processed image  
            Rectangle2D bounds = blurOperation.getBounds2D(sourceImage);
            System.out.println("Method 3 (getBounds2D): Image bounds - " +
                    "Width: " + bounds.getWidth() +
                    ", Height: " + bounds.getHeight());

            // DEMO 4: Point Transformation (Method 4: getPoint2D)  
            // Demonstrates how points are mapped between source and destination  
            Point2D sourcePoint = new Point2D.Float(100, 100);
            Point2D destinationPoint = blurOperation.getPoint2D(sourcePoint, null);
            System.out.println("Method 4 (getPoint2D): Point mapping - " +
                    "Source: " + sourcePoint +
                    " -> Destination: " + destinationPoint);

            // DEMO 5: Rendering Hints (Method 5: getRenderingHints)  
            // Get rendering hints used by the operation  
            RenderingHints hints = blurOperation.getRenderingHints();
            System.out.println("Method 5 (getRenderingHints): " +
                    (hints != null ? "Hints available" : "No specific hints"));

            // Save processed images  
            ImageIO.write(blurredImage, "jpg", new File("blurred_output.jpg"));
            ImageIO.write(compatibleImage, "jpg", new File("compatible_output.jpg"));
            System.out.println("Processing completed! Check output files.");

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
