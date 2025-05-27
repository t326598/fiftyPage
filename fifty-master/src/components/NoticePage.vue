<template>
  <div class="Notice-Container">

      <div class="modal-content">
        <h2>{{ notice.title }}</h2>
        <p class="content">{{ notice.content }}</p>
        <div class="meta">
          <p>등록일자: {{ notice.createdAt }}</p>
          <p>수정일자: {{ notice.updatedAt }}</p>
        </div>
        <button @click="closePopup">닫기</button>
      </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const showPopup = ref(false)

// 공지사항 내용 예시
const notice = ref({
  title: '서비스 점검 안내',
  content: '2025년 5월 25일 오전 2시부터 4시까지 서비스 점검이 진행됩니다.',
  createdAt: '2025-05-20',
  updatedAt: '2025-05-21'
})

const closePopup = () => {
  showPopup.value = false
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 24px;
  border-radius: 12px;
  max-width: 500px;
  width: 90%;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.modal-content h2 {
  margin-top: 0;
}

.modal-content .content {
  margin: 16px 0;
  white-space: pre-wrap;
}

.modal-content .meta {
  font-size: 14px;
  color: #555;
  margin-bottom: 16px;
}

.modal-content button {
  background-color: #007bff;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
}

.modal-content button:hover {
  background-color: #0056b3;
}
</style>