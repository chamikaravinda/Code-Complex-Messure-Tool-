import React, { Component } from "react";
import {MDBContainer, MDBFileInput,MDBBtn} from "mdbreact";
import swal from "sweetalert";
import axios from "axios";
import constants from "../../util/constants";
import FileUpload from "../upload/file-upload.component";
import {Link } from "react-router-dom";


class AnalyseHome extends Component {

    constructor(props){
        super(props);

        this.state = {
            fileId : '',
            file : ''
        }

        this.onClickCtc = this.onClickCtc.bind(this);

    }

    componentDidMount() {

        if(!localStorage.getItem('status')){
            this.props.history.push("/login")
          }
        this.loadFileDetails();

        this.setState({
            fileId : this.props.match.params.id
        })
    }

    loadFileDetails(){
        axios.get(constants.url + "/file/single/" + this.props.match.params.id )
            .then(res=>{
                this.setState({
                    file : res.data
                })
            })
            .catch(err=>{
                console.log(err);
            })

    }


    onClickCtc(){

        axios.get(constants.url + "/controlStructure/total/ctc/" + this.state.fileId )
            .then(res=>{
                swal("Total complexity of " + this.state.file.fileName + " due to type of control structures (Ctc) : " + res.data);
                console.log(res.data);
            })
            .catch(err=>{
                console.log(err);
            })
    }

    render() {
        return (
            <>
                <MDBContainer className="w-25 mt-5">
                    <h4>Measuring the complexity of file {this.state.file.fileName}</h4>
                    <MDBBtn>Complexity due to size (Cs)</MDBBtn>
                    <MDBBtn onClick={this.onClickCtc}> Complexity due to type of control structures (Ctc)</MDBBtn>
                    <MDBBtn>Complexity due to nesting of control structures (Cnc) </MDBBtn>
                    <MDBBtn>Complexity due to inheritance (Ci) </MDBBtn>
                    <MDBBtn>Complexity due to recursion (Cr) </MDBBtn>
                    <Link to={'/report/' + this.state.fileId} className='btn btn-black'> File Analysis Report </Link>
                </MDBContainer>
            </>
        )
    }
}

export default AnalyseHome;