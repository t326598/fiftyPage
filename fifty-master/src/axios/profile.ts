import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080/"
})

function ListProfile(){
    return instance.get("/profile");
}

function ListPlan(){
  return instance.get("/plan");
}


// 공지사항

function ListNotice() {
  return instance.get("/notice");
}

function backgroundList(month: number) {
  return instance.get("/months", { params: { month } });
}

function MusicChart(){
  return instance.get("/chart/fifty-fifty")
}

export default{
  ListProfile,
  ListPlan,
  ListNotice,
  backgroundList,
  MusicChart
};