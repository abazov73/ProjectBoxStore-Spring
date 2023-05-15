import Customer from "../../models/customer"
import CustomerTable from '../common/customerTable'
import { useState, useEffect} from "react";
import checkLogin from '../../checkLogin';

function CustomerPage(){
    const url = 'customer';
    const getUrl = 'customer/';
    const transformer = (data) => new Customer(data);
    const catalogCustomerHeaders = [
        { name: 'lastName', label: 'Фамилия' },
        {name: 'firstName', label: 'Имя'},
        {name: 'middleName', label: 'Отчество'}
    ];

    const [data, setData] = useState(new Customer());

    function handleOnAdd() {
        setData(new Customer());
    }

    function handleOnEdit(data) {
        setData(new Customer(data));
    }

    function handleFormChange(event) {
        setData({ ...data, [event.target.id]: event.target.value })
    }
    return(
        <article className="h-100 mt-0 mb-0 d-flex flex-column justify-content-between">
        <CustomerTable headers={catalogCustomerHeaders} 
            getAllUrl={url}
            url={url}
            getUrl={getUrl}
            transformer={transformer}
            data={data}
            onAdd={handleOnAdd}
            onEdit={handleOnEdit}>
        <div className="col-md-4">
            <label className="form-label" forhtml="lastName">Фамилия</label>
            <input className="form-control" type="text" id="lastName" value={data.lastName} onChange={handleFormChange} required="required"/>
          </div>
          <div className="col-md-4">
            <label className="form-label" forhtml="firstName">Имя</label> 
            <input className="form-control" type="text" value={data.firstName} onChange={handleFormChange} id="firstName" required="required"/>
          </div>
          <div className="col-md-4">
            <label className="form-label" forhtml="middleName">Отчество</label>
            <input className="form-control" type="text" id="middleName" value={data.middleName} onChange={handleFormChange} required="required"/>
          </div>
        </CustomerTable>
      </article>
    )
}

export default checkLogin(CustomerPage);