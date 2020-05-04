package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Blackout> getEventi(Nerc nerc){
		final String sql="SELECT p.customers_affected,p.date_event_finished,p.date_event_began FROM poweroutages AS p WHERE p.nerc_id=?;";
		List<Blackout> lista=new ArrayList<>();
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();
			

			while (res.next()) {
				Blackout b = new Blackout(res.getInt("customers_affected"), res.getTimestamp(2).toLocalDateTime(),res.getTimestamp(3).toLocalDateTime());
				lista.add(b);
			}

			conn.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;		
	}
}
