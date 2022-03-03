import React, {Component} from 'react';
import request from "../../utils/request";
import api from "../../utils/api";
import {TOKEN} from "../../utils/constant";
import {Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import {AvField, AvForm} from 'availity-reactstrap-validation'

class AdminCabinet extends Component {
    state = {
        companies: [],
        currentCompany: '',
        showModal: false,
        showDeleteModal: false,
    }

    componentDidMount() {
        if (localStorage.getItem(TOKEN)) {
            this.getCompany()
        } else {
            this.props.history.push("/login")
        }
    }

    getCompany = () => {
        request({
            url: api.getAllCompany,
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem(TOKEN)
            }
        }).then(res => {
            this.setState({companies: res.data.object})
        }).catch(err => {
        })
    }
    openModal = () => {
        this.setState({showModal: !this.state.showModal})
    }
    editCompany = (company) => {
        this.openModal()
        this.setState({
            currentCompany: company.id
        })
    }
    deleteCompany = (company) => {
        this.setState({
            showDeleteModal: !this.state.showDeleteModal,
            currentCompany: company
        })
    }
    deleteCompanyItem = () => {
        let current = this.state.currentCompany
        request({
            url: api.deleteCompany + '/' + current.id,
            method: 'DELETE'
        }).then(res => {
            this.deleteCompany();
            this.getCompany()
        })
    }
    addCompany = (e, v) => {
        let userDTO={
            firstName:"",
            lastName:"",
            middleName:"",
            passportSerialAndNumber:"",
            phoneNumber:"",
            password:""
        }
        userDTO.firstName=v.firstName
        userDTO.lastName=v.lastName
        userDTO.middleName=v.middleName
        userDTO.phoneNumber=v.phoneNumber
        userDTO.passportSerialAndNumber=v.passportSerialAndNumber
        userDTO.password=v.password
        v.userDTO=userDTO;
        let current=this.state.currentCompany
        request({
            url:current?(api.editCompany+'/'+current.id):api.addCompany,
            method:current?'PUT':'POST',
            data:v
        }).then(res=>{
            this.getCompany()
            this.openModal()
        }).catch(err=>{})

    }


    render() {
        const {companies, currentCompany} = this.state
        return (
            <div>
                <h1>Admin Cabinet</h1>
                <br/>
                <button className="btn btn-info"
                        onClick={this.openModal}>Kompaniya qo`shish
                </button>
                <br/>
                <table className="table table-bordered text-center">
                    <thead>
                    <th>№</th>
                    <th>Kompaniya nomi</th>
                    <th>Kompaniy rahbari FIO</th>
                    <th>Kompaniya telefon raqami</th>
                    <th>Kompaniya manzili</th>
                    <th>Kompaniya emaili</th>
                    <th>Kompaniya web sayti</th>
                    <th> Tahrirlash</th>
                    </thead>
                    <tbody>
                    {companies?.map((item, index) =>
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{item.name}</td>
                            <td>{item.leaderFullName.split("=", 3)[1].substring(0, item.leaderFullName.split("=", 3)[1].indexOf(",")) + ' '
                                + item.leaderFullName.split("=", 3)[2].substring(0, item.leaderFullName.split("=", 3)[2].indexOf(","))}
                            </td>
                            <td>{item.phoneNumber}</td>
                            <td>{item.address}</td>
                            <td>{item.email}</td>
                            <td><a href={item.webSite}>{item.webSite}</a></td>
                            <td>
                                <button className="btn btn-success"
                                        onClick={() => this.editCompany(item)}>edit
                                </button>
                                <button className="btn btn-danger"
                                        onClick={() => this.deleteCompany(item)}>delete
                                </button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>{currentCompany?"Kompaniyani taxrirlash":"Kompaniya qo`shish"}</ModalHeader>
                    <ModalBody>
                        <AvForm onValidSubmit={this.addCompany}>
                            <AvField
                                name="name"
                                placeholder="Kompaniya nomini kiriting"
                                defaultValue={this.state.currentCompany.name}
                            />
                            <AvField
                                name="address"
                                placeholder="Kompaniya manzilini kiriting"
                                defaultValue={this.state.currentCompany.address}
                            />
                            <AvField
                                name="email"
                                placeholder="Kompaniya emailini kiriting"
                                defaultValue={this.state.currentCompany.email}
                            />
                            <AvField
                                name="phoneNumber"
                                placeholder="Kompaniya telefon raqamini kiriting"
                                defaultValue={this.state.currentCompany.phoneNumber}
                            />
                            <AvField
                                name="webSite"
                                placeholder="Kompaniya web saytini kiriting"
                                defaultValue={this.state.currentCompany.webSite}
                            />
                            <AvField
                                name="firstName"
                                placeholder="Rahbarni ismini kiriting"/>
                            <AvField
                                name="lastName"
                                placeholder="Rahbarni familiyasini kiriting"/>
                            <AvField
                                name="middleName"
                                placeholder="Rahbarning otasini ismini kiriting"/>
                            <AvField
                                name="phoneNumber"
                                placeholder="Rahbarning telefon raqamini kiriting"/>
                            <AvField
                                name="password"
                                type="password"
                                placeholder="Parolni kiriting"/>
                            <AvField
                                name="passportSerialAndNumber"
                                placeholder="Rahbarning passport seriyasi va raqamini kiriting"/>

                            <button className="btn btn-success">saqlash</button>
                            <button className="btn btn-danger"
                                    onClick={this.openModal}>bekor qilish
                            </button>
                        </AvForm>
                    </ModalBody>
                </Modal>
                <Modal isOpen={this.state.showDeleteModal}>
                    <ModalHeader>{this.state.currentCompany.name + "ni o`chirishni xohlaysizmi"}</ModalHeader>
                    <ModalFooter>
                        <button className="btn btn-danger"
                                onClick={this.deleteCompanyItem}>ha
                        </button>
                        <button className="btn btn-success"
                                onClick={this.deleteCompany}>yo`q
                        </button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

AdminCabinet.propTypes = {};

export default AdminCabinet;
