/**
 * 
 */
package com.mitocode.dto;

import java.util.List;

import com.mitocode.model.Comida;
import com.mitocode.model.Venta;

/**
 * @author Usuario
 *
 */
public class VentaDTO {

	private Venta venta;
	private List<Comida> lstComidas;
	
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
	 * @return the lstComidas
	 */
	public List<Comida> getLstComidas() {
		return lstComidas;
	}
	/**
	 * @param lstComidas the lstComidas to set
	 */
	public void setLstComidas(List<Comida> lstComidas) {
		this.lstComidas = lstComidas;
	}
}
