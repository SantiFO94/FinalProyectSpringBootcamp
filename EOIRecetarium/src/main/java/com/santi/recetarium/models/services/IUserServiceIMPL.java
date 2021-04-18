package com.santi.recetarium.models.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.recetarium.models.dao.IUserDAO;
import com.santi.recetarium.models.entities.User;
import com.santi.recetarium.models.entities.dto.UserDTORegister;
import com.santi.recetarium.utilities.SecurityUtils;

@Service
public class IUserServiceIMPL implements IUserService {

	@Autowired
	private IUserDAO userDao;
	
	@Autowired
	private SecurityUtils securityUtils;
	
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
	
	public User login(String email, String password) throws NoSuchAlgorithmException {
		return this.userDao.login(email, securityUtils.encodePassword(password));
	}
	
	public User register(UserDTORegister userRegister) throws NoSuchAlgorithmException {
		User user = new User();
		
		user.setNickname(userRegister.getNickname());
		user.setPassword(securityUtils.encodePassword(userRegister.getPassword()));
		user.setEmail(userRegister.getEmail());
		
		return user;
	}

}
