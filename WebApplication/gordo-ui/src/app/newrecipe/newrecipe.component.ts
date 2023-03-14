import { Component, OnInit } from '@angular/core';

import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
import { HttpErrorResponse } from '@angular/common/http';

import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormArray } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-newrecipe',
  templateUrl: './newrecipe.component.html',
  styleUrls: ['./newrecipe.component.css']
})
export class NewrecipeComponent implements OnInit {

  group: FormGroup = this.fb.group({
    instruction: new FormControl('', [Validators.required]),
    destinationPosition: new FormControl(0, [Validators.required]),
    ingredient: new FormControl('', [Validators.required]),
    amount: new FormControl(0, [Validators.required]),
    unit: new FormControl('', [Validators.required]),
    ifActionable: new FormControl(false)
  });

  recipeForm = this.fb.group({
    name: ['', Validators.required],
    ownerUsername: ['public', Validators.required],
    approximateTime: [0, Validators.required],
    isPrivateRecipe: [false],
    setupContents: this.fb.array([
      this.fb.control('')
    ]),
    steps: this.fb.array([
        this.group
    ])
  });

  newRecipe!: Recipe;

  constructor(private RecipeService: RecipeService, private fb: FormBuilder, private route:Router) { }

  ngOnInit(): void {
  }

  public addRecipe(rec: Recipe): void {
    this.RecipeService.addRecipe(rec).subscribe(
      (response: Recipe) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  get steps() {
    return this.recipeForm.get('steps') as FormArray;
  }

  get setupContents() {
    return this.recipeForm.get('setupContents') as FormArray;
  }

  updateProfile() {
    this.recipeForm.patchValue({
      username: 'Nancy',
      address: {
        street: '123 Drew Street'
      }
    });
  }

  addStep() {
    this.steps.push(this.group);
  }

  addSetupContents() {
    this.setupContents.push(this.fb.control(0));
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value

    this.newRecipe = {...this.newRecipe,...this.recipeForm.value};
    this.addRecipe(this.newRecipe);
    console.warn(this.recipeForm.value);
    console.warn(this.newRecipe);

    this.route.navigate(['/home']);
  }

}
