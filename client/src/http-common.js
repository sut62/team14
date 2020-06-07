import axios from "axios";

export default axios.create({
    baseURL: "http://172.17.0.201:9000/",
    headers: {
        'Access-Control-Allow-Origin': '*',
        "Content-type": "application/json",
    }
});
