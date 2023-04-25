import { useRoutes, Outlet, BrowserRouter } from 'react-router-dom';
import Header from './components/common/Header';
import CustomerPage from './components/pages/customerPage';
import StorePage from './components/pages/storePage';
import ProductPage from './components/pages/productPage';
import OrderPage from './components/pages/orderPage';
import AddToStorePage from './components/pages/addToStorePage'
import './styleSite.css';

function Router(props) {
  return useRoutes(props.rootRoute);
}

export default function App() {
  const routes = [
    { index: true, element: <CustomerPage/> },
    { path: 'customer', element: <CustomerPage/>, label:'Покупатели'},
    { path: 'store', element: <StorePage/>, label: 'Магазины' },
    { path: 'product', element: <ProductPage/>, label: 'Товары' },
    { path: 'order', element: <OrderPage/>, label: 'Заказы'},
    { path: 'addToStore', element: <AddToStorePage/>, label: 'Доставка'}
  ];
  const links = routes.filter(route => route.hasOwnProperty('label'));
  const rootRoute = [
    { path: '/', element: render(links), children: routes }
  ];

  function render(links) {
    console.info('render links');
    return (
      <>
        <Header links={links} />
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