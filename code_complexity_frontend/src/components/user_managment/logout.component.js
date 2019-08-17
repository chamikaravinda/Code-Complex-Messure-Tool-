import React,{Component} from 'react';

export default class Logout extends Component{

    render(){
        sessionStorage.removeItem('isloged');
        sessionStorage.removeItem('id');
        sessionStorage.removeItem('regNo');
        sessionStorage.removeItem('fname');
        sessionStorage.removeItem('lname');
        sessionStorage.removeItem('role');
        sessionStorage.removeItem('email');
        sessionStorage.removeItem('password');

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