package data;

public class Vastaus {
	
	private int vastausID;
	private int kysymysID;
	private int ehdokasID;
	private int mielipide;
	private String perustelu;
	
	public Vastaus() {
		
	}
	
	public Vastaus(int vastausID, int kysymysID, int ehdokasID, int mielipide, String perustelu) {
		vastausID = this.vastausID;
		kysymysID = this.kysymysID;
		ehdokasID = this.ehdokasID;
		mielipide = this.mielipide;
		perustelu = this.perustelu;
	}

	public int getVastausID() {
		return vastausID;
	}

	public void setVastausID(int vastausID) {
		this.vastausID = vastausID;
	}

	public int getKysymysID() {
		return kysymysID;
	}

	public void setKysymysID(int kysymysID) {
		this.kysymysID = kysymysID;
	}

	public int getEhdokasID() {
		return ehdokasID;
	}

	public void setEhdokasID(int ehdokasID) {
		this.ehdokasID = ehdokasID;
	}

	public int getMielipide() {
		return mielipide;
	}

	public void setMielipide(int mielipide) {
		this.mielipide = mielipide;
	}

	public String getPerustelu() {
		return perustelu;
	}

	public void setPerustelu(String perustelu) {
		this.perustelu = perustelu;
	}
	
	

}
