package com.artistas.artistasUniversales.dao;

import java.util.List;

import com.artistas.artistasUniversales.modelo.Artistas;

public interface ArtistasDAO {
	//Operaciones básicas de la tabla 
	public List<Artistas> findAll();
	public Artistas findById(int id);
	public int insert (Artistas a);
	public boolean update (Artistas a);
	public boolean delete (int id);
	
}
