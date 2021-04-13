import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Ingredient } from '../interfaces/ingredient';
import { IngredientService } from '../services/ingredient.service';

@Component({
  selector: 'ingredient-item',
  templateUrl: './ingredient-item.component.html',
  styleUrls: ['./ingredient-item.component.css']
})
export class IngredientItemComponent implements OnInit {

  @Input() ingredient!: Ingredient;
<<<<<<< HEAD
  @Output() deleted = new EventEmitter<void>();
=======
  @Output() delete = new EventEmitter<void>();
>>>>>>> 9a8394e200dbc6d7e2234dba5fbd81cad39a4e79

  constructor(private ingredientService: IngredientService) { }

  ngOnInit(): void {
  }

  deleteIngredient() {
<<<<<<< HEAD
    this.ingredientService.deleteIngredient(this.ingredient.idIngredient!).subscribe(
      () => this.deleted.emit()
    );
  }
=======
    this.ingredientService.deleteIngredient(this.ingredient.id_ingredient as number).subscribe(
      () => this.delete.emit()
    );
  }

>>>>>>> 9a8394e200dbc6d7e2234dba5fbd81cad39a4e79
}
