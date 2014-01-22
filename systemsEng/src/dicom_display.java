import com.pixelmed.dicom.DicomException;
import com.pixelmed.display.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class dicom_display {
 /**
  * @param args
  * @throws DicomException 
  * @throws IOException 
  */
 static JFileChooser fileChooser = new JFileChooser();
 public static void main(String[] args) throws IOException, DicomException {

   JFrame p = new JFrame();
   
   JMenuBar menuBar = new JMenuBar();
   JMenu file = new JMenu("File");
   JMenuItem save = new JMenuItem("Save");
   save.addActionListener(new ActionListener() {

 @Override
 public void actionPerformed(ActionEvent arg0) {
  
  JFileChooser fileChooser1 = new JFileChooser();
  int returnVal = fileChooser1.showSaveDialog(null);
  if (returnVal == JFileChooser.APPROVE_OPTION) {
   saveImage(fileChooser1.getSelectedFile().getAbsolutePath()+ "");
  } else {
   // user cancels, do nothing. 
  }
  
 }
    
   });
   menuBar.add(file);
   file.add(save);
   p.setJMenuBar(menuBar);
   
   
   
   // allow user to select location
    fileChooser.setFileFilter(new FileNameExtensionFilter("DICOM Files", "dcm"));
 int returnVal = fileChooser.showOpenDialog(null);
 if (returnVal == JFileChooser.APPROVE_OPTION) {
    p.add(new SingleImagePanel(new SourceImage(fileChooser.getSelectedFile().getAbsolutePath())));
 } else {
  // nothing 
 }
   
   p.setBackground(Color.BLACK);
   p.setSize(512,512);
   p.setVisible(true);
   
  try {
   Thread.sleep(1000);
  } catch (InterruptedException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }
 }
  

 public static void saveImage(String directory) {
  
  try {
             
             Robot display = new Robot();
             // Capture the screen shot of the area of the screen defined by the rectangle
             BufferedImage bi = display.createScreenCapture(new Rectangle(500,500));
             ImageIO.write(bi, "jpg", new File(directory + ".jpg"));
             
             
         } catch (AWTException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
  
 }