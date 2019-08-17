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
  MDBIcon,
  MDBCardHeader
} from "mdbreact";

import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import swal from "sweetalert";
import axios from "axios";

import validationConstant from "../../util/validationConstants";
import lengthCalculator from "../../util/stringlengthCalculator";
import constants from "../../util/constants";

export default class UserRegister extends Component {
  constructor(props) {
    super(props);

    this.onChangeName = this.onChangeName.bind(this);
    this.onChangEmail = this.onChangEmail.bind(this);
    this.onChanagePassword = this.onChanagePassword.bind(this);
    this.onChanageConfirmPassword = this.onChanageConfirmPassword.bind(this);

    this.isValid = this.isValid.bind(this);
    this.onSubmit = this.onSubmit.bind(this);

    this.state = {
      name: null,
      email: null,
      password: null,
      confirmpassword: null,

      isClicked: {
        name: false,
        email: false,
        password: false,
        confirmpassword: false
      },
      isError: {
        name: false,
        email: false,
        password: false,
        confirmpassword: false
      }
    };
  }

  /*onChange methods */
  componentDidMount(){
    if(localStorage.getItem("status")){
      this.props.history.push("/")
    }
  }

  onChangeName(e) {
    this.setState({
      name: e.target.value
    });

    let tempClicked = this.state.isClicked;
    tempClicked.name = true;
    this.setState({
      isClicked: tempClicked
    });

    if (
      lengthCalculator.lengthWithoutSpaces(e.target.value + " ") < 3 ||
      !validationConstant.textOnlyRegExp.test(e.target.value)
    ) {
      let tempError = this.state.isError;
      tempError.name = true;
      this.setState({
        isError: tempError
      });
    } else {
      let tempError = this.state.isError;
      tempError.name = false;
      this.setState({
        isError: tempError
      });
    }
  }

  onChangEmail(e) {
    this.setState({
      email: e.target.value
    });

    let tempClicked = this.state.isClicked;
    tempClicked.email = true;
    this.setState({
      isClicked: tempClicked
    });

    if (!validationConstant.emailRegExp.test(e.target.value)) {
      let tempError = this.state.isError;
      tempError.email = true;
      this.setState({
        isError: tempError
      });
    } else {
      let tempError = this.state.isError;
      tempError.email = false;
      this.setState({
        isError: tempError
      });
    }
  }

  onChanagePassword(e) {
    this.setState({
      password: e.target.value
    });

    let tempClicked = this.state.isClicked;
    tempClicked.password = true;
    this.setState({
      isClicked: tempClicked
    });

    if (lengthCalculator.lengthWithoutSpaces(e.target.value + " ") < 6) {
      let tempError = this.state.isError;
      tempError.password = true;
      this.setState({
        isError: tempError
      });
    } else {
      let tempError = this.state.isError;
      tempError.password = false;
      this.setState({
        isError: tempError
      });
    }
  }

  onChanageConfirmPassword(e) {
    this.setState({
      confirmpassword: e.target.value
    });

    let tempClicked = this.state.isClicked;
    tempClicked.confirmpassword = true;
    this.setState({
      isClicked: tempClicked
    });

    if (
      lengthCalculator.lengthWithoutSpaces(e.target.value + " ") < 6 ||
      e.target.value !== this.state.password
    ) {
      let tempError = this.state.isError;
      tempError.confirmpassword = true;
      this.setState({
        isError: tempError
      });
    } else {
      let tempError = this.state.isError;
      tempError.confirmpassword = false;
      this.setState({
        isError: tempError
      });
    }
  }

