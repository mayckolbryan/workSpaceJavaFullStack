/**
 * 
 */
package com.example.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author BRYAN
 *
 */
@Entity
@Table(name="mueble")
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
	
	@ManyToOne
	@JoinColumn(name="id_tipo_mueble", nullable=false, foreignKey=@ForeignKey(name="id_mueble_tipo_mueble"))
	private TipoMueble tipoMueble;

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

	public TipoMueble getTipoMueble() {
		return tipoMueble;
	}

	public void setTipoMueble(TipoMueble tipoMueble) {
		this.tipoMueble = tipoMueble;
	}
	
}
