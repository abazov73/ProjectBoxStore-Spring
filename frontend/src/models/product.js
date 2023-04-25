export default class Product {
    constructor(data) {
        this.id = data?.id;
        this.productName = data?.productName || '';
        this.storeName = data?.storeName || '';
    }
}