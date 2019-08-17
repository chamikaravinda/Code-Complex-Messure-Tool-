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
            <form>
              <label htmlFor="defaultFormLoginEmailEx" className="grey-text">
                Your email
              </label>
              <input
                type="email"
                id="defaultFormLoginEmailEx"
                className="form-control"
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
