package com.santi.recetarium.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santi.recetarium.models.entities.User;

@Repository
public interface IUserDAO extends CrudRepository<User, Integer> {

	@Query("select u from User u where nickname = ?1 and password = ?2")
	public User login(String nickname, String password);
}
