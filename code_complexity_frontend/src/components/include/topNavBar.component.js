import React, { Component } from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import {
  MDBNavbar,
  MDBNavbarBrand,
  MDBNavbarNav,
  MDBNavItem,
  MDBNavLink,
  MDBNavbarToggler,
  MDBCollapse,
  MDBDropdown,
  MDBDropdownToggle,
  MDBDropdownMenu,
  MDBDropdownItem,
  MDBIcon
} from "mdbreact";

export default class TopNavbar extends Component {
  state = {
    isOpen: false
  };

  toggleCollapse = () => {
    this.setState({ isOpen: !this.state.isOpen });
  };
  render() {
    if (localStorage.getItem('status')) {
      return (
        <MDBNavbar color="primary-color-dark" dark expand="md">
          <MDBNavbarBrand>
            <Link to ="/"><strong className="white-text">Code Complex Tool</strong></Link>
          </MDBNavbarBrand>
          <MDBNavbarToggler onClick={this.toggleCollapse} />
          <MDBCollapse id="navbarCollapse3" isOpen={this.state.isOpen} navbar>
            <MDBNavbarNav left>
            </MDBNavbarNav>
            <MDBNavbarNav right>
              <MDBNavItem>
                <MDBNavLink className="waves-effect waves-light" to="#!">
                  <MDBIcon fab icon="twitter" />
                </MDBNavLink>
              </MDBNavItem>
              <MDBNavItem>
                <MDBNavLink className="waves-effect waves-light" to="#!">
                  <MDBIcon fab icon="google-plus-g" />
                </MDBNavLink>
              </MDBNavItem>
              <MDBNavItem>
                <MDBDropdown>
                  <MDBDropdownToggle nav caret>
                    <MDBIcon icon="user" />{" "+localStorage.getItem('name')}
                  </MDBDropdownToggle>
                  <MDBDropdownMenu className="dropdown-default">
                    <MDBDropdownItem href="/logout">Logout</MDBDropdownItem>
                    <MDBDropdownItem href="#!">Another Action</MDBDropdownItem>
                    <MDBDropdownItem href="#!">
                      Something else here
                    </MDBDropdownItem>
                    <MDBDropdownItem href="#!">
                      Something else here
                    </MDBDropdownItem>
                  </MDBDropdownMenu>
                </MDBDropdown>
              </MDBNavItem>
            </MDBNavbarNav>
          </MDBCollapse>
        </MDBNavbar>
      );
    } else {
      return <div />;
    }
  }
}
