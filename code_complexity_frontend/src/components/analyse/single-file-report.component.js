import React, {Component} from "react";
import {MDBTable, MDBTableBody, MDBTableHead} from 'mdbreact';
import axios from "axios";
import constants from "../../util/constants";
import swal from "sweetalert";


class AnalyseHome extends Component {

    constructor(props) {
        super(props);

        this.state = {
            fileId: '',
            file: '',
            results: [],
            total: '',
            totalCs: '',
            totalCtc: '',
            totalCnc: '',
            totalCi: '',
            totalTW: '',
            totalCps: '',
            totalCr: ''
        }
    }

    componentDidMount() {
        if (!localStorage.getItem('status')) {
            this.props.history.push("/login")
        }

        this.loadReport();
        this.setState({
            fileId: this.props.match.params.id
        })
    }


    loadReport() {
        axios.get(constants.url + "/controlStructure/analyse/" + this.props.match.params.id)
            .then(res => {
                console.log(res.data);
                this.setState({
                    results: res.data.list,
                    totalCs: res.data.totalCs,
                    totalCtc: res.data.totalCtc,
                    totalCnc: res.data.totalCnc,
                    totalCi: res.data.totalCi,
                    totalTW: res.data.totalTW,
                    totalCps: res.data.totalCps,
                    totalCr: res.data.totalCr
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
                    <h4>Analysis report for {this.state.file.fileName}</h4>
                    <MDBTable bordered>
                        <MDBTableHead>
                            <tr className="bg-dark text-light">
                                <th>Line #</th>
                                <th>Statement</th>
                                <th>Tokens under the size</th>
                                <th>Cs</th>
                                <th>Ctc</th>
                                <th>Cnc</th>
                                <th>Ci</th>
                                <th>TW</th>
                                <th>Cps</th>
                                <th>Cr</th>
                            </tr>
                        </MDBTableHead>
                        <MDBTableBody>
                            {this.state.results.map((result, index) => (
                                    <tr>
                                        <td>{index + 1}</td>
                                        <td>{result.statement}</td>
                                        <td>{result.tokensOnStatmentSize}</td>
                                        <td>{result.cs}</td>
                                        <td>{result.ctc}</td>
                                        <td>{result.cnc}</td>
                                        <td>{result.ci}</td>
                                        <td>{result.tw}</td>
                                        <td>{result.cps}</td>
                                        <td>{result.cr}</td>

                                    </tr>
                                )
                            )}
                            <tr className='bg-success'>
                                <td></td>
                                <td><h6>Cp</h6></td>
                                <td></td>
                                <td>{this.state.totalCs}</td>
                                <td>{this.state.totalCtc}</td>
                                <td>{this.state.totalCnc}</td>
                                <td>{this.state.totalCi}</td>
                                <td>{this.state.totalTW}</td>
                                <td>{this.state.totalCps}</td>
                                <td>{this.state.totalCr}</td>
                            </tr>
                        </MDBTableBody>
                    </MDBTable>
                </div>
            </>
        )
    }
}

export default AnalyseHome
