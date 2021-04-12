package com.santi.recetarium.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santi.recetarium.entity.Ingredient;

@Repository
public interface IIngredientDAO extends JpaRepository<Ingredient, Long> {

}
