import axios from 'axios';

const instance = axios.create({
    baseURL: "http://localhost:8080/"
})


function getNoticeList(){
    return instance.get("/notice");
}

function deleteNotice(no: number) {
    return instance.delete(`/notice/${no}`);
}

function updateNotice( data:any) {
    return instance.put("/notice", data)
    
}

function getInsertPlan(data : any){
    return instance.post("/plan", data)
}

function getUpdatePlan(data:any) {
    return instance.put("/plan", data)
}
function getDeletePlan(no:number) {
    return instance.delete(`/plan/${no}`)
    
}

export default{
  getNoticeList,
  getInsertPlan,
  updateNotice,
  deleteNotice,
  getUpdatePlan,
  getDeletePlan
};