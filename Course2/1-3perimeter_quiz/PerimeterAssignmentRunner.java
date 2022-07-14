import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.00;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point Pt : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalPerim = getPerimeter(s);
        double numPoints = getNumPoints(s);
        return totalPerim / numPoints;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double side = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > side) {
                side = currDist;
            }
        }
        return side;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point xPt = s.getLastPoint();
        double x = xPt.getX();
        for (Point Pt : s.getPoints()) {
            if (Pt.getX() > x) {
                x = Pt.getX();
            }
        }
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largePerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > largePerimeter) {
                largePerimeter = getPerimeter(s);
            }
        }
        return largePerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largePerimeter = getLargestPerimeterMultipleFiles();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) == largePerimeter) {
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int num = getNumPoints(s);
        double average = getAverageLength(s);
        double side = getLargestSide(s);
        double x = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points is " + num);
        System.out.println("average length = " + average);
        System.out.println("longest side = " + side);
        System.out.println("largest x = " + x);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largePerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largePerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String name = getFileWithLargestPerimeter();
        System.out.println(name + " has the largest perimeter");
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
