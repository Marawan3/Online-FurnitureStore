import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Product } from '../common/product';
import { ProductCategory } from '../common/product-category';


@Injectable({
providedIn: 'root'
})
export class ProductService {

     baseURL = 'http://18.208.152.195:5000/api/product/';
 baseURLdet = 'http://18.208.152.195:5000/api/product/find';
 private categoryUrl = 'http://18.208.152.195:5000/api/subcat/findAll';
 baseUrlSearch = "http://18.208.152.195:5000/api/product/findByName";
constructor(private httpClient: HttpClient) { }
readAll(): Observable<any> {
return this.httpClient.get(this.baseURL+"findAll");
}
read(product_id): Observable<any> {
return this.httpClient.get(`${this.baseURL}/${product_id}`);
}
create(data): Observable<any> {
    return this.httpClient.post(this.baseURL, data);
}
update(product_id, data): Observable<any> {
return this.httpClient.put(`${this.baseURL}/${product_id}`, data);
}
delete(product_id): Observable<any> {
return this.httpClient.delete(`${this.baseURL}/${product_id}`);
}
deleteAll(): Observable<any> {
return this.httpClient.delete(this.baseURL);
}
searchByName(productname): Observable<any> {
return this.httpClient.get(`${this.baseURL}?name=${productname}`);
}
readCategory(subcatname): Observable<any> {
    console.log("readcategory")
    return this.httpClient.get(`${this.categoryUrl}/${subcatname}`);
    }
getProduct(product_id: number): Observable<Product> {
    // need to build URL based on product id
    console.log("Get Product ");

    const productUrl = `${this.baseURLdet}/${product_id}`;
    return this.httpClient.get<Product>(productUrl);
    }

    getProductCategories(): Observable<any> {
        console.log(55)
        return this.httpClient.get(this.categoryUrl);
        }

        searchProducts(theKeyword: string): Observable<any> {
            // need to build URL based on the keyword
           console.log(150)
            return this.httpClient.get(`${this.baseUrlSearch}/${theKeyword}`);
            
            }
}
