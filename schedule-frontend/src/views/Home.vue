<template>
  <layout-default>
    <span class="text-2xl font-bold ml-5">
      Lista de agendas
    </span>
    <schedules-cards-wrapper />
  </layout-default>
</template>

<script>

import LayoutDefault from '@/components/layouts/LayoutDefault.vue';
import SchedulesCardsWrapper from '@/components/wrappers/SchedulesCardsWrapper.vue';

export default {
  name: 'Home',
  components: {
    LayoutDefault,
    SchedulesCardsWrapper,
  },
  data() {
    return {
    };
  },
  created() {
  },
  methods: {
  },
};
</script>
