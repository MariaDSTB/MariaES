<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';

defineProps({
  name: {
    type: String,
    default: 'App'
  }
});
</script>

<style scoped>
</style>
