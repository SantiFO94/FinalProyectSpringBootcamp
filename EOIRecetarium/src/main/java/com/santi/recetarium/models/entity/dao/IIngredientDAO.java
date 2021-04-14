package com.santi.recetarium.models.entity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santi.recetarium.models.entity.Ingredient;

@Repository
public interface IIngredientDAO extends CrudRepository<Ingredient, Integer> {

}
