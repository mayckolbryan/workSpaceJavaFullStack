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
import org.springframework.http.MediaType;
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

import com.mitocode.dto.VentaDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Venta;
import com.mitocode.service.IVentaService;

/**
 * @author Usuario
 *
 */
@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;
	
	@GetMapping
	public ResponseEntity<List<Venta>>  listar() {
		List<Venta> lista = service.listar();
		return new ResponseEntity<List<Venta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Venta> listarPorId(@PathVariable("id") Integer id) {
		Venta venta = service.leer(id);
		
		if (venta.getIdVenta() == null) {
			throw new ModeloNotFoundException("Id venta no encontrado: " + id);
		}
		Resource<Venta> resource = new Resource<Venta>(venta);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("venta-resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Integer> registrar(@RequestBody VentaDTO venDTO) {
//		Venta venta = service.registrar(ven);
		int rpta = service.registrarTransaccional(venDTO);
		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venta.getIdVenta()).toUri();
//		return ResponseEntity.created(location).build();
		return new ResponseEntity<Integer>(rpta, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Venta> modificar(@RequestBody Venta ven) {
		Venta venta = service.modificar(ven);
		return new ResponseEntity<Venta>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Venta venta = service.leer(id);
		
		if (venta.getIdVenta() == null) {
			throw new ModeloNotFoundException("Id venta no encontrado: " + id);
		}else {
			service.eliminar(id);
		}
	}
	
	//MediaType.APPLICATION_OCTET_STREAM_VALUE indica que devuelve una respuesta en crudo (arreglo de bytes)
	@PostMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, consumes = "application/json")
	public ResponseEntity<byte[]> generarReporte(@RequestBody VentaDTO venta) {
		byte[] data = null;
		data = service.generarReporte(venta);
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
}
