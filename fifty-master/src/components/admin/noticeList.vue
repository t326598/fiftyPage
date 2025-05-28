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


          <!-- 유저 목록 -->
          <div class="list">
            <table>
              <thead>
                <tr>
                  <th style="width: 5%">번호</th>
                  <th style="width: 15%">제목</th>
                  <th style="width: 55%">내용</th>
                  <th style="width: 15%">등록일자</th>
                  <th style="width: 10%">수정 / 삭제</th>
                </tr>
              </thead>
              <tbody>
                <tr v-if="userList.length === 0">
                  <td colspan="8">조회된 데이터가 없습니다.</td>
                </tr>
                <tr v-for="user in userList" :key="user.no">
                  <td>{{ user.no }}</td>
                  <td>{{ user.title }}</td>
                  <td>{{ user.content }}</td>
                  <td>{{ formatDate(user.createdAt) }}</td>
                  <td v-if="user.userAuth !== 'ROLE_ADMIN'">
                    <a class="updateBtn" :href="`update/${user.no}`">수정</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from '@/axios/admin'
import Header from '@/components/admin/adminHeader.vue'
import Sidebar from '@/components/admin/adminSidebar.vue'

const userList = ref<any[]>([])

const fetchUserList = async () => {
  try {
    const res = await axios.getNoticeList()
    console.log(res.data)
    userList.value = res.data
  } catch (err) {
    console.error(err)
  }
}

const formatDate = (dateStr: string) => {
  return new Date(dateStr).toLocaleString()
}


onMounted(() => {
  fetchUserList()
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
