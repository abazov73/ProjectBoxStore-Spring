export default class Customer {
    constructor(data) {
        this.id = data?.id;
        this.lastName = data?.lastName || '';
        this.firstName = data?.firstName || '';
        this.middleName = data?.middleName || '';
    }
}