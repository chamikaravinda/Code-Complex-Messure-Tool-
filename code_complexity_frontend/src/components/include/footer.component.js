import React, { Component } from "react";
import {
  MDBCol, MDBContainer, MDBRow, MDBFooter
} from "mdbreact";

export default class Footer extends Component {
  state = {
    isOpen: false
  };

  toggleCollapse = () => {
    this.setState({ isOpen: !this.state.isOpen });
  };
  render() {
    if (localStorage.getItem('status')) {
      return (
        <MDBFooter className="font-small primary-color-dark">
        {/*<MDBContainer fluid className="text-center text-md-left">*/}
        {/*  /!*<MDBRow>*!/*/}
        {/*  /!*  <MDBCol md="6">*!/*/}
        {/*  /!*    <h5 className="title">Links</h5>*!/*/}
        {/*  /!*    <ul>*!/*/}
        {/*  /!*      <li className="list-unstyled">*!/*/}
        {/*  /!*        <a href="#!">Help</a>*!/*/}
        {/*  /!*      </li>*!/*/}
        {/*  /!*    </ul>*!/*/}
        {/*  /!*  </MDBCol>*!/*/}
        {/*  /!*</MDBRow>*!/*/}
        {/*</MDBContainer>*/}
        {/*<div className="footer-copyright text-center py-3">*/}
        {/*</div>*/}
      </MDBFooter>
      );
    } else {
      return <div />;
    }
  }
}
