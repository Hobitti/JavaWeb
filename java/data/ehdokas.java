package data;

public class ehdokas {
	private int id;
	private String nimi;
	private int kunta;
	private String slogan;
	private int puolue;
	private String kuvaus;
	
	
	public ehdokas(int id, String nimi, int kunta, String slogan, int puolue, String kuvaus) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.kunta = kunta;
		this.slogan = slogan;
		this.puolue = puolue;
		this.kuvaus = kuvaus;
	}
	public ehdokas() {
	// todo	
	}
	public String getKuvaus() {
		return kuvaus;
	}
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public int getKunta() {
		return kunta;
	}
	public void setKunta(int kunta) {
		this.kunta = kunta;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	public int getPuolue() {
		return puolue;
	}
	public void setPuolue(int puolue) {
		this.puolue = puolue;
	}
	
	

}
