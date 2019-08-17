import React,{Component} from 'react';

export default class Logout extends Component{
    componentDidMount(){
        localStorage.clear();
        window.location.href = '/';
    }
    render(){
        return(
            <div>Log Out</div>
        );
    }
}