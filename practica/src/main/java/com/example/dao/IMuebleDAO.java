/**
 * 
 */
package com.example.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Mueble;

/**
 * @author BRYAN
 *
 */
public interface IMuebleDAO extends JpaRepository<Mueble, Integer>{

	Mueble findByColor(String color);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO Mueble(tela) VALUES(:tela)", nativeQuery=true)
	Integer registrarOnlyTela(@Param("tela") String tela);
}
