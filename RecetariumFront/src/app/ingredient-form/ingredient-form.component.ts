import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Ingredient } from '../interfaces/ingredient';
import { IngredientService } from '../services/ingredient.service';

@Component({
  selector: 'ingredient-form',
  templateUrl: './ingredient-form.component.html',
  styleUrls: ['./ingredient-form.component.css']
})
export class IngredientFormComponent implements OnInit {

  @Output() insert = new EventEmitter<Ingredient>();

  newIngredient!: Ingredient;

  constructor(private ingredientService: IngredientService) { }

  ngOnInit(): void {
    this.resetIngredient();
  }

  resetIngredient(): void {
    this.newIngredient = {
      name: '',
      quantity: 0,
      measure: ''
    };
  }

  addIngredient(): void {
    this.ingredientService.addIngredient(this.newIngredient).subscribe(
      ingredient => {
        this.insert.emit(ingredient);
        this.resetIngredient();
      }
    );
  }

}
