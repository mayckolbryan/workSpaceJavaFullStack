/**
 * 
 */
package com.example.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author BRYAN
 *
 */
@Entity
public class Mueble {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMueble;
	
	@Column(name="color", length=15)
	private String color;
	
	@Column(name="dimension", length=15)
	private String dimension;

	@Column(name="tela", length=15)
	private String tela;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name="fechaPublicacion")
	private LocalDate fechaPublicacion;

	public int getIdMueble() {
		return idMueble;
	}

	public void setIdMueble(int idMueble) {
		this.idMueble = idMueble;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
}
