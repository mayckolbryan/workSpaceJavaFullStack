/**
 * 
 */
package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Pelicula;

/**
 * @author Usuario
 *
 */
public interface IPeliculaService {
	void registrar(Pelicula pel);
	void modificar(Pelicula pel);
	List<Pelicula> listar();
	Pelicula listarPorId(int id);
	void eliminar(int id);
}
