package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Pelicula;

//JpaRepository es un interfaz de SpringDataJPA que me proporciona los metodos basicos para un CRUD, se le pasa el tipo de Entidad y el tipo de variable de la primary key. <T, ID>
public interface IPeliculaDAO extends JpaRepository<Pelicula, Integer>{
}
