/**
 * 
 */
package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Pelicula;
import com.mitocode.service.IPeliculaService;

/**
 * @author Usuario
 *
 */
@RestController
@RequestMapping(value = "peliculas")
public class PeliculaController {
	
	@Autowired
	private IPeliculaService service;
	
	//@RequestBody permite interpretar los valores Json que le llegan a este metodo.
	@PostMapping
	public void registrar(@RequestBody Pelicula pel) {
		service.registrar(pel);
	}
	
	@PutMapping
	public void modificar(@RequestBody Pelicula pel) {
		service.modificar(pel);
	}
	
	@GetMapping
	public List<Pelicula> listar() {
		return service.listar();
	}
	
	//@PathVariable es configarcion para poder tomar la variable que viene por GET.
	@GetMapping(value = "/{id}")
	public Pelicula listarPorId(@PathVariable("id") Integer id) {
		return service.listarPorId(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
}
