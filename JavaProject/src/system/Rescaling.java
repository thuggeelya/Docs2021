package system;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;

public class Rescaling {

	public BufferedImage processImg(BufferedImage ipimage, float scaleFactor, float offset) throws IOException {
		//String path_output = "D:/Tess4J/test/output.png";
		// Making an empty image buffer
		// to store image later
		// ipimage is an image buffer
		// of input image
		BufferedImage opimage
			= new BufferedImage(1050,
								1024,
								ipimage.getType());

		// creating a 2D platform 
		// on the buffer image 
		// for drawing the new image 
		Graphics2D graphic 
			= opimage.createGraphics(); 

		// drawing new image starting from 0 0 
		// of size 1050 x 1024 (zoomed images) 
		// null is the ImageObserver class object 
		graphic.drawImage(ipimage, 0, 0, 
						1050, 1024, null); 
		graphic.dispose(); 

		// rescale OP object 
		// for gray scaling images 
		RescaleOp rescale
			= new RescaleOp(scaleFactor, offset, null); 

		// performing scaling 
		// and writing on a .png file 
		BufferedImage fopimage 
			= rescale.filter(opimage, null); 
		//ImageIO 
		//	.write(fopimage, 
		//		"jpg", 
		//		new File(path_output)); 
		return fopimage;
	}
}