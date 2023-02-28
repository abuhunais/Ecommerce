import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from './layout/Navbar';
import Home from './Pages/Home';
import{BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddProd from './Products/AddProd';
import EditProd from './Products/EditProd';
import Search from './Products/Search';
import Category from './Products/Category';
import OrderList from './Products/OrderList';
import AddReview from'./Products/AddReview';
// import AddToCart from './Products/AddToCart';




function App() {

  
  return (
    <div className="App">
      <Router>
      <Navbar />

      



      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route exact path="/addproduct" element={<AddProd/>}/>
        <Route exact path="/addreview" element={<AddReview/>}/>
        {/* <Route exact path="/addcart/:productId" element={<AddToCart/>}/> */}
        <Route exact path="/searchproduct" element={<Search/>}/>
        <Route exactpath="/orders" element={OrderList} />
        {/* <Route path="/" exact component={() => <Home history={history} />} /> */}
        <Route exact path="/category" element={<Category/>}/>
        <Route exact path="/editproducts/:id" element ={<EditProd/>}/>
        <Route exact path="/order-list" element={<OrderList/>} />

      </Routes>
      
      

      </Router>

      

    </div>
  );
}

export default App;
