<template>
    <div class="min-w-screen min-h-screen bg-gray-800 text-white">
        <nav-bar />
        <div class="my-5 mx-3">
            <slot />
        </div>
    </div>
</template>

<script>

import NavBar from '@/components/navigation/NavBar.vue';

export default {
  name: 'LayoutDefault',
  components: {
    NavBar,
  },
};
</script>
