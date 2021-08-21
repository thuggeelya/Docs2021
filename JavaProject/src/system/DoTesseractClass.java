package system;
import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class DoTesseractClass implements ReadTemplate {

	private String data_path = "D:/Tess4J/tessdata";

	@Override
	public String readTemp(String path) {
		Tesseract tesseract = new Tesseract();
        String language = "rus";
        tesseract.setLanguage(language);

        try {
            tesseract.setDatapath(data_path);
            String spravka_orig = tesseract.doOCR(new File(path));
            return spravka_orig;
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }
		return "-1";
	}

}