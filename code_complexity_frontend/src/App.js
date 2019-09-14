
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
import FileReportComponent from './components/analyse/single-file-report.component'
import AssignTaskComponent from './components/analyse/assign-task.component'
import MyTasks from './components/analyse/my-tasks-component'


import FileUploadComponent from './components/upload/file-upload.component'


function App() {
  return (
      <Router>
        <TopNavBar />
        <BottomNavBar />
        <Route exact path="/" component={Home} />
        <Route path = "/upload" component = {FileUploadComponent} />
        <Route path="/login" component={UserLogin}/>
        <Route path="/register" component={UserRegister}/>
        <Route path="/logout" component={Logout} />
        <Route path = "/report/:id" component = {FileReportComponent} />
        <Route path = "/assign/:id" component = {AssignTaskComponent} />
        <Route path = "/myTasks" component = {MyTasks} />
      </Router>
  );
}

export default App;