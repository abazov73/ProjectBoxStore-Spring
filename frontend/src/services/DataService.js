import axios from 'axios';

function toJSON(data) {
    const jsonObj = {};
    const fields = Object.getOwnPropertyNames(data);
    for (const field of fields) {
        if (data[field] === undefined) {
            continue;
        }
        jsonObj[field] = data[field];
    }
    return jsonObj;
}

const getTokenForHeader = function () {
    return "Bearer " + localStorage.getItem("token");
}

export default class DataService {
    static dataUrlPrefix = 'http://localhost:8080/api/';

    static async readAll(url, transformer) {
        const response = await axios.get(this.dataUrlPrefix + url, {headers: {"Authorization": getTokenForHeader()}});
        console.log(response);
        return response.data.map(item => transformer(item));
    }

    static async read(url, transformer) {
        const response = await axios.get(this.dataUrlPrefix + url, {headers: {"Authorization": getTokenForHeader()}});
        return transformer(response.data);
    }

    static async create(url, data) {
        const response = await axios.post(this.dataUrlPrefix + url, data, {headers: {"Authorization": getTokenForHeader()}});
        return true;
    }

    static async update(url, data) {
        const response = await axios.put(this.dataUrlPrefix + url, data, {headers: {"Authorization": getTokenForHeader()}});
        return true;
    }

    static async delete(url) {
        const response = await axios.delete(this.dataUrlPrefix + url, {headers: {"Authorization": getTokenForHeader()}});
        return response.data.id;
    }

    static async login(url, login, password){
        const response = await axios.post(url, toJSON({login: login, password: password}))
        console.log("status " + response.status);
        if (response.status === 200) {
            const result = response.data;
            console.log(result);
            localStorage.setItem("token", result);
            localStorage.setItem("user", login);
            let jwtData = result.split('.')[1]
            let decodedJwtJsonData = window.atob(jwtData);
            let decodedJwtData = JSON.parse(decodedJwtJsonData);
            
            let role = decodedJwtData.role;
            localStorage.setItem("role", role.toUpperCase());
            return true;
        } else {
            console.error("BAD USER!!!!!");
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            localStorage.removeItem("role");
            return false;
        }
    }
}