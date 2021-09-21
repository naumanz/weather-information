import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {first, map, tap} from 'rxjs/operators';
import { Observable } from 'rxjs';
import { IntervalObservable } from 'rxjs/observable/IntervalObservable';
import 'rxjs/add/operator/takeWhile';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpClient]
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'frontend';
  weathers = [];
  city = 'Lahore';
  country = 'Pakistan';
  currentWeather: {
    temp: '',
    feels_like: '',
    description: ''
  };

  private alive: boolean;
  private REST_API = 'http://localhost:8080/weather/details?city=' + this.city + '&country=' + this.country;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.alive = true;
    this.http.get(this.REST_API).pipe(
      first(),
      tap(result => console.log('Message received from the server: ', result)),
      map(result => {
       this.weathers = (result as any).weather;
       this.currentWeather = this.weathers[0];
       })
    ).subscribe();

    // get our data every subsequent 10 seconds
    IntervalObservable.create(10000)
      .takeWhile(() => this.alive) // only fires when component is alive
      .subscribe(() => {
        this.http.get(this.REST_API).pipe(
              first(),
              tap(result => console.log('Message received from the server: ', result)),
              map(result => {
                this.weathers = (result as any).weather;
                this.currentWeather = this.weathers[0];
              })
            ).subscribe();
      });

  }

  ngOnDestroy(): void {
    this.alive = false; // switches your IntervalObservable off
  }

}
