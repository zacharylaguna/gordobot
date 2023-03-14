import { Step } from './step';

export interface Recipe {
  id: string;
  name: string;
  ownerUsername: string;
  approximateTime: number;
  isPrivateRecipe: boolean;
  steps: Step[];
  setupContents: string[];
}
