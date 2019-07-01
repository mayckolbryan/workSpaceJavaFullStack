package com.example.service;

import java.util.List;

import com.example.model.Mueble;

public interface IMuebleService {

	void registrar(Mueble m);
	void modificar(Mueble m);
	List<Mueble> listar();
	Mueble listarPorId(int id);
	void eliminar(int id);

	Mueble listarPorColor(String color);
	Integer registrarOnlyTela(Mueble m);
}
