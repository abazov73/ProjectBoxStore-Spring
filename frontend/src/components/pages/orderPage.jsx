import Order from "../../models/order"
import Product from "../../models/product"
import Customer from "../../models/customer";
import OrderTable from '../common/orderTable'
import { useState, useEffect} from "react";

export default function OrderPage(){
    const url = 'order';
    const getUrl = 'order/';
    const getCustomerUrl = 'customer';
    const getProductUrl = 'product/getWithStores'
    const transformer = (data) => new Order(data);
    const transformerProduct = (data) => new Product(data);
    const transformerCustomer = (data) => new Customer(data); 
    const catalogOrderHeaders = [
        { name: 'customerFIO', label: 'ФИО покупателя' },
        { name: 'storeName', label: 'Магазина'},
        { name: 'productName', label: 'Товар'}
    ];

    const [data, setData] = useState(new Order());
    const [customerOptions, setCustomerOptions] = useState([])
    const [productOptions, setProductOptions] = useState([])

    function loadOptions(dataCustomer, dataProduct){
        const results1 = [];
        console.log(dataCustomer);
        //console.log(dataProduct);
        dataCustomer.forEach((value) => {
            results1.push({
                key: value.lastName + " " + value.firstName + " " + value.middleName,
                value: value.id,
            })
        })
        console.log(results1);
        setCustomerOptions(results1);
        const results2 = [];
        dataProduct.forEach((value) => {
            results2.push({
                key: value.productName,
                value: value.id,
            })
        })
        setProductOptions(results2);
        //console.log(customerOptions);
        //console.log(productOptions);
    }

    function handleOnAdd() {
        setData(new Order());
    }

    function handleOnEdit(data) {
        setData(new Order(data));
    }

    function handleFormChange(event) {
        setData({ ...data, [event.target.id]: event.target.value })
    }
    return(
        <article className="h-100 mt-0 mb-0 d-flex flex-column justify-content-between">
        <OrderTable headers={catalogOrderHeaders} 
            getAllUrl={url}
            url={url}
            getUrl={getUrl}
            getCustomerUrl={getCustomerUrl}
            getProductUrl={getProductUrl}
            transformer={transformer}
            transformerCustomer={transformerCustomer}
            transformerProduct={transformerProduct}
            data={data}
            onAdd={handleOnAdd}
            loadOptions={loadOptions}
            onEdit={handleOnEdit}>
        <div className="col-md-4">
            <label className="form-label" forhtml="customerId">Покупатель</label>
            <select className="form-select" id="customerId" value={data.customerId} onChange={handleFormChange} required>
                {
                    customerOptions.map((option) => {
                        return(
                            <option key={option.value} value={option.value}>
                                {option.key}
                            </option>
                        )
                    }
                    )
                }
            </select>
            <label className="form-label" forhtml="productId">Продукт</label>
            <select className="form-select" id="productId" value={data.customerId} onChange={handleFormChange} required>
                {
                    productOptions.map((option) => {
                        return(
                            <option key={option.value} value={option.value}>
                                {option.key}
                            </option>
                        )
                    }
                    )
                }
            </select>
            <label className="form-label" forhtml="quantity">Количество</label>
            <input className="form-control" type="number" id="quantity" value={data.quantity} onChange={handleFormChange} placeholder="1" step="1" min="1" required="required"/>
        </div>
        </OrderTable>
      </article>
    )
}