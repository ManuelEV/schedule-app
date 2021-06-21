<template>
  <div
    class="p-4 bg-secondary-400 text-gray-700 rounded-lg cursor-pointer truncate"
  >
    <div class="text-xl font-bold uppercase">
      {{title}}
    </div>
    <div class="text-sm">
      {{description}}
    </div>
  </div>
</template>

<script>
export default {
  name: 'ScheduleCard',
  props: {
    title: {
      type: String,
      required: true,
    },
    description: {
      type: String,
      required: true,
    },
  },
};
</script>
