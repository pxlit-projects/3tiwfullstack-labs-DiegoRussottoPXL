import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-calculator',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './calculator.component.html',
  styleUrl: './calculator.component.css'
})


export class CalculatorComponent {
   // Variables for two-way binding
   num1: number = 0;
   num2: number = 0;
   
   // Variables to store the result and control visibility
   result: number = 0;
   resultVisible: boolean = false;
 
   // Method to add the numbers and show the result
   addNumbers() {
     this.result = this.num1 + this.num2;
     this.resultVisible = true;  // Show the result after the button is clicked
   }
}
