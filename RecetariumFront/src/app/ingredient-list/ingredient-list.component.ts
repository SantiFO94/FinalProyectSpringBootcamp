import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../interfaces/ingredient';
import { IngredientService } from '../services/ingredient.service';

@Component({
  selector: 'ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {
  title = 'Lista de ingredientes';
  headers = {
    quantity: 'Cantidad',
    measure: 'Medida',
    name: 'Nombre'
  };

  ingredients: Ingredient[] = [];

  constructor(private ingredientService: IngredientService) { }

  ngOnInit(): void {
    this.ingredientService.getAll().subscribe(
      ingredients => this.ingredients = ingredients,
      error => console.log(error),
      () => console.log('Petición completada')
    );
  }

}
