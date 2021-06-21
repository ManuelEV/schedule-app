<template>
  <div class="grid grid-cols-3">
    <div
      v-for="schedule in schedules" :key="schedule.id"
      class="m-2"
      v-on:click="navigate(schedule.id)"
    >
      <schedule-card
        :title="schedule.title"
        :description="schedule.description"
      />
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import ScheduleCard from '@/components/cards/ScheduleCard.vue';

export default {
  name: 'SchedulesCardsWrapper',
  components: {
    ScheduleCard,
  },
  computed: {
    ...mapState(['schedules']),
  },
  async created() {
    await this.getSchedules();
  },
  methods: {
    ...mapActions(['getSchedules']),
    navigate(scheduleId) {
      this.$router.push({ name: 'Contacts', params: { scheduleId } });
    },
  },
};
</script>
