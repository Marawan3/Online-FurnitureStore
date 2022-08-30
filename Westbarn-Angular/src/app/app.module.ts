import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { Router, RouterModule, Routes } from '@angular/router';
import { ProductCreateComponent } from './components/product-create/product-create.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { SearchComponent } from './components/search/search.component';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import { LoginComponent } from './components/login/login.component';
//import { LoginStatusComponent } from './components/login-status/login-status.component';
import { ProductService } from './services/product.service';
import { AngularMaterialModule } from './angular-material.module';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/log-in/log-in.component';
import { AlertComponent } from './components/alert.component';
import { HomeComponent } from './components/home/home.component';
import { ErrorInterceptor } from './helpers/error.interceptor';
import { fakeBackendProvider } from './helpers/fake-backend';
import { JwtInterceptor } from './helpers/jwt.interceptor';
import { AuthGuard } from './helpers/auth.guard';

/*import {
  OKTA_CONFIG,
  OktaAuthModule,
  OktaCallbackComponent,
  OktaAuthGuard
} from '@okta/okta-angular';
import { AuthInterceptorService } from './services/auth-interceptor.service';
import { ProductService } from './services/product.service';
const oktaConfig = Object.assign({

  // let's create this new object 'onAuthRequired' so when a user tries to login to our application
  // and they haven't been authenticated with Okta, then we'll automatically route them to the login page
  onAuthRequired: (oktaAuth, injector) => {
    const router = injector.get(Router);

    // Redirect the user to your custom login page
    router.navigate(['/login']);
  }
})
*/
const routes: Routes = [
 // { path: 'login/callback', component: OktaCallbackComponent },
  //{ path: 'login', component: LoginComponent },
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
  { path: 'products/:product_id', component: ProductDetailsComponent },
  { path: 'products', component: ProductListComponent },
  {path: 'category/:subcat_id/:subcatname', component: ProductListComponent},
  { path: 'category', component: ProductListComponent },
  {path: 'search/:keyword', component: ProductListComponent},
  {path: 'cart-details', component: CartDetailsComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'cart-status', component: CartDetailsComponent},
   { path: 'create', component: ProductCreateComponent },
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  ];
  
@NgModule({
  declarations: [
    AppComponent,ProductListComponent, ProductDetailsComponent, ProductCreateComponent, CartStatusComponent, CartDetailsComponent, SearchComponent, ProductCategoryMenuComponent,RegisterComponent, CheckoutComponent,LoginComponent,RegisterComponent
  ],
  imports: [
    BrowserModule,FlexLayoutModule ,FormsModule,SlickCarouselModule,ReactiveFormsModule,FormsModule,MatSidenavModule,BrowserAnimationsModule,AngularMaterialModule,
    AppRoutingModule,AlertComponent,LoginComponent,RegisterComponent,HomeComponent,HttpClientModule,RouterModule.forRoot(routes)
  ],exports: [RouterModule],
  providers: [ProductService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },fakeBackendProvider],
  bootstrap: [AppComponent],
  //schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
