package system;
import java.util.ArrayList;

public class MinimalPercentDefinition {

	private String pathDocs;
	private String pathTemp;

	public int sampleAmount;
	public int actualMax;
	public int actualMin;
	public int possibleMin;
	public double density;
	public String keyWord;
	public ArrayList<Boolean> names = new ArrayList<>();

	public MinimalPercentDefinition(Docs doc) {
		this.pathDocs = doc.getD_();
		this.pathTemp = doc.getT_();
		this.sampleAmount = doc.getC_();
		this.keyWord = doc.getK_();
	}

	public void containsName(String doc, String keyWord) {
		this.names.add(doc.toLowerCase().contains(keyWord));
	}

	public void init(boolean showInf) {
		ArrayList<Integer> percentResults = new ArrayList<>();

		ReadTemplate readTemplate = new DoTesseractProxy();
		DoTesseractProxy tesseractProxy = new DoTesseractProxy();

		String template = readTemplate.readTemp(pathTemp);

		for(int i=1; i<sampleAmount+1; i++) {
			//String doc = tesseractProxy.readRescaledDoc(path+i+"."+Formats.jpg);
			String doc = tesseractProxy.readDoc(pathDocs+i+"."+Formats.jpg);
			//System.out.println(doc);
			containsName(doc, this.keyWord);
			FuzzyMatchClass fuzzyWuzzy = new FuzzyMatchClass();
			percentResults.add(fuzzyWuzzy.getRatio(template,doc,showInf));
		}

		int sum = 0;
		float min = 100f;
		float max = 0f;

		for(int i=0; i<sampleAmount; i++) {
			int percent = percentResults.get(i);
			min = percent<min ? percent : min;
			max = percent>max ? percent : max;
			System.out.print(percent + " ");
			sum += percent;
		}

		double density = (max-min)/sampleAmount;
		double possibleDeviationFromMinValue = max-density*(sampleAmount+1);
		int roundedDeviation = (int) Math.ceil(possibleDeviationFromMinValue);

		System.out.println("\nSAMPLE AMOUNT: "+sampleAmount+"\nMIN: "+min+"\nAVERAGE: "+sum/sampleAmount+
							"\nMAX: "+max+"\nDENSITY: "+density+"\nPOSSIBLE MIN: "+roundedDeviation);

		this.actualMin = (int) min;
		this.actualMax = (int) max;
		this.possibleMin = roundedDeviation;
		this.density = density;

	}

	public void setDensity(double density) {
		this.density = density;
	}

	public void getNameBooleans() {
		for(int i=0; i<this.names.size(); i++)
			System.out.println((i+1)+ ". " + this.names.get(i));
	}
}