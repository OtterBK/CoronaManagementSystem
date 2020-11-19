package Addon;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MyUtility {

	public static void printLog(String logMsg) {
		System.out.println(logMsg);
	}
	
	public static ImageIcon resizeImage(ImageIcon baseIcon, int newWidth, int newHeight) {
		Image tmpImage = baseIcon.getImage();
		Image chgImage = tmpImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(chgImage);
		return chgIcon;
	}
	
}
