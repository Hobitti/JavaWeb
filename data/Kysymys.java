package data;

public class Kysymys {
	
	private int id;
	private String kysymys;
	private String selite;
	private String axis;
	
	public Kysymys(int id, String kysymys, String selite/*, String axis*/) {
		super();
		this.id = id;
		this.kysymys = kysymys;
		this.selite = selite;
		/*this.axis = axis;*/
	}
	
	public Kysymys() {
		//todo
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getKysymys() {
		return kysymys;
	}
	
	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}
	
	public String getSelite() {
		return selite;
	}
	
	public void setSelite(String selite) {
		this.selite = selite;
	}
	
	public String getAxis() {
		return axis;
	}
	
	public void setAxis(String axis) {
		this.axis = axis;
	}
}
