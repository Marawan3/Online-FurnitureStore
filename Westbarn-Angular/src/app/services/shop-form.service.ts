import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Country } from 'src/app/common/country';

import { map } from 'rxjs/operators';
import { State } from '../common/state';

@Injectable({
  providedIn: 'root'
})
export class ShopFormService {

  private countriesUrl:any = ['A','b'];
  private  statesUrl:any = ['C','D'];

  constructor(private httpClient: HttpClient) { }

  // our angular components will subscribe to these methods and 
  // receive this async data

  getCreditCardMonths(startMonth: number): Observable<number[]> {
    let data: number[] = [];

    // build array for "Month" drop down list
    // - start at current month and loop until

    for (let theMonth = startMonth; theMonth <= 12; theMonth++) {

      data.push(theMonth);
    }

    // the 'of' operator from rxjs will wrap an object as an observable
    return of(data);
  }

  getCreditCardYears(): Observable<number[]> {

    let data: number[] = [];

    // build array for "Year" drop down list
    // - start at current yera and loop for next 5 years

    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;

    for (let theYear = startYear; theYear <= endYear; theYear++) {

      data.push(theYear);
    }

    // the 'of' operator from rxjs will wrap an object as an observable
    return of(data);
  }






  // makes Spring Data REST API call and unwraps the countries from
  // a JSON data package returned
  getCountries(): Observable<Country[]> {

    return this.countriesUrl

      // map method imported from rxjs/operators
      
  }

  getStates(theCountryCode: string): Observable<State[]> {

    // search url, retrieve states based on the country code -> "IN", "US", "CA"
    const searchStatesUrl = `${this.statesUrl}/search/findByCountryCode?code=${theCountryCode}`;

    return this.statesUrl
  }


}
