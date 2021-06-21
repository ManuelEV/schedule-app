<template>
  <div
    class="p-4 bg-primary-400 rounded-lg"
  >
    <div class="text-xl font-bold uppercase">
      {{name}}
    </div>
    <div class="">
      {{phoneNumber}}
    </div>
    <div class="flex justify-end mt-2">
      <button
        class="bg-red-500 text-white rounded mx-2 px-2 text-sm"
        v-on:click="onDeleteContact(id)"
      >
        Eliminar
      </button>
      <contact-edit-modal
        :name="name"
        :phoneNumber="phoneNumber"
        :id="id"
      />
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import ContactEditModal from '@/components/modals/ContactEditModal.vue';

export default {
  name: 'ContactCard',
  components: {
    ContactEditModal,
  },
  props: {
    id: {
      type: Number,
      required: true,
    },
    name: {
      type: String,
      required: true,
    },
    phoneNumber: {
      type: String,
      required: true,
    },
  },
  methods: {
    ...mapActions(['deleteContact']),
    onDeleteContact(contactId) {
      const { scheduleId } = this.$route.params;
      this.deleteContact({ contactId, scheduleId });
    },
  },
};
</script>
