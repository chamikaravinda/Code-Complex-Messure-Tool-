import React, {Component} from "react";
import { MDBTable, MDBTableBody, MDBTableHead } from 'mdbreact';
import axios from "axios";
import constants from "../../util/constants";
import swal from "sweetalert";



class AnalyseHome extends Component {

    constructor(props){
        super(props);

        this.state = {
            fileId : '',
            file : '',
            results : [],
            total : ''
        }
    }

    loadTotal(){

        axios.get(constants.url + "/controlStructure/total/ctc/" + this.props.match.params.id )
            .then(res=>{
                this.setState({
                    total : res.data
                })
            })
            .catch(err=>{
                console.log(err);
            })
    }

    componentDidMount() {
        if(!localStorage.getItem('status')){
            this.props.history.push("/login")
        }
        this.loadFileDetails();
        this.loadReport();
        this.loadTotal();
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

    loadReport(){
        axios.get(constants.url + "/controlStructure/analyse/" + this.props.match.params.id )
            .then(res=>{
                this.setState({
                    results : res.data
                })
                console.log(res.data);
            })
            .catch(err=>{
                console.log(err);
            })
    }


    render() {
        return(
           <>
               <div className="container border-bottom">
                   <h4>Analysis report for {this.state.file.fileName}</h4>
                   <MDBTable bordered>
                       <MDBTableHead>
                           <tr className = "bg-dark text-light">
                               <th>Line #</th>
                               <th>Statement</th>
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
                           {this.state.results.map((result,index)=>(
                               <tr>
                                   <td>{index+1}</td>
                                   <td>{result.statement}</td>
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
                               <td></td>
                               <td></td>
                               <td></td>
                               <td></td>
                               <td></td>
                               <td> <h6>{this.state.total}</h6></td>
                           </tr>
                       </MDBTableBody>
                   </MDBTable>
               </div>
           </>
        )
    }
}

export default AnalyseHome
