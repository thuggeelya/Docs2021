package system;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class DoTesseractProxy implements ReadTemplate {

	private String data_path = "D:/Tess4J/tessdata";
	private ReadTemplate readTemplate = new DoTesseractClass();
	public String cashTemp = null;

	@Override
	public String readTemp(String path) {
		if (cashTemp == null) {
			cashTemp = readTemplate.readTemp(path);
		}
		return cashTemp;
	}

	public BufferedImage compareAndScale(double d, BufferedImage ipimage) throws IOException {
		Rescaling scale = new Rescaling();
		if (d >= -1.4211511E7 && d < -7254228) {
			return scale.processImg(ipimage, 3f, -10f);
		}
		else if (d >= -7254228 && d < -2171170) {
			return scale.processImg(ipimage, 1.455f, -47f);
		}
		else if (d >= -2171170 && d < -1907998) {
			return scale.processImg(ipimage, 1.35f, -10f);
		}
		else if (d >= -1907998 && d < -257) {
			return scale.processImg(ipimage, 1.19f, 0.5f);
		}
		else if (d >= -257 && d < -1) {
			return scale.processImg(ipimage, 1f, 0.5f);
		}
		else if (d >= -1 && d < 2) {
			return scale.processImg(ipimage, 1f, 0.35f);
		}
		return null;
	}

	public String readDoc(String path) {
		Tesseract tesseract = new Tesseract();
        String language = "rus";
        tesseract.setLanguage(language);

        try {
            tesseract.setDatapath(data_path);
            String spravka_orig = tesseract.doOCR(new File(path));
            System.out.println("Opening " + path);
            return spravka_orig;
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }
		return "-1";
		
	}

	public String readRescaledDoc(String path) {
		Tesseract tesseract = new Tesseract();
        String language = "rus";
        tesseract.setLanguage(language);

        try {
			BufferedImage ipimage = ImageIO.read(new File(path));
			// getting RGB content of the whole image file
			double d = ipimage.getRGB(ipimage.getTileWidth() / 2, 
									  ipimage.getTileHeight() / 2);
			BufferedImage resultImage = compareAndScale(d, ipimage);

			tesseract.setDatapath(data_path);
            String spravka_orig = tesseract.doOCR(resultImage);
            System.out.println("Opening " + path);
            return spravka_orig;
		} catch (IOException | TesseractException e) {
			e.printStackTrace();
		}

		return "-1";
		
	}

	public void clear() {
		cashTemp = null;
	}
}