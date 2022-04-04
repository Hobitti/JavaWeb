package dao;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	//Ehdokas tablen toiminnot alkavat tästä
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
				String sql="select * from ehdokas where EhdokasID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet RS=pstmt.executeQuery();
				while (RS.next()){
					ehdokas = new Ehdokas();
					ehdokas.setId(RS.getInt("EhdokasID"));
					ehdokas.setNimi(RS.getString("Nimi"));
					ehdokas.setKunta(RS.getInt("KuntaID"));
					ehdokas.setSlogan(RS.getString("Slogan"));
					ehdokas.setPuolue(RS.getInt("PuolueID"));
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
		
		//Kysymykset Tablen toiminnota alkavat tästä
		public ArrayList<Kysymys> readAllKysymykset() {
			ArrayList<Kysymys> list=new ArrayList<>();
			try {
				Statement stmt = conn.createStatement();
				ResultSet RS = stmt.executeQuery("select * from kysymykset");
				while (RS.next()){
					Kysymys next = new Kysymys();
					next.setId(RS.getInt("kysymysID"));
					next.setKysymys(RS.getString("kysymys"));
					next.setSelite(RS.getString("selite"));
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
			Kysymys kysymykset = null;
			try {
				String sql="select * from kysymykset where kysymysID = ?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet RS=pstmt.executeQuery();
				while (RS.next()){
					kysymykset.setId(RS.getInt("kysymysID"));
					kysymykset.setKysymys(RS.getString("kysymys"));
					kysymykset.setSelite(RS.getString("selite"));
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
		
	}
