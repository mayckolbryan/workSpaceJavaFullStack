/**
 * 
 */
package com.mitocode.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author Usuario
 *
 */
@Entity
@IdClass(VentaComidaPK.class)
public class VentaComida {
	
	@Id
	private Venta venta;
	@Id
	private Comida comida;
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
	 * @return the comida
	 */
	public Comida getComida() {
		return comida;
	}
	/**
	 * @param comida the comida to set
	 */
	public void setComida(Comida comida) {
		this.comida = comida;
	}	
}
