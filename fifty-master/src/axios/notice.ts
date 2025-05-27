import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080/"
})

function insertNotice(formData: any){
    return instance.post("/notice", formData);
}


export default{
  insertNotice
};