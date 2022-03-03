import React, {Component} from 'react';
import {TOKEN} from "../utils/constant";
import request from "../utils/request";
import api from "../utils/api";
import {AvField, AvForm} from 'availity-reactstrap-validation'


class Login extends Component {

    state = {
        reCaptcha: '',
        smsSent: false,
        data: [],
        confirmationResult: '',
        token: ''
    }

    componentDidMount() {
        if (localStorage.getItem(this.state.token)) {
        } else {

        }
    }

    login = (e, v) => {
        request({
            url: api.loginUrl,
            method: 'POST',
            data: v
        }).then(res => {
            if (res.status === 200) {
                this.setState({token: 'Bearer ' + res.data.object})
                localStorage.setItem(
                    TOKEN, this.state.token
                )
                request({
                    url: api.me,
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + res.data.object
                    }
                }).then(res => {
                    if (res.data.object.systemRoles[0].systemRoleEnum === 'ROLE_COMPANY') {
                        this.props.history.push('/adminCompany')
                    } else if (res.data.object.systemRoles[0].systemRoleEnum === 'ROLE_ADMIN') {
                        this.props.history.push('/adminCabinet')
                    }
                })
            } else {
                alert("Parol yoki Login xato! Iltimos qayta urinib ko`ring!")
            }
        }).catch(err => {
            alert("xatolik")
        })
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Login</h1>
                <AvForm onValidSubmit={this.login}>
                    <AvField
                        placeholder="enter phone number"
                        validate={{required: {value: true, errorMessage: "Please enter phoneNumber"}}}
                        name="phoneNumber"
                    />
                    <AvField
                        placeholder="enter password"
                        validate={{required: {value: true, errorMessage: "Please enter password"}}}
                        type="password"
                        name="password"
                    />
                    <button className="btn btn-success m-1"
                    >kirish
                    </button>
                </AvForm>

            </div>
        );
    }


}


export default Login;
