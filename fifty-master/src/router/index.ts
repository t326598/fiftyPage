import InsertFiles from "@/components/admin/insertFiles.vue";
import HomePage from "@/components/member/HomePage.vue";
import MemberDetail from "@/components/MemberDetail.vue";
import NoticePage from "@/components/NoticePage.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
    { path: '/', name: 'HomePage', component:HomePage},
    { path: '/member', name: 'MemberDetail', component: MemberDetail },
    {path: '/admin', name:'insertFiles', component: InsertFiles},
    {path: '/notice', name:'NoticePage', component:NoticePage}
]

const router = createRouter({
    history: createWebHistory(),
    routes
  })
  
  export default router  