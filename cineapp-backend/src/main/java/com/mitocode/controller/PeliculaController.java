/**
 * 
 */
package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Pelicula;
import com.mitocode.service.IPeliculaService;

/**
 * @author BRYAN
 *
 */
@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

	@Autowired
	private IPeliculaService service;
	
	@GetMapping
	public ResponseEntity<List<Pelicula>>  listar() {
		List<Pelicula> lista = service.listar();
		return new ResponseEntity<List<Pelicula>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Pelicula> listarPorId(@PathVariable("id") Integer id) {
		Pelicula pelicula = service.leer(id);
		
		if (pelicula.getIdPelicula() == null) {
			throw new ModeloNotFoundException("Id pelicula no encontrado: " + id);
		}
		Resource<Pelicula> resource = new Resource<Pelicula>(pelicula);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("pelicula-resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Pelicula> registrar(@RequestBody Pelicula pel) {
		Pelicula pelicula = service.registrar(pel);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pelicula.getIdPelicula()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Pelicula> modificar(@RequestBody Pelicula pel) {
		Pelicula pelicula = service.modificar(pel);
		return new ResponseEntity<Pelicula>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Pelicula pelicula = service.leer(id);
		
		if (pelicula.getIdPelicula() == null) {
			throw new ModeloNotFoundException("Id pelicula no encontrado: " + id);
		}else {
			service.eliminar(id);
		}
	}
}
