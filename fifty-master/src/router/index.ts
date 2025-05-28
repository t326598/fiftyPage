import AdminHeader from "@/components/admin/adminHeader.vue";
import InsertFiles from "@/components/admin/insertFiles.vue";
import InsertNotice from "@/components/admin/insertNotice.vue";
import InsertPlan from "@/components/admin/insertPlan.vue";
import NoticeList from "@/components/admin/noticeList.vue";
import HomePage from "@/components/member/HomePage.vue";
import MemberDetail from "@/components/MemberDetail.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
    { path: '/', name: 'HomePage', component:HomePage},
    { path: '/member', name: 'MemberDetail', component: MemberDetail },
    {path: '/admin', name:'insertFiles', component: InsertFiles},
    {path: '/admin/notice', name:'insertNotice', component:InsertNotice},
    {path: '/admin/plan', name:'insertPlan', component:InsertPlan},
    {path: '/admin/header', name:'adminHeader', component:AdminHeader},
    {path: '/admin/noticeList', name:'noticeList', component:NoticeList},
]

const router = createRouter({
    history: createWebHistory(),
    routes
  })
  
  export default router  