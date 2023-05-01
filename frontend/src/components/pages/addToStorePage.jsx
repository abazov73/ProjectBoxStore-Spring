import Product from "../../models/product"
import Store from "../../models/store"
import DataService from '../../services/DataService';
import { useState, useEffect} from "react";

export default function AddToStorePage(){
    const getStoreUrl = 'store';
    const getProductUrl = 'product/getWithoutStores'
    const url = 'store/'
    const [storeOptions, setStoreOptions] = useState([])
    const [productOptions, setProductOptions] = useState([])
    const transformerProduct = (data) => new Product(data);
    const transformerStore = (data) => new Store(data);
    useEffect(() => {
        loadOptions();
    }, []);

    async function loadOptions(){
        loadSelOptions(await DataService.readAll(getStoreUrl, transformerStore), await DataService.readAll(getProductUrl, transformerProduct)); 
    }

    function loadSelOptions(dataStore, dataProduct){
        const results1 = [];
        //console.log(dataProduct);
        dataStore.forEach((value) => {
            results1.push({
                key: value.storeName,
                value: value.id,
            })
        })
        console.log(results1);
        setStoreOptions(results1);
        const results2 = [];
        console.log(dataProduct);
        dataProduct.forEach((value) => {
            results2.push({
                key: value.productName,
                value: value.id,
            })
        })
        setProductOptions(results2);
    }

    function add(){
        var storeId = document.getElementById("storeId").value;
        var productId = document.getElementById("productId").value;
        let product = {
            id: productId
        }
        DataService.update(url + storeId + "/add", product);
        window.location.replace("/product");
    }

    return(
    <>
        <div className="col-md-4">
        <label className="form-label" forhtml="storeId">Магазин</label>
            <select className="form-select" id="storeId" required>
                {
                    storeOptions.map((option) => {
                        return(
                            <option key={option.value} value={option.value}>
                                {option.key}
                            </option>
                        )
                    }
                    )
                }
            </select>
            <label className="form-label" forhtml="productId">Товар</label>
            <select className="form-select" id="productId" required>
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
            <button className={`btn btn-success`} onClick={add}>
                Добавить в магазин
            </button>
        </div>
    </>
    );
}