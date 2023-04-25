export default class Store {
    constructor(data) {
        this.id = data?.id;
        this.storeName = data?.storeName || '';
        this.customerFIO = data?.customerFIO || '';
        this.productName = data?.productName || '';
    }
}