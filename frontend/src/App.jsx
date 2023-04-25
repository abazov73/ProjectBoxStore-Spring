import { useRoutes, Outlet, BrowserRouter } from 'react-router-dom';
import Header from './components/common/Header';
import Footer from './components/common/Footer';
import CustomerPage from './components/pages/customerPage'
import './styleSite.css';

function Router(props) {
  return useRoutes(props.rootRoute);
}

export default function App() {
  const routes = [
    { index: true, element: <CustomerPage/> },
    { path: 'customer', element: <CustomerPage/>, label:'Покупатели'},
    // { path: 'shop', element: <Shop/>, label: 'Магазины' },
    // { path: 'product', element: <Product/>, label: 'Товары'},
    // { path: 'order', element: <Order/>, label: 'Заказы'}
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
        <Footer/>
      </>
    );
  }

  return (
    <BrowserRouter>
      <Router rootRoute={ rootRoute } />
    </BrowserRouter>
  );
}