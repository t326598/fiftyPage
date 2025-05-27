import InsertFiles from "@/components/admin/insertFiles.vue";
import InsertNotice from "@/components/admin/insertNotice.vue";
import InsertPlan from "@/components/admin/insertPlan.vue";
import HomePage from "@/components/member/HomePage.vue";
import MemberDetail from "@/components/MemberDetail.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
    { path: '/', name: 'HomePage', component:HomePage},
    { path: '/member', name: 'MemberDetail', component: MemberDetail },
    {path: '/admin', name:'insertFiles', component: InsertFiles},
    {path: '/admin/notice', name:'insertNotice', component:InsertNotice},
    {path: '/admin/plan', name:'insertPlan', component:InsertPlan}
]

const router = createRouter({
    history: createWebHistory(),
    routes
  })
  
  export default router  