  onSubmit(e) {
    e.preventDefault();
    if (this.isValid()) {
      let newUser = {
        name: this.state.name,
        email: this.state.email,
        password: this.state.password
      };
      axios
        .post(constants.url + "/user/add", newUser)
        .then(res => {
          console.log(res.data);

          swal("Success", "User added sucessfully", "success");
        })
        .catch(err => {
          swal("Error", err.response.data.error + "", "error");
          console.log(err);
        });
    }
  }
  isValid() {
    let tempClicked = this.state.isClicked;
    tempClicked.name = true;
    tempClicked.email = true;
    tempClicked.password = true;
    tempClicked.confirmpassword = true;

    this.setState({
      isClicked: tempClicked
    });

    let tempError = this.state.isError;

    if (
      lengthCalculator.lengthWithoutSpaces(this.state.name + " ") < 3 ||
      !validationConstant.textOnlyRegExp.test(this.state.name)
    ) {
      tempError.name = true;
    }
    if (!validationConstant.emailRegExp.test(this.state.email)) {
      tempError.email = true;
    }
    if (lengthCalculator.lengthWithoutSpaces(this.state.password + " ") < 6) {
      tempError.password = true;
    }
    if (
      lengthCalculator.lengthWithoutSpaces(this.state.confirmpassword + " ") <
        6 ||
      this.state.confirmpassword !== this.state.password
    ) {
      tempError.confirmpassword = true;
    }

    if (
      lengthCalculator.lengthWithoutSpaces(this.state.name + " ") >= 3 &&
      validationConstant.textOnlyRegExp.test(this.state.name)
    ) {
      if (validationConstant.emailRegExp.test(this.state.email)) {
        if (
          lengthCalculator.lengthWithoutSpaces(this.state.password + " ") >= 6
        ) {
          if (
            lengthCalculator.lengthWithoutSpaces(
              this.state.confirmpassword + " "
            ) >= 6 &&
            this.state.confirmpassword === this.state.password
          ) {
            console.log("name correct")
            return true;
          }
        }
      }
    }

    return false;
  }

  render() {
    return (
      <MDBContainer className="w-25 mt-5">
        <MDBCard className="d-flex justify-content-center align-self-center">
          <MDBCardHeader className="text-center" color="primary-color" tag="h3">
            Sign Up
          </MDBCardHeader>
          <MDBCardBody>
            <form onSubmit={this.onSubmit}>
              <label htmlFor="defaultFormRegisterNameEx" className="grey-text">
                Your name
              </label>
              <input
                type="text"
                id="defaultFormRegisterNameEx"
                className={`form-control ${
                  !this.state.isClicked.name
                    ? null
                    : this.state.isError.name
                    ? "is-invalid"
                    : "is-valid"
                }`}
                onChange={this.onChangeName}
                value={this.state.name}
              />
              <br />
              <label htmlFor="defaultFormRegisterEmailEx" className="grey-text">
                Your email
              </label>
              <input
                type="email"
                id="defaultFormRegisterEmailEx"
                className={`form-control ${
                  !this.state.isClicked.email
                    ? null
                    : this.state.isError.email
                    ? "is-invalid"
                    : "is-valid"
                }`}
                onChange={this.onChangEmail}
                value={this.state.email}
              />
              <br />
              <label
                htmlFor="defaultFormRegisterPasswordEx"
                className="grey-text"
              >
                Your password
              </label>
              <input
                type="password"
                id="defaultFormRegisterPasswordEx"
                className={`form-control ${
                  !this.state.isClicked.password
                    ? null
                    : this.state.isError.password
                    ? "is-invalid"
                    : "is-valid"
                }`}
                onChange={this.onChanagePassword}
                value={this.state.password}
              />
              <br />
              <label
                htmlFor="defaultFormRegisterPasswordEx"
                className="grey-text"
              >
                Confirm Your password
              </label>
              <input
                type="password"
                id="defaultFormRegisterPasswordEx"
                className={`form-control ${
                  !this.state.isClicked.confirmpassword
                    ? null
                    : this.state.isError.confirmpassword
                    ? "is-invalid"
                    : "is-valid"
                }`}
                onChange={this.onChanageConfirmPassword}
                value={this.state.confirmpassword}
              />
              <div className="text-center mt-4">
                <button className=" btn btn-primary  btn-block " type="submit">
                  <MDBIcon icon="user-plus" /> Register
                </button>
              </div>
            </form>
            <br />
            <p class="text-center font-weight-light">
              Have an account ?<Link to="/login"> Login From Here</Link>
            </p>
          </MDBCardBody>
        </MDBCard>
      </MDBContainer>
    );
  }
}
