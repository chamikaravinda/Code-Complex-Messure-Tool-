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
import swal from "sweetalert";
import axios from "axios";
import constants from "../../util/constants";


export default class UserLogin extends Component {

  constructor(props){
    super(props);

    this.onChangeEmail= this.onChangeEmail.bind(this);
    this.onChanagePassword = this.onChanagePassword.bind(this);
    this.onSubmit = this.onSubmit.bind(this);

    this.state={
      email:null,
      password:null
    }
  }

  componentDidMount(){
    if(localStorage.getItem("status")){
      this.props.history.push("/")
    }
  }

  onChangeEmail(e){
    this.setState({
      email: e.target.value
    });
  }

  onChanagePassword(e){
    this.setState({
      password: e.target.value
    });
  }

  onSubmit(e){
    e.preventDefault();
    if(this.state.email==null || this.state.password==null){
      swal("Error","Enter valid email and passowrd","error");
    }else{
      let user={
        email:this.state.email,
        password:this.state.password
      };

      axios
      .post(constants.url + "/user/login", user)
      .then(res => {
        console.log(res.data.email);
        localStorage.setItem('id',res.data._id);
        localStorage.setItem('name',res.data.name);
        localStorage.setItem('email',res.data.email);
        localStorage.setItem('status',true);

        this.props.history.push("/");
        window.location.reload();

      })
      .catch(err => {
        swal("Error", err.response.data.error + "", "error");
        console.log(err);
      });
    }
    
  }
  render() {
    return (
      <MDBContainer className="w-25 mt-5">
        <br />
        <br />
        <br />
        <MDBCard className="d-flex justify-content-center align-self-center">
          <MDBCardHeader className="text-center" color="primary-color" tag="h3">
            Sign in
          </MDBCardHeader>
          <MDBCardBody>
            <form onSubmit={this.onSubmit}>
              <label htmlFor="defaultFormLoginEmailEx" className="grey-text">
                Your email
              </label>
              <input
                type="email"
                id="defaultFormLoginEmailEx"
                className="form-control"
                onChange={this.onChangeEmail}
                value={this.state.email}
              />

              <br />
              <label htmlFor="defaultFormLoginPasswordEx" className="grey-text">
                {" "}
                Your password{" "}
              </label>
              <input
                type="password"
                id="defaultFormLoginPasswordEx"
                className="form-control"
                onChange={this.onChanagePassword}
                value={this.state.password}
              />

              <div className="text-center mt-4">
                <button className=" btn btn-primary  btn-block " type="submit">
                  <i class="fas fa-sign-in-alt" /> Login
                </button>
              </div>
            </form>
            <br />

            <p class="text-center font-weight-light">
              Don't have a account ?
              <Link to="/register"> Register From Here</Link>
            </p>
          </MDBCardBody>
        </MDBCard>
      </MDBContainer>
    );
  }
}
