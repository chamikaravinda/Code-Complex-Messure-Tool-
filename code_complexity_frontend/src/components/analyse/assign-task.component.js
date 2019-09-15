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
import lengthCalculator from "../../util/stringlengthCalculator";
import validationConstant from "../../util/validationConstants";
import constants from "../../util/constants";

export default class AssignTask extends Component {
    constructor(props) {
        super(props);

        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeFile = this.onChangeFile.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            name: 'chamika@gmail.com',
            file: this.props.id,
            fileName :''
        };
    }
    onChangeFile(e) {
        this.setState({
            file: e.target.value
        })
    }
    onChangeName(e) {
        this.setState({
            name: e.target.value
        })
    }

    componentDidMount() {
        if (!localStorage.getItem("status")) {
            this.props.history.push("/upload");
        }
        this.loadAllFiles();
    }

    loadAllFiles() {
        axios
            .get(constants.url + "/file/single/"+this.props.match.params.id)
            .then(res => {
                this.setState({
                    fileName : res.data.fileName
                })
                console.log(res.data.fileName);

            })
            .catch(err => {
                console.log(err);
            });
    }

    onSubmit(e) {
        e.preventDefault(); // Stop form submit
        let task = {
            fileName: this.state.fileName,
            assigneeName: this.state.name,
        };
        const url = constants.url + "/task/assign/";
        axios.post(url, task).then(res => {
            console.log(res);
            this.props.history.push('/myTasks/');

        }).catch(err => {
            console.log(err);
        });

        this.state = {
            name: '',
            file: '',
        };

    }

    render() {
        return (
            <MDBContainer className="w-25 mt-5">
                <MDBCard className="d-flex justify-content-center align-self-center">
                    <MDBCardHeader className="text-center" color="primary-color" tag="h3">
                        Assign Task
                    </MDBCardHeader>
                    <MDBCardBody>
                        <form onSubmit={this.onSubmit}>
                            <label htmlFor="defaultFormRegisterNameEx" className="grey-text">
                                Assignee
                            </label>
                            <select className='form-control'>
                                <option> chamika@gmail.com </option>
                            </select>
                            <br />
                            <label htmlFor="defaultFormRegisterNameEx" className="grey-text">
                                Assigned File
                            </label>
                            <select className='form-control'>
                                <option>{this.state.fileName} </option>
                            </select>
                            <br />
                            <div className="text-center mt-4">
                                <button className=" btn btn-primary  btn-block " type="submit">
                                    <MDBIcon icon="user-plus" /> Assign
                                </button>
                            </div>
                        </form>
                        <br />
                    </MDBCardBody>
                </MDBCard>
            </MDBContainer>
        );
    }
}
