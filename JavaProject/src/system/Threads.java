package system;
public class Threads implements Runnable {

	public Thread thread;
	public Docs doc;

	public static boolean endAnalysis = false;
	public int actualMax;
	public int actualMin;
	public int possibleMin;
	public double density;

	public static void setEnd(boolean end) {
		endAnalysis = end;
	}

	public Threads(Docs doc) {
		this.doc = doc;
		this.thread = new Thread();
	}

	@Override
	public void run() {
		MinimalPercentDefinition M_ = new MinimalPercentDefinition(doc);
		M_.init(false);
		//M_.getNameBooleans();
		this.actualMax = M_.actualMax;
		this.actualMin = M_.actualMin;
		this.density = M_.density;
		this.possibleMin = M_.possibleMin;
		endAnalysis = true;
	}

	public void initThread() {
		this.thread.start();
	}

}