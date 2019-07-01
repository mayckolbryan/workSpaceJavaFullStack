package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.IMuebleDAO;
import com.example.model.Mueble;

@Service
public class MuebleServiceImpl implements IMuebleService{

	@Autowired
	private IMuebleDAO muebleDao;
	
	@Override
	public void registrar(Mueble m) {
		muebleDao.save(m);
	}

	@Override
	public void modificar(Mueble m) {
		muebleDao.save(m);
	}

	@Override
	public List<Mueble> listar() {
		return muebleDao.findAll();
	}

	@Override
	public Mueble listarPorId(int id) {
		Optional<Mueble> op = muebleDao.findById(id);
		return op.isPresent() ? op.get(): new Mueble();
	}

	@Override
	public void eliminar(int id) {
		muebleDao.deleteById(id);
	}

	@Override
	public Mueble listarPorColor(String color) {
		return muebleDao.findByColor(color);
	}

	@Override
	public Integer registrarOnlyTela(Mueble m) {
		return muebleDao.registrarOnlyTela(m.getTela());
	}
}
