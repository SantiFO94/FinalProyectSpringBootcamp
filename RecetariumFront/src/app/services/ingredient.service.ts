import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredient } from '../interfaces/ingredient';
import { ResponseIngredients } from '../interfaces/responses';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  private readonly URL = 'ingredients';

  constructor(private http:HttpClient) { }

  getAll() : Observable<Ingredient[]> {
    return this.http.get<ResponseIngredients>(`${this.URL}/all`).pipe(
      map(response => response.ingredients)
    );
  }
}
