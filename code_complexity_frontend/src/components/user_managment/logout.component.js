import React,{Component} from 'react';

export default class Logout extends Component{

    render(){
        localStorage.removeItem('id');
        localStorage.removeItem('name');
        localStorage.removeItem('email');
        localStorage.removeItem('status');

        let{history} = this.props;
        history.push({
            pathname:'/',
        });
        window.location.reload();
        return(
            <div></div>
        );
    }
}