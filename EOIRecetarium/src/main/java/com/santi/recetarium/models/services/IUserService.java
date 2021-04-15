package com.santi.recetarium.models.services;

import java.util.List;

import com.santi.recetarium.models.entities.User;

public interface IUserService {

	public List<User> findAll();

    public User findById(Integer id);

    public User save(User user);

    public void delete(User user);

    public void deleteById(Integer id);
}
