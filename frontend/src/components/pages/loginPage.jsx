import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate} from "react-router-dom";
import DataService from '../../services/DataService'

export default function LoginPage() {
  const [loginData, setLoginData] = useState({ login: '', password: '' });
  const hostURL = 'http://localhost:8080';
  const handleLoginSubmit = (e) => {
    e.preventDefault();
    
    login(loginData.login, loginData.password);
  };

  const login = async function (login, password) {
    console.log(localStorage.getItem('token'));
    let loginFlag = await DataService.login(hostURL + "/jwt/login", login, password).catch(() => {alert("Wrong login or password!");});
    if (loginFlag) {
        window.dispatchEvent(new Event("storage"));
        console.log("Успешный вход");
        window.location.replace("/store");
    }
  }


  return (
    <div className="container-fluid">
      <div className="row justify-content-center align-items-center vh-100">
        <div className="col-sm-6 col-md-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Авторизация</h5>
              <form onSubmit={handleLoginSubmit}>
                <div className="form-group mb-3">
                  <label htmlFor="login">Логин</label>
                  <input type="text" className="form-control" id="login" value={loginData.login} onChange={(e) => setLoginData({ ...loginData, login: e.target.value })} />
                </div>
                <div className="form-group mb-3">
                  <label htmlFor="loginPassword">Пароль</label>
                  <input type="password" className="form-control" id="loginPassword" value={loginData.password} onChange={(e) => setLoginData({ ...loginData, password: e.target.value })} />
                </div>
                <button type="submit" className="btn btn-primary">Вход</button>               
              </form>
            </div>
          </div>
        </div>      
      </div>
    </div>
  );
}