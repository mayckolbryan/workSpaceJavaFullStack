/**
 * 
 */
package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Usuario
 *
 */
@Entity
@Table(name="detalleVenta")
public class DetalleVenta {
	
	//@JsonIgnore señala un campo de un Pojo para ser ignorado por Jackson durante la serializacion y deserealizacion. Lo ignora en la construccion del Json.
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_venta", nullable = false, foreignKey = @ForeignKey(name = "id_detalle_venta"))
	private Venta venta;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalle;

	@Column(name = "asiento", length = 3)
	private String asiento;

	/**
	 * @return the venta
	 */
	public Venta getVenta() {
		return venta;
	}

	/**
	 * @param venta the venta to set
	 */
	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	/**
	 * @return the idDetalle
	 */
	public Integer getIdDetalle() {
		return idDetalle;
	}

	/**
	 * @param idDetalle the idDetalle to set
	 */
	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	/**
	 * @return the asiento
	 */
	public String getAsiento() {
		return asiento;
	}

	/**
	 * @param asiento the asiento to set
	 */
	public void setAsiento(String asiento) {
		this.asiento = asiento;
	}
}
