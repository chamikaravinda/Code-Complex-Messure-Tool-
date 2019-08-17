import React, { Component } from "react";
import { MDBContainer, MDBFileInput, MDBBtn,MDBRow,MDBCol } from "mdbreact";
import swal from "sweetalert";
import axios from "axios";
import constants from "../../util/constants";
import FileUpload from "../upload/file-upload.component";

class AnalyseHome extends Component {
  constructor(props) {
    super(props);

    this.state = {
      fileId: ""
    };

    this.onClickCtc = this.onClickCtc.bind(this);
    this.onClickCs = this.onClickCs.bind(this);
  }

  componentDidMount() {
    if (!localStorage.getItem("status")) {
      this.props.history.push("/login");
    }
    this.setState({
      fileId: this.props.match.params.id
    });
  }

  onClickCtc() {
    axios
      .get(constants.url + "/controlStructure/analyse/" + this.state.fileId)
      .then(res => {
        swal("No of if statements in the code " + res.data);
      })
      .catch(err => {
        console.log(err);
      });
  }

  onClickCs() {
    axios
      .get(constants.url + "/statmentsize/analyse/" + this.state.fileId)
      .then(res => {
        swal("Complexity of a program statement due to size " + res.data);
        console.log("dss" + res.data);
      })
      .catch(err => {
        console.log(err);
      });
  }

  render() {
    return (
      <>
        <MDBContainer className="w-100 mt-5">
          <MDBRow>
            <MDBCol sm="4">
              <MDBBtn onClick={this.onClickCs}>
                Complexity due to size (Cs)
              </MDBBtn>
            </MDBCol>
            <MDBCol sm="4">
              <MDBBtn onClick={this.onClickCtc}>
                {" "}
                Complexity due to type of control structures (Ctc)
              </MDBBtn>
            </MDBCol>
            <MDBCol sm="4">
              {" "}
              <MDBBtn>
                Complexity due to nesting of control structures (Cnc){" "}
              </MDBBtn>
            </MDBCol>
          </MDBRow>
          <MDBRow>
            <MDBCol sm="4">
            <MDBBtn>Complexity due to inheritance (Ci) </MDBBtn>
            </MDBCol>
            <MDBCol sm="4">
              <MDBBtn onClick={this.onClickCtc}>
                {" "}
                Complexity due to type of control structures (Ctc)
              </MDBBtn>
            </MDBCol>
          </MDBRow>
        </MDBContainer>
      </>
    );
  }
}

export default AnalyseHome;
