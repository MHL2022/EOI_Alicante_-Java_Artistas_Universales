package com.artistas.artistasUniversales;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import javax.sql.PooledConnection;
import com.artistas.artistasUniversales.dao.ArtistasDAO;
import com.artistas.artistasUniversales.dao.ArtistasDAOMySQL;
import com.artistas.artistasUniversales.modelo.Artistas;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class App {
	private static PooledConnection pcon;
	private static Scanner sc;
	private static ArtistasDAO artDAO;
	
	private static void mostrarArtistas() {
		List<Artistas> artistas = artDAO.findAll();
		
		for(Artistas a: artistas) {
			System.out.println(a);		
		}
	}
		private static void addArtistas() {
			System.out.println("Nombre: ");
			String nombre = sc.nextLine();
			System.out.println("Fecha de nacimiento: ");
			String fecha_nacimiento = sc.nextLine();
			System.out.println("Fecha de fallecimiento: ");
			String fecha_fallecimiento = sc.nextLine();
			System.out.println("Disciplina: ");
			String disciplina = sc.nextLine();
			System.out.println("Obra representativa: ");
			String obra_representativa = sc.nextLine();
			System.out.println("Periodo: ");
			String periodo = sc.nextLine();
			
			Artistas a = new Artistas(nombre,fecha_nacimiento, fecha_fallecimiento,disciplina,obra_representativa,periodo );
			int id = artDAO.insert(a);
			if(id > 0) {
				a.setId(id);
				System.out.println("Artista insertado: "+a);
			}
	}
		private static void modificarArtistas() {
			mostrarArtistas();
			System.out.println("Elige un artista para modificar: ");
			int id = Integer.parseInt(sc.nextLine());
			
			Artistas a = artDAO.findById(id);
			
			if(a == null) {
				System.err.println("El artista no existe!");
				return;
				
			}
			
			System.out.println("Nombre del artista("+a.getNombre()+"): ");
			String nombre = sc.nextLine();
			System.out.println("Fecha de nacimiento("+a.getFecha_nacimiento()+"): ");
			String fecha_nacimiento = sc.nextLine();
			System.out.println("Fecha de fallecimiento("+a.getFecha_fallecimiento()+"): ");
			String fecha_fallecimiento = sc.nextLine();
			System.out.println("Disciplina("+a.getDisciplina()+"): ");
			String disciplina = sc.nextLine();
			System.out.println("Obra representativa("+a.getObra_representativa()+"): ");
			String obra_representativa = sc.nextLine();
			System.out.println("Período("+a.getPeriodo()+"): ");
			String periodo = sc.nextLine();
			
			a.setNombre(nombre);
			a.setFecha_nacimiento(fecha_nacimiento);
			a.setFecha_fallecimiento(fecha_fallecimiento);
			a.setDisciplina(disciplina);
			a.setObra_representativa(obra_representativa);
			a.setPeriodo(periodo);
		    if(artDAO.update(a)) {
		    	System.out.println("Artista modificado: "+ a);
		    }
			if(id > 0) {
				a.setId(id);
				System.out.println("Artista insertado: "+a);
			}
	}
	private static void borrarArtistas() {
		mostrarArtistas();
		System.out.println("Elige un artista para borrar: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(artDAO.delete(id)) {
			System.out.println("Artista borrado correctamente!");
		}
		
		
		
	}
	private static void menu() {	
		artDAO = new ArtistasDAOMySQL(pcon);
		int opcion = -1;
		while (opcion != 0) {
			System.out.println("MENU");
			System.out.println("-".repeat(40));
			System.out.println("1) Mostrar todos los artistas");
			System.out.println("2) Añadir artista");
			System.out.println("3) Modificar artista");
			System.out.println("4) Borrar artista");
			System.out.println("0) Salir");
			System.out.println("Elige una opción: ");
			opcion = Integer.parseInt(sc.nextLine());
			switch(opcion) {
			case 1:
				mostrarArtistas();
				break;
			case 2:
				addArtistas();
				break;
			case 3:
				modificarArtistas();
				break;
			case 4:
				borrarArtistas();
				break;
			}
			System.out.println("Presione enter para continuar ...");
			sc.nextLine();
		}
	}
	
    public static void main( String[] args ){
    	sc = new Scanner (System.in);	
    	MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
    	ds.setDatabaseName("historia_del_arte");
    	ds.setServerName("localhost");
    	ds.setPortNumber(3306);
    	ds.setUser("root");
    	ds.setPassword("");
    	try {
			pcon = ds.getPooledConnection();
			menu();
		
//			
			pcon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}
