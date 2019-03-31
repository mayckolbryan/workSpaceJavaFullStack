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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.mitocode.model.Config;
import com.mitocode.service.IConfigService;

/**
 * @author BRYAN
 *
 */
@RestController
@RequestMapping("/configuraciones")
//CrossOrigin Para permitir la conexion con otro puerto (Angular)
@CrossOrigin(origins = "http://localhost:4200")
public class ConfigController {

	@Autowired
	private IConfigService service;
	
	//@ResponseEntity permite manipular el estado de las respuestas enviadas.
	@GetMapping
	public ResponseEntity<List<Config>> listar() {
		List<Config> lista = service.listar();
		return new ResponseEntity<List<Config>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Config> listarPorId(@PathVariable("id") Integer id) {
		Config conf = service.leer(id);

		if(conf.getIdConfig() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
		}

		Resource<Config> resource = new Resource<Config>(conf);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("genero-resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Config> registrar(@RequestBody Config conf) {
		Config genero = service.registrar(conf);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genero.getIdConfig()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Config conf) {
		service.modificar(conf);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Config conf = service.leer(id);
		if (conf.getIdConfig() == null) {
			throw new ModeloNotFoundException("Id no encontrado: " + id);
		}else {
			service.eliminar(id);
		}
	}
}
