import React, { Component } from "react";
import {MDBContainer, MDBFileInput,MDBBtn} from "mdbreact";
import swal from "sweetalert";
import axios from "axios";
import constants from "../../util/constants";
import FileUpload from "../upload/file-upload.component";

class AnalyseHome extends Component {

    constructor(props){
        super(props);

        this.state = {
            fileId : '',
        }

        this.onClickCtc = this.onClickCtc.bind(this);
    }

    componentDidMount() {
        if(!localStorage.getItem('status')){
            this.props.history.push("/login")
          }
        this.setState({
            fileId : this.props.match.params.id
        })
    }

    onClickCtc(){

        axios.get(constants.url + "/controlStructure/analyse/" + this.state.fileId )
            .then(res=>{
                swal("No of if statements in the code " + res.data);
            })
            .catch(err=>{
                console.log(err);
            })
    }

    render() {
        return(
            <>
                <MDBContainer className="w-25 mt-5">

                    <MDBBtn>Complexity due to size (Cs)</MDBBtn>
                    <MDBBtn onClick={this.onClickCtc}> Complexity due to type of control structures (Ctc)</MDBBtn>
                    <MDBBtn>Complexity due to nesting of control structures (Cnc) </MDBBtn>
                    <MDBBtn>Complexity due to inheritance (Ci) </MDBBtn>
                    <MDBBtn>Complexity due to recursion (Cr) </MDBBtn>
                </MDBContainer>
            </>
        )
    }
}

export default AnalyseHome