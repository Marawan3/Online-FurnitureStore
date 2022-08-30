import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/common/cart-item';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
@Component({
    selector: 'app-product-list',
    templateUrl: './product-list.component.html',
    styleUrls: ['./product-list.component.css']
    })
    export class ProductListComponent implements OnInit {
         products: Product[];
         currentCategoryId: number = 1;
         previousCategoryId: number = 1;
         currentCategoryName: string;
         searchMode: boolean = false;

         constructor(private productService: ProductService,
            private cartService: CartService,
            private route: ActivatedRoute) { }
        ngOnInit() {
        this.listProducts();
        
        }
        listProducts() {
               // basically checking, did user type something into search bar?
    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    // if yes, then call the search method
    if (this.searchMode) {

      this.handleSearchProducts();

      // otherwise, just list the products
    } else {

      this.handleListProducts();
    }

  }


  handleSearchProducts() {

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');


    if(theKeyword!=null){
        this.productService.searchProducts(theKeyword).subscribe(
        data => {
        this.products = data;
        });
    }else {
            this.productService.readAll().subscribe(
            data => {
            this.products = data;
            });
    }


  }


  handleListProducts() {

    // check if "id" paramter is available first
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('subcat_id');
    
    if (hasCategoryId) {
      // get the "id" param string. convert string to a number using the "+" symbol
      this.currentCategoryId = +this.route.snapshot.paramMap.get('subcat_id');

      // get the "name" param string
      this.currentCategoryName = this.route.snapshot.paramMap.get('subcatname');
      this.productService.readAll().subscribe(
        data => {
        this.products = data;
        });
    }
    else {
      // not category id available ... default to category id 1
      this.currentCategoryId = 1;
      this.currentCategoryName = 'Living';
    }
}
        addToCart(theProduct: Product) {
        console.log(`Adding to cart: ${theProduct.productname}, ${theProduct.price}`);
        const theCartItem = new CartItem(theProduct);
        // TODO ... do the real work
        this.cartService.addToCart(theCartItem);
        }
        
}