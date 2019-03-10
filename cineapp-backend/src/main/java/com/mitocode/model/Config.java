/**
 * 
 */
package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Usuario
 *
 */
@Entity
@Table(name = "config")
public class Config {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConfig;
	
	@Column(name = "parametro", length = 5)
	private String parametro;
	
	@Column(name = "valor", length = 25)
	private String valor;

	/**
	 * @return the idConfig
	 */
	public Integer getIdConfig() {
		return idConfig;
	}

	/**
	 * @param idConfig the idConfig to set
	 */
	public void setIdConfig(Integer idConfig) {
		this.idConfig = idConfig;
	}

	/**
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}

	/**
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
}
