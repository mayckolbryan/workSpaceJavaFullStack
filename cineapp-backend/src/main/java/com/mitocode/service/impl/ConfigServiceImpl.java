/**
 * 
 */
package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IConfigDAO;
import com.mitocode.model.Config;
import com.mitocode.service.IConfigService;
import com.mitocode.service.IConfigService;

/**
 * @author BRYAN
 *
 */
@Service
public class ConfigServiceImpl implements IConfigService{
	
	@Autowired
	private IConfigDAO dao;
	
	@Override
	public Config registrar(Config obj) {		
		return dao.save(obj);
	}

	@Override
	public Config modificar(Config obj) {
		return dao.save(obj);
	}

	@Override
	public List<Config> listar() {
		return dao.findAll();
	}

	@Override
	public Config leer(Integer id) {
		Optional<Config> op = dao.findById(id);
		return op.isPresent() ? op.get() : new Config();
	}

	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Config leerParametro(String parametro) {
		return dao.findByParametro(parametro);
	}

}
