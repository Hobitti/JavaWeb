package dao;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;

import data.*;

public class Dao {
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	public Dao(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	           try {
	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	                throw new SQLException(e);
	            }
	           
	            conn = DriverManager.getConnection(url, user, pass);
	            
	        }
	        return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	//Ehdokas tablen toiminnot alkavat t�st�
	public ArrayList<Ehdokas> readAllEhdokas() {
		ArrayList<Ehdokas> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from ehdokas");
			while (RS.next()){
				Ehdokas next = new Ehdokas();
				next.setId(RS.getInt("EhdokasID"));
				next.setNimi(RS.getString("Nimi"));
				next.setKunta(RS.getInt("KuntaID"));
				next.setSlogan(RS.getString("Slogan"));
				next.setPuolue(RS.getInt("PuolueID"));
				list.add(next);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
		public Ehdokas readEhdokas(String id) {
			Ehdokas ehdokas=null;
			try {
				String sql="SELECT EhdokasID, ehdokas.Nimi, ehdokas.KuntaID, kunta.Nimi AS KunnanNimi, Slogan, ehdokas.PuolueID, puolue.Nimi AS PuolueenNimi, ehdokas.Kuvaus FROM ehdokas INNER JOIN kunta ON ehdokas.KuntaID = kunta.KuntaID INNER JOIN puolue ON ehdokas.PuolueID = puolue.PuolueID WHERE EhdokasID = ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet RS=pstmt.executeQuery();
				while (RS.next()){
					ehdokas = new Ehdokas();
					ehdokas.setId(RS.getInt("EhdokasID"));
					ehdokas.setNimi(RS.getString("Nimi"));
					ehdokas.setKunta(RS.getInt("KuntaID"));
					ehdokas.setKuntaS(RS.getString("KunnanNimi"));
					ehdokas.setSlogan(RS.getString("Slogan"));
					ehdokas.setPuolue(RS.getInt("PuolueID"));
					ehdokas.setPuolueS(RS.getString("PuolueenNimi"));
					ehdokas.setKuvaus(RS.getString("Kuvaus"));
				}
				return ehdokas;
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		public ArrayList<Ehdokas> updateEhdokas(Ehdokas eh) {
			try {
				String sql="update ehdokas set Nimi=?, KuntaID=?, Slogan=?, PuolueID=? where id=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, eh.getNimi());
				pstmt.setInt(2, eh.getKunta());
				pstmt.setString(3, eh.getSlogan());
				pstmt.setInt(4, eh.getPuolue());
				pstmt.setInt(5, eh.getId());
				pstmt.executeUpdate();
				return readAllEhdokas();
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		public ArrayList<Ehdokas> deleteEhdokas(String id) {
			try {
				String sql="delete from ehdokas where EhdokasID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				return readAllEhdokas();
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		//Kysymykset Tablen toiminnota alkavat t�st�
		public ArrayList<Kysymys> readAllKysymykset() {
			ArrayList<Kysymys> list=new ArrayList<>();
			try {
				Statement stmt = conn.createStatement();
				ResultSet RS = stmt.executeQuery("select * from kysymykset");
				while (RS.next()){
					Kysymys next = new Kysymys();
					next.setId(RS.getInt("kysymysID"));
					next.setKysymys(RS.getString("Kysymys"));
					next.setAxis(RS.getString("KysymysAkseli"));
					list.add(next);
				}
				return list;
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		@SuppressWarnings("null")
		public Kysymys readKysymys(String id) {
			Kysymys kysymykset = new Kysymys();
			try {
				String sql="select * from kysymykset where kysymysID = ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet RS=pstmt.executeQuery();
				while (RS.next()){
					kysymykset.setId(RS.getInt("kysymysID"));
					kysymykset.setKysymys(RS.getString("Kysymys"));
					//kysymykset.setSelite(RS.getString("Selite"));
				}
				return kysymykset;
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		public ArrayList<Kysymys> updateEhdokas(Kysymys k) {
			try {
				String sql="update kysymykset set Kysymys=?, KysymysAkseli=? where id=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, k.getKysymys());
				pstmt.setString(2, k.getAxis());
				pstmt.setInt(3, k.getId());
				
				pstmt.executeUpdate();
				return readAllKysymykset();
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		public ArrayList<Kysymys> deleteKysymys(String id) {
			try {
				String sql="delete from kysymykset where KysymysID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				return readAllKysymykset();
			}
			catch(SQLException e) {
				return null;
			}
		}
		

		// Vastaukset
		public ArrayList<Vastaukset> readAllVastaukset() {
			ArrayList<Vastaukset> list=new ArrayList<>();
			try {
				Statement stmt = conn.createStatement();
				ResultSet RS = stmt.executeQuery("select * from Vastaukset");
				while (RS.next()){
					Vastaukset next = new Vastaukset();
					next.setId(RS.getInt("VastausID"));
					next.setKysymysId(RS.getInt("KysymysID"));
					next.setEhdokasId(RS.getInt("EhdokasID"));
					next.setVastasi(RS.getInt("Vastasi"));
					next.setPerustelu(RS.getString("Perustelu"));
					list.add(next);
				}
				return list;
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		public ArrayList<Vastaukset> readEhdokasVastaukset(String id) {
			ArrayList<Vastaukset> list=new ArrayList<>();
			try {
				String sql="select * from Vastaukset where EhdokasID = ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet RS=pstmt.executeQuery();
				while (RS.next()){
					Vastaukset next = new Vastaukset();
					next.setId(RS.getInt("VastausID"));
					next.setKysymysId(RS.getInt("KysymysID"));
					next.setEhdokasId(RS.getInt("EhdokasID"));
					next.setVastasi(RS.getInt("Vastasi"));
					next.setPerustelu(RS.getString("Perustelu"));
					list.add(next);
				}
				return list;
			}
			catch(SQLException e) {
				return null;
			}
		}

		public ArrayList<Vastaukset> deleteVastaus(String id) {
			try {
				String sql="delete from vastaukset where VastausID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				return readAllVastaukset();
			}
			catch(SQLException e) {
				return null;
			}
		}

		public Vastaukset readVastaus(String id) {
			Vastaukset vastaus = new Vastaukset();
			try {
				String sql="select * from Vastaukset where VastausID = ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet RS=pstmt.executeQuery();
				while (RS.next()){
					vastaus.setId(RS.getInt("VastausID"));
					vastaus.setKysymysId(RS.getInt("KysymysID"));
					vastaus.setEhdokasId(RS.getInt("EhdokasID"));
					vastaus.setVastasi(RS.getInt("Vastasi"));
					vastaus.setPerustelu(RS.getString("Perustelu"));
					
				}
				return vastaus;
			}
			catch(SQLException e) {
				return null;
			}
		}

		public Vastaukset updateVastaus(Vastaukset v) {
			try {
				String sql="update vastaukset set Vastasi=?, Perustelu=? where VastausID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, v.getVastasi());
				System.out.print(v.getVastasi());
				pstmt.setString(2, v.getPerustelu());
				System.out.print(v.getPerustelu());
				pstmt.setInt(3, v.getId());
				
				pstmt.executeUpdate();
				return readVastaus(v.getId()+"");
			}
			catch(SQLException e) {
				return null;
			}
		
		}

		
		public Map<Integer, Float> readAllEhdokasVastausAverage() {
			Map<Integer, Float> ehdokkaidenVastauksienAvg = new HashMap<Integer, Float>();
			try {
				String sql="SELECT EhdokasID, AVG(Vastasi) AS avg FROM vastaukset GROUP BY EhdokasID";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				ResultSet RS = pstmt.executeQuery();
				while (RS.next()){
					ehdokkaidenVastauksienAvg.put(RS.getInt("EhdokasID"), RS.getFloat("avg"));
				}
				return ehdokkaidenVastauksienAvg;
			}
			catch(SQLException e) {
				return null;
			}
		}
		
		public Map<Integer, Float> readBestEhdokkaat(float userAvg) {
			Map<Integer, Float> bestEhdokkaat = new HashMap<Integer, Float>();
			try {
				String sql="SELECT EhdokasID, AVG(Vastasi) AS avg FROM vastaukset GROUP BY EhdokasID ORDER BY ABS(AVG(Vastasi) - " + userAvg + ") ASC LIMIT 3;";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet RS = pstmt.executeQuery();
				while (RS.next()){
					bestEhdokkaat.put(RS.getInt("EhdokasID"), RS.getFloat("avg"));
				}
				return bestEhdokkaat;
			}
			catch(SQLException e) {
				return null;
			}
		}

		public Vastaukset insertVastaus(Vastaukset vastaus) {
			try {
				String sql="INSERT INTO vastaukset (KysymysID, EhdokasID, Vastasi, Perustelu) VALUES (?, ?, ?, ?);";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, 1);
				pstmt.setInt(3, vastaus.getVastasi());
				pstmt.setString(4, vastaus.getPerustelu());
				pstmt.executeUpdate();
			}
			catch(SQLException e) {
				return null;
			}
			return vastaus;
		}
}
