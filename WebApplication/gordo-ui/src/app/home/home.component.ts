import { Component, OnInit } from '@angular/core';

import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public recipes: Recipe[] = [];
  public username: string = "public";

  constructor(private RecipeService: RecipeService){}

  ngOnInit(): void {
    this.getRecipes();
  }

  // ngAfterViewInit(): void {
  //   this.username = localStorage.getItem("username")!;
  // }

  reset() {
    this.RecipeService.populateRecipes().subscribe(
      (response: Recipe[]) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getRecipes(): void {
    this.RecipeService.getRecipes().subscribe(
      (response: Recipe[]) => {
        this.recipes = response;
        console.log(this.recipes);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteRecipe(id: string): void {
    // do delete here
  }

  public openModal(): void {
    localStorage.getItem("username");
  }

  onLogin(event:any) {
    this.username = event.target.value;
    // console.log("submitted");
    console.log(this.username);

    localStorage.setItem("username", this.username);

    return this.username;
  }

  

}