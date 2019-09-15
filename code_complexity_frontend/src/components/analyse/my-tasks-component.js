import {MDBTable, MDBTableBody, MDBTableHead} from "mdbreact";
import React, {Component} from "react";
import axios from "axios";
import constants from "../../util/constants";

export default class AnalyseHome extends Component {

    constructor(props) {
        super(props);

        this.state = {
            results: [],
            param: "chamika@gmail.com"
        }
    }

    componentDidMount() {
        if (!localStorage.getItem('status')) {
            this.props.history.push("/login")
        }
        axios.get(constants.url + "/task/myall/" + this.state.param)
            .then(res => {
                console.log(res.data);
                this.setState({
                    results: res.data,

                })
            })
            .catch(err => {
                console.log(err);
            })

    }

    render() {
        return (
            <>
                <div className="container border-bottom">
                    <br/>  <br/>
                    <h4>My Tasks</h4>
                    <br/>  <br/>
                    <MDBTable bordered>
                        <MDBTableHead>
                            <tr className="bg-dark text-light">
                                <th>Tasks</th>
                                </tr>
                        </MDBTableHead>
                        <MDBTableBody>
                            {this.state.results.map((result, index) => (
                                    <tr>
                                        <td>{result.fileName}</td>
                                    </tr>
                                )
                            )}

                        </MDBTableBody>
                    </MDBTable>
                </div>
            </>
        )
    }
}