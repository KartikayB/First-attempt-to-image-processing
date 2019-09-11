
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kartikay Bansal
 */
public class Pixel {
    
    public void og(){
        String file1;
        file1 = "C:\\Users\\Kartikay Bansal\\Pictures\\Lab 4";
        Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
        PixelGrabber grabImage1Pixels = new PixelGrabber(image1, 1, 1, -1,
					-1, false);
        
        System.out.println(grabImage1Pixels.getColorModel());
    }
    
    public float compareImage() throws IOException {
        File fileA =new File("C:\\Users\\Kartikay Bansal\\Documents\\NetBeansProjects\\OpenCV\\New folder") ;
        File fileB =new File("C:\\Users\\Kartikay Bansal\\Documents\\NetBeansProjects\\OpenCV\\New folder (2)") ;
    float percentage = 0;
        // take buffer data from both image files //
        BufferedImage biA = null;
        for(File file: fileA.listFiles()){ 
        biA = ImageIO.read(file);}
        
        DataBuffer dbA;
        dbA = biA.getData().getDataBuffer();
        int sizeA = dbA.getSize();
        BufferedImage biB =null;
        for(File file1: fileB.listFiles()){
        biB= ImageIO.read(file1);}
        DataBuffer dbB = biB.getData().getDataBuffer();
        int sizeB = dbB.getSize();
        int count = 0;
        // compare data-buffer objects //
        System.out.println(sizeA+"  "+sizeB);
        if (sizeA == sizeB) {

            for (int i = 0; i < sizeA; i++){ 
                if (dbA.getElem(i) == dbB.getElem(i)) {
                    
                    count = count + 1;
                }
                
            }
            percentage = (count * 100) / sizeA;
        } 
    return percentage;
    }
    public static void main(String args[]) throws IOException{
        Pixel ob = new Pixel();
        System.out.println(ob.compareImage());
    }
}
