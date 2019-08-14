package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer>{

	Usuario findByUsername(String username);
}
