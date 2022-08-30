import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ProductCategory } from 'src/app/common/product-category';
import { ProductService } from 'src/app/services/product.service';
import {MatMenuModule} from '@angular/material/menu';
@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrls: ['./product-category-menu.component.css']
})
export class ProductCategoryMenuComponent implements OnInit {
  productCategories: ProductCategory[];
  
  constructor(private productService: ProductService) { }
  ngOnInit() {
  this.listProductCategories();
  }
  listProductCategories() {
    
  this.productService.getProductCategories().subscribe(
  data => {
 //console.log('Product Categories=' + JSON.stringify(data));
  this.productCategories = data.filter((catname, index) => data.indexOf(catname) !== index);
  console.log('Product Filter=' + JSON.stringify(data));
  }
  )
  
}
uniqByFilter<subcatname>(data: subcatname[]) {
  console.log('90')
  return data.filter((value, index) => data.indexOf(value) === index);
}
  }
  