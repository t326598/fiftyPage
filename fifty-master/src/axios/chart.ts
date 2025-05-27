import axios from 'axios';

const instance = axios.create({
    baseURL: "http://localhost:8080/"
})


function getYoutubeChart(){
    return instance.get("/api/chart/youtube/today");
}


export default{
  getYoutubeChart
};