<template>
  <div class="upload-container">
    <!-- 선택 박스 -->
    <div class="field">
      <label for="crt">멤버버:</label>
      <select v-model="form.crt" id="crt">
        <option disabled value="">선택</option>
         <option value="1">키나</option>
        <option value="2">샤넬</option>
        <option value="3">예원</option>
        <option value="4">하나</option>
        <option value="5">아테나나</option>
  
      </select>
    </div>

    <div class="field">
      <label for="true_day">촬영일자:</label>
      <select v-model="form.true_day" id="true_day">
        <option disabled value="">선택</option>
        <option value="2024">2024년</option>
        <option value="2025">2025년</option>
      </select>
    </div>

    <!-- 이미지 업로드 -->
    <div class="field">
      <label>이미지 업로드:</label>
      <input type="file" accept="image/*" @change="onFileChange" />
    </div>

    <!-- 이미지 미리보기 -->
    <div v-if="previewUrl" class="preview">
      <img :src="previewUrl" alt="preview" />
      <button @click="removeImage">취소</button>
    </div>
    <button @click="submit">업로드</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios  from '../../axios/files'

const form = ref({
  crt: '',
  true_day: '',
  image: null
})

const submit = async () => {
    console.log(form.value.image); 
// [object File] 형태가 나와야 합니다.

  const formData = new FormData()
  formData.append('crt', form.value.crt)
  formData.append('trueDay', form.value.true_day)
  formData.append('data', form.value.image) // 서버에서 MultipartFile 받는 필드명이 data여야 함

  try {
    const res = await axios.insertFile( formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    console.log('성공:', res.data)
  } catch (err) {
    console.error('에러 발생:', err)
  }
}

const previewUrl = ref(null)

const onFileChange = (e) => {
  const file = e.target.files[0]
  if (file) {
    form.value.image = file
    previewUrl.value = URL.createObjectURL(file)
  }
}

const removeImage = () => {
  form.value.image = null
  previewUrl.value = null
}
</script>

<style scoped>
.upload-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  max-width: 300px;
}

.field {
  display: flex;
  flex-direction: column;
}

.preview img {
  max-width: 100px;
  max-height: 100px;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.preview button {
  margin-top: 0.5rem;
  background: #f44336;
  color: white;
  border: none;
  padding: 0.3rem 0.6rem;
  border-radius: 4px;
  cursor: pointer;
}
</style>
