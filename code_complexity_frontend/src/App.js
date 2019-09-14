
import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";


import UserRegister from "./components/user_managment/register.component";
import UserLogin from "./components/user_managment/login.component";
import TopNavBar from "./components/include/topNavBar.component";
import Footer from "./components/include/footer.component";
import Home from "./components/home/home.component";
import Logout from "./components/user_managment/logout.component";
import FileReportComponent from './components/analyse/single-file-report.component'
import AssignTaskComponent from './components/analyse/assign-task.component'


import FileUploadComponent from './components/upload/file-upload.component'


function App() {
  return (
      <Router>
        <TopNavBar />
        <Route exact path="/" component={Home} />
        <Route path = "/upload" component = {FileUploadComponent} />
        <Route path="/login" component={UserLogin}/>
        <Route path="/register" component={UserRegister}/>
        <Route path="/logout" component={Logout} />
        <Route path = "/report/:id" component = {FileReportComponent} />
<<<<<<< HEAD
        <Footer/>
=======
        <Route path = "/assign/:id" component = {AssignTaskComponent} />
>>>>>>> f3596a96d4c7089380f3a21494e09991c9b7528f
      </Router>
  );
}

export default App;