import React, {Component} from 'react';
import PropTypes from 'prop-types';
import request from "../../utils/request";
import api from "../../utils/api";
import {TOKEN} from "../../utils/constant";
import {Modal, ModalHeader, ModalBody, ModalFooter} from "reactstrap";
import {AvField, AvForm} from 'availity-reactstrap-validation'


class CompanyCabinet extends Component {
    state = {
        users: [],
        currentUser: '',
        showModal: false,
        showDeleteModal: false
    }
    getAllUsers = () => {
        request({
            url: api.getAllUser,
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem(TOKEN)
            }
        }).then(res => {
            this.setState({users: res.data})
        })
    }

    componentDidMount() {
        if (localStorage.getItem(TOKEN)) {
            this.getAllUsers()
        } else {
            this.props.history.push("/login")
        }
    }

    openModal = () => {
        this.setState({showModal: !this.state.showModal})
    }
    addUser = (e, v) => {
        let current = this.state.currentUser.id
        request({
            url: !current ? api.addUser : api.editUser + '/' + current,
            method: !current ? 'POST' : 'PUT',
            data: v,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem(TOKEN)}
        }).then(res => {
            this.setState({users: res.data})
        }).catch(err => {
        })
    }
    editUser = (user) => {
        this.openModal();
        this.setState({currentUser: user})
    }
    deleteModal=(user)=>{
       this.setState({
           showDeleteModal:true,
           currentUser:user
       })
    }
    hideDeleteModal = () => {
        this.setState({
            showDeleteModal: false,
            currentUser: '',
        })
    }
    deleteUser=()=>{
        let current=this.state.currentUser.id
        request({
            url:api.deleteUser+'/'+current,
            method:'DELETE'
        }).then(res=>{
            this.hideDeleteModal();
            this.getAllUsers();
        }).catch(err=>{})
    }

    render() {
        const {users, currentUser} = this.state
        return (
            <div>
                <h1>Company Cabinet</h1>
                <br/>
                <button className="btn btn-success"
                        onClick={this.openModal}>Xodim qo`shish
                </button>
                <br/>
                <table className="table table-bordered text-center">
                    <thead>
                    <th>№</th>
                    <th>Ismi</th>
                    <th>Familiyasi</th>
                    <th>Otasining ismi</th>
                    <th>Passport seriyasi va raqami</th>
                    <th>Telefon raqami</th>
                    <th>Lavozimi</th>
                    <th>Tahrirlash</th>
                    </thead>
                    <tbody>
                    {users?.map((item, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{item.firstName}</td>
                            <td>{item.lastName}</td>
                            <td>{item.middleName}</td>
                            <td>{item.passportSerialAndNumber}</td>
                            <td>{item.phoneNumber}</td>
                            <td>{item.position}</td>
                            <td>
                                <button className="btn btn-primary"
                                        onClick={() => this.editUser(item)}>edit
                                </button>
                                <button className="btn btn-danger"
                                onClick={()=>this.deleteModal(item)}>delete</button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>

                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>{this.state.currentUser ? 'Xodimni taxrirlash' : 'Xodim qo`shish'}</ModalHeader>
                    <ModalBody>
                        <AvForm onValidSubmit={this.addUser}>
                            <AvField
                                placeholder="Xodimning ismini kiriting"
                                defaultValue={this.state.currentUser.firstName}
                                name="firstName"/>
                            <AvField
                                placeholder="Xodimning familiyasini kiriting"
                                defaultValue={this.state.currentUser.lastName}
                                name="lastName"/>
                            <AvField
                                placeholder="Xodimning Otasining ismini kiriting"
                                defaultValue={this.state.currentUser.middleName}
                                name="middleName"/>
                            <AvField
                                placeholder="Xodimning telefon raqamini kiriting"
                                defaultValue={this.state.currentUser.phoneNumber}
                                name="phoneNumber"/>
                            <AvField
                                placeholder="Xodimning lavozimini kiriting"
                                defaultValue={this.state.currentUser.position}
                                name="position"/>
                            <AvField
                                placeholder="Xodimning passport seriyasi va raqamini kiriting"
                                defaultValue={this.state.currentUser.passportSerialAndNumber}
                                name="passportSerialAndNumber"/>
                            <button className="btn btn-success">saqlash</button>
                            <button className="btn btn-danger"
                                    onClick={this.openModal}>bekor qilish
                            </button>
                        </AvForm>
                    </ModalBody>
                </Modal>
                <Modal isOpen={this.state.showDeleteModal}>
                    <ModalHeader>{this.state.currentUser.firstName+' '+this.state.currentUser.lastName+ "ni o`chirishni xohlaysizmi?"}</ModalHeader>
                    <ModalFooter>
                        <button className="btn btn-success"
                        onClick={this.hideDeleteModal}>Yo`q</button>
                        <button className="btn btn-danger"
                        onClick={this.deleteUser}>Ha</button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

CompanyCabinet.propTypes = {};

export default CompanyCabinet;
