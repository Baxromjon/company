import {BASEURL, TOKEN} from "./constant";
import axios from 'axios'

export default (params) => {
    let url = BASEURL + params.url;
    let method = params.method;
    let data = params.data;

    return axios({
        url,
        method,
        data,
        // headers: {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem(TOKEN)}
        // }
    })
}
