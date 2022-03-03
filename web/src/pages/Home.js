import React, {Component} from 'react';

class Home extends Component {
    routeToLogin = () => {
        this.props.history.push("/login")
    }

    render() {
        return (
            <div>
                <h1>login page</h1>
                <div>
                    <button
                        className="btn btn-info"
                        onClick={this.routeToLogin}
                    >login
                    </button>
                </div>
            </div>
        );
    }
}

Home.propTypes = {};

export default Home;
