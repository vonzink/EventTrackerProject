import { Component }               from '@angular/core';
import { bootstrapApplication }    from '@angular/platform-browser';
import { provideRouter }           from '@angular/router';
import { RouterOutlet }            from '@angular/router';
import { appRoutes }               from './app.routes';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  title = 'ngLoanTracker';
}

// Move this outside the class!
bootstrapApplication(App, {
  providers: [
    provideRouter(appRoutes)
  ]
}).catch(err => console.error(err));
