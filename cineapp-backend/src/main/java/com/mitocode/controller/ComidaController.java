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
import com.mitocode.model.Comida;
import com.mitocode.model.Pelicula;
import com.mitocode.service.IComidaService;

/**
 * @author Usuario
 *
 */
@RestController
@RequestMapping("/comidas")
public class ComidaController {

	@Autowired
	private IComidaService service;
	
	@GetMapping
	public ResponseEntity<List<Comida>> listar() {
		List<Comida> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Comida> listarPorId(@PathVariable("id") Integer id) {
		Comida comida = service.leer(id);
		if (comida.getIdComida() == null) {
			throw new ModeloNotFoundException("Id Comida no existe: " + id);
		}
		
		Resource<Comida> resource = new Resource<Comida>(comida);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("comida-resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Comida> registrar(@RequestBody Comida cli) {
		Comida comida = service.registrar(cli);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comida.getIdComida()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Comida> modificar(@RequestBody Comida cli) {
		Comida comida = service.modificar(cli);
		return new ResponseEntity<Comida>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Comida comida = service.leer(id);
		
		if (comida.getIdComida() == null) {
			throw new ModeloNotFoundException("Id Comida no encontrado: " + id);
		}else {
			service.eliminar(id);
		}
	}
}
