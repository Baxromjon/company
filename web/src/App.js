import React, {Component} from 'react';
import {ToastContainer} from "react-toastify";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import adminCabinet from "./pages/adminPage/adminCabinet";
import companyCabinet from "./pages/companyPage/companyCabinet";

class App extends Component {
    render() {
        return (
            <div className="container pt-3">
                <ToastContainer/>
                <Router>
                    <Switch>
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/adminCabinet" component={adminCabinet}/>
                        <Route exact path="/adminCompany" component={companyCabinet}/>
                    </Switch>
                </Router>
            </div>
        );
    }
}

App.propTypes = {};

export default App;
