import axios from "axios";

export interface FileItem {
  no: number;
  crt: number;
  path: string;
  name: string;
  size: number;
  trueDay: number;
  createdAt: string;
}

export interface FileParams {
  crt?: string;
  page?: number;
  rows?: number;
}

const instance = axios.create({
    baseURL: "http://localhost:8080/"
})

function insertFile(formData: any){
    return instance.post("/files", formData);
}

function fetchFiles(params: FileParams) {
  return instance.get("/files",{params})
}


export default{
  insertFile,
  fetchFiles
};