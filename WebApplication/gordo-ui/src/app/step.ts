export interface Step {
    instruction: string;
    destinationPosition: number;
    ingredient: string;
    amount: number;
    unit: string;
    ifActionable: boolean;
}