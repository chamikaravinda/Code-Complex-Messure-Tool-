import React, { Component } from "react";
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBCardHeader
} from "mdbreact";

import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export default class UserLogin extends Component {

  componentDidMount(){
    if(!localStorage.getItem('status')){
      this.props.history.push("/login")
    }
  }
  render() {
    return (
        <div>
          <h3>Welcome</h3>

        </div>
    );
  }
}
