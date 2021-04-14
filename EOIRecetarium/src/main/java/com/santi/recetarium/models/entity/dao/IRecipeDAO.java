package com.santi.recetarium.models.entity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santi.recetarium.models.entity.Recipe;

@Repository
public interface IRecipeDAO extends CrudRepository<Recipe, Integer> {

}
