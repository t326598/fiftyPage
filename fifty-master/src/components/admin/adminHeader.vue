<template>
  <div class="admin-layout">
    <!-- Header -->
    <header class="header">
  
      <div class="header-link">
        <a href="/">홈페이지</a>
        <button type="button" @click="handleLogout" class="btnst">로그아웃</button>
      </div>
    </header>

 
  </div>
</template>

<script setup lang="ts">


const handleLogout = () => {
  console.log('로그아웃 실행됨')
  // 실제 로그아웃 로직 (예: 토큰 삭제 후 라우터 이동 등)
}

</script>

<style scoped>
/* Header */
.btnst {
  text-decoration: none;
  color: inherit;
  border: none;
  background: none;
  font-size: 18px;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  font-weight: bold;
  cursor: pointer;
}

.header {
  padding: 0 30px;
  width: 2500px;
  height: 75px;
  background-color: #E7ECEF;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo {
  width: 250px;
  height: 75px;
}

.logo img {
  width: 200px;
  height: 75px;
  object-fit: contain;
}

.header-link {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 250px;
  height: 50px;
}

.header-link > a {
  color: #000;
  text-decoration: none;
  width: 125px;
  font-size: 18px;
  font-weight: bold;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
}

.header-link > a:hover {
  color: #ddd;
}

/* Sidebar */
.sidebar * {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  font: inherit;
}

</style>
