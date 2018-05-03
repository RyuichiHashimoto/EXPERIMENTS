package Util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.ImageProducer;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageIconJar extends ImageIcon {


	public ImageIconJar(String f, Window own) {
		super();
		Image image;
		try {
			Toolkit tk = own.getToolkit();
			URL url = own.getClass().getResource(f);
			image = tk.createImage((ImageProducer) url.getContent());
			setImage(image);
		} catch (Exception e) {
			System.out.println("Image not Found!");
		}
	}
}