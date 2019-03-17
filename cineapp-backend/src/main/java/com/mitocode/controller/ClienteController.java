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
import com.mitocode.model.Cliente;
import com.mitocode.model.Pelicula;
import com.mitocode.service.IClienteService;

/**
 * @author Usuario
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Cliente> listarPorId(@PathVariable("id") Integer id) {
		Cliente cliente = service.leer(id);
		if (cliente.getIdCliente() == null) {
			throw new ModeloNotFoundException("Id Cliente no existe: " + id);
		}
		
		Resource<Cliente> resource = new Resource<Cliente>(cliente);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("cliente-resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Cliente> registrar(@RequestBody Cliente cli) {
		Cliente cliente = service.registrar(cli);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Cliente> modificar(@RequestBody Cliente cli) {
		Cliente cliente = service.modificar(cli);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Cliente cliente = service.leer(id);
		
		if (cliente.getIdCliente() == null) {
			throw new ModeloNotFoundException("Id Cliente no encontrado: " + id);
		}else {
			service.eliminar(id);
		}
	}
}
