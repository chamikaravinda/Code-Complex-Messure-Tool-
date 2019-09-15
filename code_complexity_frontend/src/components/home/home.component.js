import React, { Component } from "react";
import {
  MDBMask,MDBBtn,MDBIcon,MDBNavLink
} from "mdbreact";

import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Background from '../include/images/cct_background.jpg';
import "../include/style.css";

export default class UserLogin extends Component {

  componentDidMount(){
    if(!localStorage.getItem('status')){
      this.props.history.push("/login")
    }
  }
  render() {
    return (

     <div >
       <div className="bg">
          <div className="col-md-6">
            <MDBMask  className="flex-center flex-column text-white text-center">
              <span className="space-5"></span>
              <h2 className="h1 display-3">Welcome,to CCT</h2>
              <br/>
              <h5 className="col-md-10">CCT is a free open source tool that analyse the complexity of your source code 
                right away , without any extra setup. It also store code files for future mesurments detection.</h5>
              <br />
              <MDBBtn outline color="white" className="mb-5"  href="/upload"><MDBIcon icon="clone" className="mr-2"></MDBIcon>Start</MDBBtn>
            </MDBMask>
          </div>
       </div>
     </div>         
    );
  }
}
