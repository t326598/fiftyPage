<template>
  <div class="godDoUser">
      <Header />
      <div class="container">
        <Sidebar/>
      <div class="main">
        <div class="inner">
          <div class="title">
            <h2>공지사항 목록</h2>
          </div>

          <!-- 검색창 -->
          <div class="search-container">
            <form class="search" @submit.prevent="handleSearchSubmit">
              <input
                type="text"
                name="keyword"
                placeholder="등록명으로 검색"
                v-model="keyword"
              />
              <button type="submit">검색</button>
            </form>
          </div>

          <!-- 유저 목록 -->
          <div class="list">
            <table>
              <thead>
                <tr>
                  <th style="width: 10%">ID</th>
                  <th style="width: 10%">이름</th>
                  <th style="width: 20%">이메일</th>
                  <th style="width: 15%">연락처</th>
                  <th style="width: 10%">권한</th>
                  <th style="width: 12%">담당 트레이너</th>
                  <th style="width: 13%">가입일자</th>
                  <th style="width: 10%">회원수정</th>
                </tr>
              </thead>
              <tbody>
                <tr v-if="userList.length === 0">
                  <td colspan="8">조회된 데이터가 없습니다.</td>
                </tr>
                <tr v-for="user in userList" :key="user.no">
                  <td>{{ user.id }}</td>
                  <td>{{ user.name }}</td>
                  <td>{{ user.email }}</td>
                  <td>{{ user.phone }}</td>
                  <td>
                    {{
                      user.userAuth === 'ROLE_ADMIN'
                        ? '관리자'
                        : user.userAuth === 'ROLE_USER'
                        ? '회원'
                        : user.userAuth === 'ROLE_TRAINER'
                        ? '트레이너'
                        : '기타'
                    }}
                  </td>
                  <td>{{ !user.trainerNo ? '-' : user.trainerName }}</td>
                  <td>{{ formatDate(user.createdAt) }}</td>
                  <td v-if="user.userAuth !== 'ROLE_ADMIN'">
                    <a class="updateBtn" :href="`update/${user.no}`">수정</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          <!-- 페이지네이션 -->
          <div class="pagination" v-if="page">
            <a href="#" @click.prevent="handlePageLinkClick(page.first)">≪</a>
            <a v-if="page.page !== page.first" href="#" @click.prevent="handlePageLinkClick(page.prev)">＜</a>
            <a
              v-for="no in pageRange"
              :key="no"
              href="#"
              :class="{ active: currentPage === no }"
              @click.prevent="handlePageLinkClick(no)"
            >
              {{ no }}
            </a>
            <a v-if="page.page !== page.last" href="#" @click.prevent="handlePageLinkClick(page.next)">＞</a>
            <a href="#" @click.prevent="handlePageLinkClick(page.last)">≫</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import Header from '@/components/admin/adminHeader.vue'
import Sidebar from '@/components/admin/adminSidebar.vue'

const keyword = ref('')
const userList = ref<any[]>([])
const page = ref<any>(null)
const currentPage = ref(1)

const fetchUserList = async (pageNum = 1) => {
  try {
    const res = await axios.get(`/api/users?page=${pageNum}&keyword=${keyword.value}`)
    userList.value = res.data.users
    page.value = res.data.page
    currentPage.value = pageNum
  } catch (err) {
    console.error(err)
  }
}

const handleSearchSubmit = () => {
  fetchUserList(1)
}

const handlePageLinkClick = (pageNum: number) => {
  fetchUserList(pageNum)
}

const formatDate = (dateStr: string) => {
  return new Date(dateStr).toLocaleString()
}

const pageRange = computed(() => {
  if (!page.value) return []
  return Array.from({ length: page.value.end - page.value.start + 1 }, (_, i) => page.value.start + i)
})

onMounted(() => {
  fetchUserList(1)
})
</script>

<style scoped>
.godDoUser .container {
  display: flex;
}

.godDoUser .ticketList .mainTitle {
  height: 100px;
  text-align: center;
  line-height: 100px;
  font-weight: bold;
  font-size: 1.2em;
}

.godDoUser .main {
  display: flex;
  width: 100%;
}

.godDoUser .button,
.godDoUser .search button,
.godDoUser .search-form button {
  padding: 10px 20px;
  background-color: #008cba;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  font-size: 14px;
  transition: background-color 0.3s ease, color 0.3s ease;
  box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.2);
}

.godDoUser .button:hover,
.godDoUser .search button:hover,
.godDoUser .search-form button:hover {
  background-color: #005f73;
}

.godDoUser .search {
  width: 100%;
  display: flex;
  justify-content: end;
  margin-left: -30px;
}

.godDoUser .search input[type='text'],
.godDoUser .search-form input[type='text'] {
  padding: 8px;
  width: 200px;
  margin-right: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}

.godDoUser .list {
  margin-top: 30px;
  display: flex;
  justify-content: center;
  width: 1500px;
  max-height: 550px;
}

.godDoUser table {
  width: 80%;
  border-collapse: collapse;
  background-color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.godDoUser table th,
.godDoUser table td {
  border: 1px solid #ddd;
  padding: 4px;
  text-align: center;
  font-size: 14px;
}

.godDoUser table th {
  background-color: #f4f4f4;
}

.godDoUser .pagination {
  display: flex;
  justify-content: center;
  margin-top: 100px;
}

.godDoUser .pagination a {
  margin: 0 5px;
  padding: 8px 16px;
  text-decoration: none;
  color: #008cba;
  border: 1px solid #ddd;
  border-radius: 5px;
  transition: background-color 0.3s ease, color 0.3s ease;
  box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.1);
}

.godDoUser .pagination a:hover {
  background-color: #005f73;
  color: white;
}

.godDoUser .pagination a.active {
  background-color: #008cba;
  color: white;
  border: 1px solid #008cba;
  pointer-events: none;
}

.godDoUser .inner {
  margin-top: 50px;
  height: 800px;
  width: 1500px;
  background-color: #e7ecef;
  border-radius: 5%;
  box-sizing: border-box;
}

.godDoUser .inner .title {
  width: 100%;
  height: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.godDoUser .mainTitle {
  width: 100%;
  height: 100px;
  text-align: center;
  line-height: 100px;
  font-weight: bold;
  font-size: 1.2em;
}

.godDoUser .attendance-table th:nth-child(3),
.godDoUser .attendance-table td:nth-child(3) {
  width: 80px;
}
</style>
