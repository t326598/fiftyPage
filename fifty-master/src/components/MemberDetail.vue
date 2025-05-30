<template>
  <div class="slider-container">
    <div class="slider-wrapper" :style="sliderStyle" ref="sliderRef">

      <div
        class="slide-item"
        v-for="(video, index) in repeatedVideos"
        :key="index + '-' + video.videoId"
        @click="goToVideo(video.videoUrl)"
      >
        <img :src="video.thumbnailUrl" :alt="video.title" />
        <div class="info">
          <p class="title">{{ video.title }}</p>
          <p class="views">{{ formatViews(video.viewCount) }} views</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, onBeforeUnmount, computed } from 'vue';
import axios from '../api/chart';

const videoitems = ref<any[]>([]);
const currentOffset = ref(0);
const sliderRef = ref<HTMLElement | null>(null);
let timer: number | undefined;

const repeatedVideos = computed(() => [...videoitems.value, ...videoitems.value]);

const fetchMusic = async () => {
  const response = await axios.getYoutubeChart();
  videoitems.value = response.data;
  console.log(videoitems.value)
};

const formatViews = (num: number) => {
  if (num >= 1_000_000) return (num / 1_000_000).toFixed(1) + 'M';
  if (num >= 1_000) return (num / 1_000).toFixed(1) + 'K';
  return num.toString();
};

const goToVideo = (url: string) => {
  window.open(url, '_blank');
};

const slideSpeed = 0.5; // px/frame

const slide = () => {
  if (!sliderRef.value) return;

  currentOffset.value += slideSpeed;

  const itemWidth = sliderRef.value.scrollWidth / repeatedVideos.value.length;
  const totalWidth = itemWidth * videoitems.value.length;

  if (currentOffset.value >= totalWidth) {
    currentOffset.value = 0;
  }

  sliderRef.value.style.transform = `translateX(-${currentOffset.value}px)`;
};

const startSliding = () => {
  timer = window.setInterval(slide, 16);
};

const stopSliding = () => {
  if (timer) clearInterval(timer);
};

onMounted(async () => {
  await fetchMusic();
  startSliding();
});

onBeforeUnmount(() => {
  stopSliding();
});
</script>

<style scoped>
.slider-container {
  overflow: hidden;
  width: 100%;
}

.slider-wrapper {
  display: flex;
  will-change: transform;
}

.slide-item {
  position: relative;
  flex: 0 0 auto;
  width: 320px;
  padding: 10px;
  background: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-right: 1px solid #eee;
}

img {
  width: 280px;
  height: 158px;
  object-fit: cover;
  border-radius: 6px;
  margin-bottom: 6px;
}

.info {
  width: 280px;
}

.title {
  font-size: 16px;
  font-weight: 600;
  margin: 0 0 4px 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.views {
  font-size: 14px;
  color: #666;
}
</style>
