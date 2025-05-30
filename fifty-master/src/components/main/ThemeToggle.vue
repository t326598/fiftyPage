<template>
    <button @click="$emit('toggle')" class="theme-toggle">
        {{ isDark ? '☀️' : '🌙' }}
    </button>
</template>
<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'
const props = defineProps<{ isDark: boolean }>()
const emit = defineEmits(['toggle'] as const)
</script>
