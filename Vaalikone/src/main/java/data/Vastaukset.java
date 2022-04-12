package data;

public class Vastaukset {
	private int id;
	private int ehdokasId;
	private int kysymysId;
	private int vastasi;
	private String perustelu;
	
	public Vastaukset (int id, int ehdokasId, int kysymysId, int vastasi, String perustelu /*, String axis*/) {
		super();
		this.id = id;
		this.ehdokasId = ehdokasId;
		this.kysymysId = kysymysId;
		this.vastasi = vastasi;
		this.perustelu = perustelu;
		/*this.axis = axis;*/
	}
	
	public Vastaukset() {
		//todo
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEhdokasId() {
		return ehdokasId;
	}
	public void setEhdokasId(int ehdokasId) {
		this.ehdokasId = ehdokasId;
	}
	public int getKysymysId() {
		return kysymysId;
	}
	public void setKysymysId(int kysymysId) {
		this.kysymysId = kysymysId;
	}
	public int getVastasi() {
		return vastasi;
	}
	public void setVastasi(int vastasi) {
		this.vastasi = vastasi;
	}
	public String getPerustelu() {
		return perustelu;
	}
	public void setPerustelu(String perustelu) {
		this.perustelu = perustelu;
	}

	public void setMielipide(int int1) {
		// TODO Auto-generated method stub
		
	}
}
