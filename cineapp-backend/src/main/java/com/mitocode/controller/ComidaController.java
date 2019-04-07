/**
 * 
 */
package com.mitocode.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Comida;
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

	//	MediaType.APPLICATION_OCTET_STREAM_VALUE indica que devuelve una respuesta en crudo (arreglo de bytes)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> listarPorId(@PathVariable("id") Integer id) {
		Comida c = service.leer(id);
		byte[] data = c.getFoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
		
//		Comida comida = service.leer(id);
//		if (comida.getIdComida() == null) {
//			throw new ModeloNotFoundException("Id Comida no existe: " + id);
//		}
//		
//		Resource<Comida> resource = new Resource<Comida>(comida);
//		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
//		resource.add(linkTo.withRel("comida-resource"));
//		return resource;
	}
	
	@PostMapping
	public Comida registrar(@RequestPart("comida") Comida comida, @RequestPart("file") MultipartFile file) throws IOException{
		Comida c = comida;
		c.setFoto(file.getBytes());
		return service.registrar(c);
		
//		Comida comida = service.registrar(cli);
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comida.getIdComida()).toUri();
//		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public Comida modificar(@RequestPart("comida") Comida comida, @RequestPart("file") MultipartFile file) throws IOException{
		Comida c = comida;
		c.setFoto(file.getBytes());
		return service.modificar(c);
		
//		Comida comida = service.modificar(cli);
//		return new ResponseEntity<Comida>(HttpStatus.OK);
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
