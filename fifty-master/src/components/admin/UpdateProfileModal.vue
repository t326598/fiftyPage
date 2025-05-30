<template>
  <div class="modal-overlay" @click.self="close">
    <div class="modal-content">
      <h2>프로필 수정</h2>

      <div class="form-group">
        <label>타이틀</label>
        <input v-model="form.title" />
      </div>

      <div class="form-group">
        <label>서브 타이틀</label>
        <input v-model="form.subContent" />
      </div>

      <div class="form-group">
        <label>콘텐츠</label>
        <textarea v-model="form.content"></textarea>
      </div>

      <div class="form-group">
        <label>이미지</label>
        <input type="file" @change="onImageChange" />
        <img v-if="previewUrl" :src="previewUrl" class="preview" />
      </div>

      <div class="actions">
        <button @click="submit">저장</button>
        <button @click="close">닫기</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from "@/api/profile"

const props = defineProps<{
  profile: any
}>()

const emit = defineEmits(['close', 'updated'])

const form = ref({
  no: props.profile.no,
  title: props.profile.title,
  subContent: props.profile.subContent,
  content: props.profile.content,
  file: null as File | null,       // 선택한 새 이미지 파일
  filePath: props.profile.filePath // 기존 이미지 경로 (서버 저장된 파일명 등)
})

const previewUrl = ref<string | null>(`http://localhost:8080/upload/${props.profile.filePath}`)

const onImageChange = (e: Event) => {
  const file = (e.target as HTMLInputElement).files?.[0]
  if (file) {
    form.value.file = file
    previewUrl.value = URL.createObjectURL(file)
  }
}

const uploadFile = async (file: File): Promise<string> => {
  const formData = new FormData()
  formData.append('file', file)
  const response = await axios.UpdateImage( formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  })
  console.log(response.data)


  return response.data.filePath
}

const submit = async () => {
  try {
    console.log("나오나요?")
    let newFilePath = form.value.filePath
    if (form.value.file) {
      newFilePath = await uploadFile(form.value.file)
    }
    // 프로필 수정 API 호출, 기존 파일 경로도 보내서 서버가 이전 파일 삭제 가능하게
    const res = await axios.UpdateProfile({
      no: form.value.no,
      title: form.value.title,
      subContent: form.value.subContent,
      content: form.value.content,
      filePath: newFilePath,
      oldFilePath: form.value.filePath  
    })

    console.log(res)
    emit('updated')
    emit('close')
  } catch (err) {
    console.error(err)
  }
}

const close = () => emit('close')
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0; left: 0; right: 0; bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}
.modal-content {
  background: white;
  padding: 24px;
  width: 400px;
  border-radius: 8px;
}
.form-group {
  margin-bottom: 16px;
}
.preview {
  width: 100%;
  object-fit: cover;
  margin-top: 8px;
}
.actions {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}
</style>
