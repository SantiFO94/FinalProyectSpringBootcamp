package com.santi.recetarium.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santi.recetarium.models.entities.Ingredient;

@Repository
public interface IIngredientDAO extends CrudRepository<Ingredient, Integer> {

}
