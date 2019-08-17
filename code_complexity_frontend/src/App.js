import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";


import UserRegister from "./components/user_managment/register.component";
import UserLogin from "./components/user_managment/login.component";
import TopNavBar from "./components/include/topNavBar.component";
import BottomNavBar from "./components/include/bottomNavBar.component";
import Home from "./components/home/home.component";
import Logout from "./components/user_managment/logout.component";


import FileUploadComponent from './components/upload/file-upload.component'
import FileAnalyseComponent from './components/analyse/analyse-home.component'


function App() {
  return (
    <Router>
            <TopNavBar />
            <BottomNavBar />
            <Route exact path="/" component={Home} />
            <Route path = "/upload" component = {FileUploadComponent} />
            <Route path = "/analyse/:id" component = {FileAnalyseComponent} />
            <Route path="/login" component={UserLogin}/>
            <Route path="/register" component={UserRegister}/>
            <Route path="/logout" component={Logout} />
    </Router>
  );
}

export default App;
