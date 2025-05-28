import axios from 'axios';

const instance = axios.create({
    baseURL: "http://localhost:8080/"
})


function getNoticeList(){
    return instance.get("/notice");
}

function getInsertPlan(data : any){
    return instance.post("/plan", data)
}

export default{
  getNoticeList,
  getInsertPlan
};