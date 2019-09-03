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

    componentDidMount() {
        if(!localStorage.getItem('status')){
            this.props.history.push("/login")
        }

        this.loadReport();
        this.setState({
            fileId : this.props.match.params.id
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
                               <td> <h6>total</h6></td>
                           </tr>
                       </MDBTableBody>
                   </MDBTable>
               </div>
           </>
        )
    }
}

export default AnalyseHome
