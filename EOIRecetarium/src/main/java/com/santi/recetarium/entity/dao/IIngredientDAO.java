package com.santi.recetarium.entity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santi.recetarium.entity.Ingredient;

@Repository
public interface IIngredientDAO extends CrudRepository<Ingredient, Long> {

}
