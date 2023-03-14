import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {
  recipes$!: Observable<Recipe[]>;
  private searchTerms = new Subject<string>();
  private term: string | undefined | null;

  constructor(
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private location: Location) 
    { this.route.queryParams.subscribe(params => {
    this.queryParamsChange(params['term']);/*Change*/ });/*params*/ } //end constructor
  
  ngOnInit(): void {
  }
  search(term: string): void {
    this.searchTerms.next(term);
  }
  queryParamsChange(term: string | null | undefined) {
    this.term = term;
    // if (this.term != null) this.recipes$ = this.recipeService.searchRecipes(this.term);
  }

  goBack(): void {
    this.location.back();
  }
}