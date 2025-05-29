import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080/"
})

function ListProfile(){
    return instance.get("/profile");
}
function UpdateProfile(data : any){
    return instance.put("/profile", data);
}

function UpdateImage(data: FormData, config = {}) {
  return instance.post("/profile", data, config)
}

function ListPlan(){
  return instance.get("/plan");
}

function ListCalendar(){
  return instance.get("/months/list");
}


// 공지사항

function ListNotice() {
  return instance.get("/notice");
}

function backgroundList(month: number) {
  return instance.get("/months", { params: { month } });
}

function updateBackground(data : any){
    return instance.put("/months", data);
}

function MusicChart(){
  return instance.get("/chart/fifty-fifty")
}

export default{
  ListProfile,
  ListPlan,
  ListNotice,
  backgroundList,
  MusicChart,
  UpdateProfile,
  UpdateImage,
  ListCalendar,
  updateBackground
};