import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

//Component import
import UserRegister from "./components/user_managment/register.component";
import UserLogin from "./components/user_managment/login.component";
import TopNavBar from "./components/include/topNavBar.component";
import BottomNavBar from "./components/include/bottomNavBar.component";
import Home from "./components/home/home.component";

function App() {
  return (
    <Router>
      <TopNavBar />
      <BottomNavBar />
      <Route exact path="/" component={Home} />
      <Route path="/login" component={UserLogin} />
      <Route path="/register" component={UserRegister} />
    </Router>
  );
}

export default App;
