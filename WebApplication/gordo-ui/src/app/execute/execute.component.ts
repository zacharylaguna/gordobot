import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

import { USER } from '../mock-user';
import { Recipe } from '../recipe';
import { Step } from '../step';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-execute',
  templateUrl: './execute.component.html',
  styleUrls: [ './execute.component.css' ]
})
export class ExecuteComponent implements OnInit {
  user = USER;
  recipe: Recipe | undefined;
  id_1: string | null | undefined;
  id_2: string | null | undefined;
  step: Step | undefined;
  completed: boolean | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private recipeService: RecipeService,
    private location: Location )
    {
      this.route.paramMap.subscribe( params => {
      this.id_1 = params.get('id1');
      this.id_2 = params.get('id2'); });
      this.route.params.subscribe(params => {
        this.paramsChange(params['id2']);/*Change*/ });/*params*/  //end constructor
    }

  ngOnInit(): void {
    this.getRecipe();
  }
  getRecipe(): void {
    //const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    if(this.id_1 && this.id_1 != "") this.recipeService.getRecipe(this.id_1)
      .subscribe();
  }
  getStep() {
  }
  paramsChange(term: string | null | undefined) {
    this.id_2 = term;
    this.getStep();
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.recipe) {
      this.recipeService.updateRecipe(this.recipe)
        .subscribe(() => this.goBack());
    }
  }
  flipSpeaker(): void {
    this.user.speaker = !this.user.speaker;
  }
  Complete(): void {
    if(this.recipe && this.id_2) this.router.navigate(['execute/'+this.recipe.id+'/'+this.next()]);
  }
  next(): number {
    if(this.id_2) return parseInt(this.id_2)+1;
    return 0;
  }
}