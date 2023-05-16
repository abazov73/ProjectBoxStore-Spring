import { useRoutes, Outlet, BrowserRouter } from 'react-router-dom';
import { useState, useEffect } from 'react';
import Header from './components/common/Header';
import CustomerPage from './components/pages/customerPage';
import StorePage from './components/pages/storePage';
import ProductPage from './components/pages/productPage';
import OrderPage from './components/pages/orderPage';
import AddToStorePage from './components/pages/addToStorePage';
import LoginPage from './components/pages/loginPage';
import Logout from './components/pages/logout';
import ForbiddenPage from './components/pages/forbiddenPage'
import './styleSite.css';

function Router(props) {
  return useRoutes(props.rootRoute);
}

export default function App() {
  const routes = [
    { index: true, element: <StorePage/> },
    localStorage.getItem("role") === "ADMIN" && { path: 'customer', element: <CustomerPage/>, label:'Покупатели'},
    localStorage.getItem("role") !== "ADMIN" && { path: 'customer', element: <ForbiddenPage/>},
    { path: 'store', element: <StorePage/>, label: 'Магазины' },
    { path: 'product', element: <ProductPage/>, label: 'Товары' },
    { path: 'order', element: <OrderPage/>, label: 'Заказы'},
    localStorage.getItem("role") === "ADMIN" && { path: 'addToStore', element: <AddToStorePage/>, label: 'Доставка'},
    localStorage.getItem("role") !== "ADMIN" && { path: 'addToStore', element: <ForbiddenPage/>},
    { path: '/login', element: <LoginPage/>},
    { path: '/logout', element: <Logout/>}
  ];
  const links = routes.filter(route => route.hasOwnProperty('label'));
  
  const [token, setToken] = useState(localStorage.getItem('token'));

  useEffect(() => {
    
    function handleStorageChange() {
      setToken(localStorage.getItem('token'));
    }

    window.addEventListener('storage', handleStorageChange);

    return () => {
      window.removeEventListener('storage', handleStorageChange);
    };
  }, []);

  const rootRoute = [
    { path: '/', element: render(links), children: routes }
  ];

  function render(links) {
    console.info('render links');
    return (
      <>
        <Header token={token} links={links} />
        <div className="container-fluid">
          <Outlet />
        </div>
      </>
    );
  }

  return (
    <BrowserRouter>
      <Router rootRoute={ rootRoute } />
    </BrowserRouter>
  );
}