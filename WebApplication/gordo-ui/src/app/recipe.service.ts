import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recipe } from './recipe';
import { Step } from './step';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class RecipeService {
  private apiServerUrl = environment.apiBaseUrl;
  private gordobotUrl = environment.gordobotBaseUrl;

  constructor(private http: HttpClient){}

  public getRecipe(RecipeId: string): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.apiServerUrl}/Recipe/get/${RecipeId}`);
  }
  
  public getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.apiServerUrl}/Recipe/getAll`);
  }

  public addRecipe(Recipe: Recipe): Observable<Recipe> {
    return this.http.post<Recipe>(`${this.apiServerUrl}/Recipe/add`, Recipe);
  }

  public updateRecipe(Recipe: Recipe): Observable<Recipe> {
    return this.http.put<Recipe>(`${this.apiServerUrl}/Recipe/update`, Recipe);
  }

  public deleteRecipe(RecipeId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/Recipe/delete/${RecipeId}`);
  }

  public searchRecipes(term: string): Observable<Recipe> { // change return type it isn't correct
    return new Observable<Recipe>(); // change this, it isn't correct
  }

  public populateRecipes(): Observable<Recipe[]> {
    return this.http.post<Recipe[]>(`${this.apiServerUrl}/Recipe/populateRecipes`, {});
  }



  public sendToGordo(Step: Step): Observable<Step> {
    return this.http.post<Step>(`${this.gordobotUrl}/post_json`, Step);
  }
}
