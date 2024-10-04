import { Component} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { CalculatorComponent } from './calculator/calculator.component';




@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CalculatorComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})


export class AppComponent {
  title = 'H4_oef1';
  // Two-way data binding for username
  username: string = '';

  // Method to clear the username
  clearUsername() {
    this.username = '';
  }
}
