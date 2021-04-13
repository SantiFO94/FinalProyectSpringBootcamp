import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredient } from '../interfaces/ingredient';
import { ResponseIngredient, ResponseIngredients } from '../interfaces/responses';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  private readonly URL = 'ingredients';

  constructor(private http:HttpClient) { }

  getAll(): Observable<Ingredient[]> {
    return this.http.get<ResponseIngredients>(this.URL + '/all').pipe(
      map(response => response.ingredients)
    );
  }

<<<<<<< HEAD
  getIngredient(id: number): Observable<Ingredient> {
    return this.http.get<ResponseIngredient>(`${this.URL}/ingredient/${id}`).pipe(
      map(resp => resp.ingredient)
    );
  }

  addIngredient(ingredient: Ingredient): Observable<Ingredient> {
    return this.http.post<ResponseIngredient>(this.URL + '/add', ingredient).pipe(
      map(resp => resp.ingredient)
=======
  addIngredient(ingredient: Ingredient): Observable<Ingredient> {
    return this.http.post<ResponseIngredient>(`${this.URL}/add`, ingredient).pipe(
      map(response => response.ingredient)
>>>>>>> 9a8394e200dbc6d7e2234dba5fbd81cad39a4e79
    );
  }

  deleteIngredient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.URL}/delete/${id}`);
  }
}
