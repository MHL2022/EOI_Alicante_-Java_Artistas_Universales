package com.artistas.artistasUniversales.modelo;

import java.io.Serializable;

/**
 * Modelo tabla Artistas
 */
public class Artistas implements Serializable {
//Atributos y constructor
	private static final long serialVersionUID = 1L;
	public static final String TABLA = "artistas";
	//clave primaria autogenerada
	private int id;
	private String nombre;
	private String fecha_nacimiento;
	private String fecha_fallecimiento;
	private String disciplina;
	private String obra_representativa;
	private String periodo;
	
	public Artistas () {}
//Restos de constructores, getters, setters y toString	
	public Artistas(int id, String nombre,  String fecha_nacimiento, String fecha_fallecimiento, String disciplina,
			String obra_representativa, String periodo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_fallecimiento = fecha_fallecimiento;
		this.disciplina = disciplina;
		this.obra_representativa = obra_representativa;
		this.periodo = periodo;
	}

	public Artistas ( String nombre,String fecha_nacimiento,String fecha_fallecimiento, String disciplina,String obra_representativa, String periodo) {
		this.nombre = nombre;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_fallecimiento = fecha_fallecimiento;
		this.disciplina = disciplina;
		this.obra_representativa = obra_representativa;
		this.periodo = periodo;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getFecha_fallecimiento() {
		return fecha_fallecimiento;
	}

	public void setFecha_fallecimiento(String fecha_fallecimiento) {
		this.fecha_fallecimiento = fecha_fallecimiento;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getObra_representativa() {
		return obra_representativa;
	}

	public void setObra_representativa(String obra_representativa) {
		this.obra_representativa = obra_representativa;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		return "Id: " + id + " - Nombre: " + nombre + " - Fecha de nacimiento: " + fecha_nacimiento
				+ " - Fecha de fallecimiento: " + fecha_fallecimiento + " - Disciplina: " + disciplina
				+ " - Obra representativa: " + obra_representativa + " - Período: " + periodo;
	}

	
	

	
}
