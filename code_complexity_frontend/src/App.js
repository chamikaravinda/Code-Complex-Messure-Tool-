import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router,Route,Link } from "react-router-dom";

//Component import
import UserRegister from './components/user_managment/register.component'
import UserLogin from './components/user_managment/login.component'
import Navbar from './components/include/navbar.component'
import Slidebar from './components/include/slidebar.component'
import Home from './components/home/home.component'


function App() {
  
  return (
    <Router>
      {localStorage.getItem("status") !== null ?
        <>

        </> 
        :
        <>
            <Route exact path="/" component={UserLogin}/>
            <Route path="/login" component={UserLogin}/>
            <Route path="/register" component={UserRegister}/>
        </> 
        }      
    </Router>
  );
}

export default App;
