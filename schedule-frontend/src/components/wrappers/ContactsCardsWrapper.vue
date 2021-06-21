<template>
  <div class="grid grid-cols-3">
    <div
      class="text-xl"
      v-if="scheduleContacts.length === 0"
    >
      Aún no hay elementos
    </div>
    <div
      v-for="contact in scheduleContacts" :key="contact.id"
      class="m-2"
    >
      <contact-card
        :name="contact.name"
        :phoneNumber="contact.phoneNumber"
        :id="contact.id"
      />
    </div>
    <div style="display: none;">
      {{contacts}}
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import ContactCard from '@/components/cards/ContactCard.vue';

export default {
  name: 'ContactsCardsWrapper',
  components: {
    ContactCard,
  },
  data() {
    return {
      scheduleContacts: [],
    };
  },
  computed: {
    ...mapState(['contacts']),
    scheduleId() {
      return this.$route.params.scheduleId;
    },
  },
  async created() {
    const { scheduleId } = this.$route.params;
    await this.getContacts(scheduleId);
    this.getScheduleContacts(this.contacts);
  },
  methods: {
    ...mapActions(['getContacts']),
    getScheduleContacts(contacts) {
      this.scheduleContacts = contacts[`${this.scheduleId}`];
    },
  },
  watch: {
    contacts(current) {
      this.getScheduleContacts(current);
    },
  },
};
</script>
