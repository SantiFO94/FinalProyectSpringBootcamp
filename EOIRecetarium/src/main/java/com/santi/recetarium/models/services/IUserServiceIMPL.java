package com.santi.recetarium.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.recetarium.models.dao.IUserDAO;
import com.santi.recetarium.models.entities.User;

@Service
public class IUserServiceIMPL implements IUserService {

	@Autowired
	private IUserDAO userDao;
	
	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}

}
