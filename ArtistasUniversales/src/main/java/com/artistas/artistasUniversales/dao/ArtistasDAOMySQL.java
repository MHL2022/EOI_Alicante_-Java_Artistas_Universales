package com.artistas.artistasUniversales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.PooledConnection;

import com.artistas.artistasUniversales.modelo.Artistas;


public class ArtistasDAOMySQL implements ArtistasDAO{
	private PooledConnection pcon;
	public ArtistasDAOMySQL(PooledConnection pcon) {
		this.pcon = pcon; 
	}
	
	@Override
	public List<Artistas> findAll() {
		List<Artistas> artistas = new ArrayList<>();
		try (Connection con = pcon.getConnection()){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from " + Artistas.TABLA );
			while(rs.next()) {
				artistas.add(new Artistas(rs.getInt("id"),rs.getString("nombre"),rs.getString("fecha_nacimiento"),rs.getString("fecha_fallecimiento"),rs.getString("disciplina"), rs.getString("obra_representativa"), rs.getString("periodo") ));
			}
		}catch(SQLException e){
			System.err.println("Error obteniendo artistas: "+ e.getMessage());
		}
		return artistas;
	}

	@Override
	public Artistas findById(int id) {
		Artistas a= null;
		String consulta = "SELECT * from " + Artistas.TABLA + " WHERE id = ? ";
		try (Connection con = pcon.getConnection()) {
			PreparedStatement st = con.prepareStatement(consulta);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				a = new Artistas(rs.getInt("id"), rs.getString("nombre"), rs.getString("fecha_nacimiento"),
						rs.getString("fecha_fallecimiento"), rs.getString("disciplina"),
						rs.getString("obra_representativa"), rs.getString("periodo"));
			}
		} catch (SQLException e) {
			System.err.println("Error obteniendo artista: " + e.getMessage());
		}
		return a;
		
	}
//Inserta un usuario y devuelve la id generada (o -1 si falla)
	@Override
	public int insert(Artistas a) {
		int  id = -1;
		String consulta = "INSERT INTO " + Artistas.TABLA + " (nombre,fecha_nacimiento,fecha_fallecimiento, disciplina, obra_representativa, periodo ) VALUES (? , ? , ? , ? , ? , ?) ";
		try (Connection con = pcon.getConnection()) {
			PreparedStatement st = con.prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, a.getNombre());
			st.setString(2, a.getFecha_nacimiento());
			st.setString(3, a.getFecha_fallecimiento());
			st.setString(4, a.getDisciplina());
			st.setString(5, a.getObra_representativa());
			st.setString(6, a.getPeriodo());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			if (rs.next()) {
				a = new Artistas(rs.getInt("id"), rs.getString("nombre"), rs.getString("fecha_nacimiento"),
						rs.getString("fecha_fallecimiento"), rs.getString("disciplina"),
						rs.getString("obra_representativa"), rs.getString("periodo"));
			}
		} catch (SQLException e) {
			System.err.println("Error insertando artista: " + e.getMessage());
		}
		return id;
		
	}

	@Override
	public boolean update(Artistas a) {
		boolean  actualizado = false;
		String consulta = "UPDATE " + Artistas.TABLA + " SET nombre=?, fecha_nacimiento=?, fecha_fallecimiento=?, disciplina=?, obra_representativa=?, periodo=? WHERE id=?";
		try (Connection con = pcon.getConnection()) {
			PreparedStatement st = con.prepareStatement(consulta);
			st.setString(1, a.getNombre());
			st.setString(2, a.getFecha_nacimiento());
			st.setString(3, a.getFecha_fallecimiento());
			st.setString(4, a.getDisciplina());
			st.setString(5, a.getObra_representativa());
			st.setString(6, a.getPeriodo());
			st.setInt(7, a.getId());
			if(st.executeUpdate()>0) {
				actualizado = true;
			}
		
			
		} catch (SQLException e) {
			System.err.println("Error modificando artista: " + e.getMessage());
			e.printStackTrace();
		}
		return actualizado;
	}

	@Override
	public boolean delete(int id) {
		boolean  borrado = false;
		String consulta = "DELETE FROM " + Artistas.TABLA + " WHERE id = ?";
		try (Connection con = pcon.getConnection()) {
			PreparedStatement st = con.prepareStatement(consulta);		
			st.setInt(1, id);
			if(st.executeUpdate()>0) {
				borrado = true;
			}
		
			
		} catch (SQLException e) {
			System.err.println("Error borrando artista: " + e.getMessage());
		}
		return borrado;
	
	}
	
}
