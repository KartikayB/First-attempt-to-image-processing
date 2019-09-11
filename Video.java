/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kartikay Bansal
 */
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs; // imread, imwrite, etc
import org.opencv.videoio.VideoCapture;   // 
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

        
public class Video {
    private Object canny;
    
    public void capture(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	VideoCapture camera = new VideoCapture(0);
    	
    	if(!camera.isOpened()){
    		System.out.println("Error");
    	}
    	else {
    		Mat frame = new Mat();
    	    while(true){
    	    	if (camera.read(frame)){
    	    		System.out.println("Frame Obtained");
    	    		System.out.println("Captured Frame Width " + 
    	    		frame.width() + " Height " + frame.height());
    	    		Imgcodecs.imwrite("camera.jpg", frame);
    	    		System.out.println("OK");
    	    		break;
    	    	}
    	    }	
    	}
    	camera.release();
        
    }
    
    static void processImage() {

		String file1;
        file1 = "C:\\Users\\Kartikay Bansal\\Documents\\NetBeansProjects\\OpenCV";
		String file2;
        file2 = "C:\\Users\\Kartikay Bansal\\Documents\\NetBeansProjects\\OpenCV";

		// Load the images
		Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
		Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

		try {

			PixelGrabber grabImage1Pixels = new PixelGrabber(image1, 0, 0, -1,
					-1, false);
			PixelGrabber grabImage2Pixels = new PixelGrabber(image2, 0, 0, -1,
					-1, false);

			int[] image1Data = null;

			if (grabImage1Pixels.grabPixels()) {
				int width = grabImage1Pixels.getWidth();
				int height = grabImage1Pixels.getHeight();
				image1Data = new int[width * height];
				image1Data = (int[]) grabImage1Pixels.getPixels();
			}

			int[] image2Data = null;

			if (grabImage2Pixels.grabPixels()) {
				int width = grabImage2Pixels.getWidth();
				int height = grabImage2Pixels.getHeight();
				image2Data = new int[width * height];
				image2Data = (int[]) grabImage2Pixels.getPixels();
			}

			System.out.println("Pixels equal: "
					+ java.util.Arrays.equals(image1Data, image2Data));

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

    
    public static void main (String args[]){
        Video obj = new Video();
        obj.capture();
       
    }
}   