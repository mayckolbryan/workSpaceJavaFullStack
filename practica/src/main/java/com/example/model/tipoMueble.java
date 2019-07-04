/**
 * 
 */
package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author BRYAN
 *
 */
@Entity
@Table(name="tipoMueble")
public class tipoMueble {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipoMueble;
	
	@Column(name="descripcion", length=50)
	private String descripcion;
	
	@Column(name="estado", length=1)
	private String estado;

	public int getIdTipoMueble() {
		return idTipoMueble;
	}

	public void setIdTipoMueble(int idTipoMueble) {
		this.idTipoMueble = idTipoMueble;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
