import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

import { USER } from '../mock-user';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
import { Step } from '../step';
import { HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: [ './preview.component.css' ]
})
export class PreviewComponent implements OnInit {

  // environment variables
  urlid: string = "";
  private routeSub!: Subscription;

  // data variables
  recipe!: Recipe;

  // ui variables
  activeStepidx: number = 0;
  loading: boolean = false;

  constructor(private RecipeService: RecipeService, private route: ActivatedRoute){}
  
  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      (this.urlid = params['id']) //log the value of id
    });
  
    this.getRecipe(this.urlid);
  }

  public getRecipe(id: string): void {
    this.RecipeService.getRecipe(id).subscribe(
      (response: Recipe) => {
        this.recipe = response;
        console.log(this.recipe);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public sendToGordo(step: Step): void {
    this.RecipeService.sendToGordo(step).subscribe(
      (response: Step) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onNextClick(){
    if (this.activeStepidx < this.recipe.steps.length){
      // perform http request for dispense here //
      // send http request to api for dispensing //
      // send instruction to the raspberry pi //
      // 2 parameters (address, message: json)

      this.loading = true;

      this.sendToGordo(this.recipe.steps[this.activeStepidx]);

      setTimeout(() => {
        this.activeStepidx += 1;
        this.loading = false;
      }, 1000); // 15 sec
      
      // this.activeStepidx += 1;
    }
    else{ // when instruction set is finished
      this.activeStepidx = 0; // reset instructions

      this.routeSub.unsubscribe();
    }
  }
}