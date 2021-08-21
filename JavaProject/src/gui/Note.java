package gui;

public class Note {
	private String name;
	private String D_;
	private String T_;
	private String C_;
	private String K_;
	private String MIN;
	private String MAX;
	private String DEN;
	private String POS;
    public String getMIN() {
		return MIN;
	}

	public void setMIN(String mIN) {
		MIN = mIN;
	}

	public String getMAX() {
		return MAX;
	}

	public void setMAX(String mAX) {
		MAX = mAX;
	}

	public String getDEN() {
		return DEN;
	}

	public void setDEN(String dEN) {
		DEN = dEN;
	}

	public String getPOS() {
		return POS;
	}

	public void setPOS(String pOS) {
		POS = pOS;
	}

	public String getD_() {
		return D_;
	}

	public void setD_(String d_) {
		this.D_ = d_;
	}

	public String getT_() {
		return T_;
	}

	public void setT_(String t_) {
		this.T_ = t_;
	}

	public String getC_() {
		return C_;
	}

	public void setC_(String c_) {
		this.C_ = c_;
	}

	public String getK_() {
		return K_;
	}

	public void setK_(String k_) {
		this.K_ = k_;
	}

	private String text;

    public Note() {
        name = "Новый документ";
        D_ = "Путь к датасету";
        T_ = "Путь к шаблону";
        C_ = "Объем датасета";
        K_ = "Ключевое слово";
        MAX = "Здесь будет максимальный процент схожести";
        MIN = "Здесь будет минимальный процент схожести";
        DEN = "Здесь будет плотность распределения процентов схожести";
        POS = "Здесь будет нижняя граница доверительного интервала";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return name;
    }
}