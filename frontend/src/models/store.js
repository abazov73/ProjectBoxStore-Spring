export default class Store {
    constructor(data) {
        this.id = data?.id;
        this.storeName = data?.storeName || '';
        this.products = data?.products || null;
    }
}