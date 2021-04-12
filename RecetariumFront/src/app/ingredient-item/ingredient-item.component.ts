import { Component, Input, OnInit, Output } from '@angular/core';
import { Ingredient } from '../interfaces/ingredient';
import { IngredientService } from '../services/ingredient.service';

@Component({
  selector: 'ingredient-item',
  templateUrl: './ingredient-item.component.html',
  styleUrls: ['./ingredient-item.component.css']
})
export class IngredientItemComponent implements OnInit {

  @Input() ingredient!: Ingredient;

  constructor(private ingredientService: IngredientService) { }

  ngOnInit(): void {
  }

}
