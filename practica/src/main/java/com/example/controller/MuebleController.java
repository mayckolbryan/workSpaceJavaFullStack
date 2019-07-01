package com.example.controller;

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

import com.example.model.Mueble;
import com.example.service.IMuebleService;

@RestController
@RequestMapping(value="muebles")
public class MuebleController {

	@Autowired
	private IMuebleService muebleService;
	
	@PostMapping
	public void registrar(@RequestBody Mueble m){
		muebleService.registrar(m);
	}
	
	@PutMapping
	public void modificar(@RequestBody Mueble m) {
		muebleService.modificar(m);
	}
	
	@GetMapping
	public List<Mueble> listar(){
		return muebleService.listar();
	}
	
	@GetMapping(value="/{id}")
	public Mueble listarPorId(@PathVariable("id") Integer id) {
		return muebleService.listarPorId(id);
	}
	
	@DeleteMapping(value="/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		muebleService.eliminar(id);
	}
	
	@GetMapping(value="/buscar/{color}")
	public Mueble listarPorColor(@PathVariable("color") String color) {
		return muebleService.listarPorColor(color);
	}
	
	@PostMapping
	public Integer registrarOnlyTela(Mueble m) {
		return muebleService.registrarOnlyTela(m);
	}
}
