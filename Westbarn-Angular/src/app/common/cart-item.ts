
import { Product } from "./product";

export class CartItem {
    id: string;
    name: string;
    imageUrl: string;
    unitPrice: number;
    quantity: number;
    constructor(product: Product) {
    this.name = product.productname;
    this.imageUrl = product.pic_address;
    this.unitPrice = product.price;
    this.quantity = 1;
    }
    }
    