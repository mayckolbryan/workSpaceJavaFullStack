package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Config;

public interface IConfigDAO extends JpaRepository<Config, Integer>{

	//Spring posee las paalabras reservadas "findBy" que seguido de un campo de la tabla 
	//funciona como consdicional directamente sin necesida de escribir una consulta.
	Config findByParametro(String parametro);
}
