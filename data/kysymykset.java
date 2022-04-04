package data;

public class kysymykset {
	private int id;
	private String kysymys;
	private String axis;
	public kysymykset(int id, String kysymys, String axis) {
		super();
		this.id = id;
		this.kysymys = kysymys;
		this.axis = axis;
	}
	public kysymykset() {
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
	public String getAxis() {
		return axis;
	}
	public void setAxis(String axis) {
		this.axis = axis;
	}
}
