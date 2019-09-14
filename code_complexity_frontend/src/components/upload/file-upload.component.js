import React, { Component } from "react";
import { MDBContainer, MDBFileInput, MDBBtn } from "mdbreact";
import swal from "sweetalert";
import axios from "axios";
import constants from "../../util/constants";
import { MDBTable, MDBTableBody, MDBTableHead,MDBRow, MDBCol,MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";

const File = props => {
  return (
    <tr>
      <td>{props.index}</td>
      <td>{props.file.fileName}</td>
      <td>{props.file.uploadDate}</td>
      <td>{props.file.uploadTime}</td>
      <td>
        <div className="btn-group">
          <button
            type="button"
            onClick={props.fileDelete}
            className="btn btn-danger btn-sm"
          >
            {" "}<MDBIcon far icon="trash-alt" />
            {" "} Delete{" "}
          </button>
          <Link to={"/report/" +props.file._id} className="btn btn-primary btn-sm">
            {" "}<MDBIcon icon="chart-line" />
            {" "} Analyse{" "}
        </Link>
            <Link to={"/assign/" +props.file._id} className="btn btn-primary btn-sm">
                {" "}<MDBIcon icon="bug" style={{ color: '#FFF' }} />
                {" "} Assign{" "}
            </Link>
        </div>
      </td>
    </tr>
  );
};

class FileUpload extends Component {
  constructor(props) {
    super(props);

    this.state = {
      fileName: "",
      file: "",
      files: []
    };

    this.onChangeFile = this.onChangeFile.bind(this);
    this.onClickUpload = this.onClickUpload.bind(this);
  }

  componentDidMount() {
    if (!localStorage.getItem("status")) {
      this.props.history.push("/login");
    }
    this.loadAllFiles();
  }

  onChangeFile(e) {
    this.setState({
      file: e.target.files[0],
      fileName: e.target.files[0].name
    });
  }

  onClickUpload(e) {
    e.preventDefault();

    const formData = new FormData();

    formData.append("file", this.state.file);

    axios
      .post(constants.url + "/upload/file", formData)
      .then(res => {
        swal(
          "Success",
          "File " + res.data.fileName + " uploaded successfully " + "",
          "success"
        );
        this.loadAllFiles();

        this.setState({
          file: "",
          fileName: ""
        });
      })
      .catch(err => {
        console.log(err);
      });
  }

  fileList() {
    let count = 0;
    return this.state.files.map((currentFile, i) => {
      ++count;
      return <File file={currentFile} key={i} index={count} />;
    });
  }

  loadAllFiles() {
    axios
      .get(constants.url + "/file/all")
      .then(res => {
        this.setState({
          files: res.data
        });
      })
      .catch(err => {
        console.log(err);
      });
  }

  render() {
    return (
      <>
        <MDBContainer className="w-75 mt-5">
          <h3 className="text-left">Upload your file</h3>
          <MDBRow className="mt-3">
            <MDBCol md="8">
              <div className="input-group">
                <div className="input-group-prepend">
                  <span className="input-group-text" id="inputGroupFileAddon01">
                    File
                  </span>
                </div>
                <div className="custom-file">
                  <input
                    type="file"
                    className="custom-file-input"
                    id="inputGroupFile01"
                    aria-describedby="inputGroupFileAddon01"
                    onChange={this.onChangeFile}
                  />
                  {this.state.fileName === "" ? (
                    <label
                      className="custom-file-label"
                      htmlFor="inputGroupFile01"
                    >
                      Choose file
                    </label>
                  ) : (
                    <label
                      className="custom-file-label"
                      htmlFor="inputGroupFile01"
                    >
                      {this.state.fileName}
                    </label>
                  )}
                </div>
              </div>
            </MDBCol>
            <MDBCol md="4">
              <MDBBtn color="primary" onClick={this.onClickUpload} size="sm">
              <MDBIcon icon="upload" /> Upload
              </MDBBtn>
            </MDBCol>
          </MDBRow>
        </MDBContainer>
        <br></br>
        <div className="container border-bottom">
          <MDBTable bordered>
            <MDBTableHead>
              <tr className="bg-dark text-light">
                <th width="8%">Number</th>
                <th width="40%">Name</th>
                <th>Upload Date</th>
                <th>Upload Time</th>
                <th>Action</th>
              </tr>
            </MDBTableHead>
            <MDBTableBody>{this.fileList()}</MDBTableBody>
          </MDBTable>
        </div>
      </>
    );
  }
}

export default FileUpload;
