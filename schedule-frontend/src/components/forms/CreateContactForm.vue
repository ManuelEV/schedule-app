<template>
  <form class="w-full flex justify-center" v-on:submit.prevent="onCreateContact">
    <div class="w-2/3 p-4 bg-primary-400 rounded flex flex-col">
      <div class="py-2 text-lg">
        Nombre
      </div>
      <input
        type="text"
        class="text-gray-800 rounded mr-12 px-4"
        v-model="contact.name"
      />
      <div class="py-2 text-lg">
        Número de contacto
      </div>
      <input
        type="text"
        class="text-gray-800 rounded mr-12 px-4"
        v-model="contact.phoneNumber"
      />
      <div class="w-full flex justify-center">
        <button
          class="mt-5 px-4 py-2 text-gray-700 font-bold text-lg rounded shadow
          bg-secondary-500 hover:bg-secondary-300 w-1/3"
          type="submit"
        >
          Crear
        </button>
      </div>
      <span class="h-2 text-sm text-red-500 w-full text-center pb-4">
          {{errorMsg}}
      </span>
    </div>
  </form>
</template>

<script>

import { mapActions } from 'vuex';

export default {
  name: 'CreateContactForm',
  data() {
    return {
      contact: {},
      errorMsg: '',
    };
  },
  methods: {
    ...mapActions(['createContact']),
    async onCreateContact() {
      if (!this.isFormValid()) {
        this.errorMsg = 'Los campos deben tener al menos 5 caracteres';
        return;
      }
      const { scheduleId } = this.$route.params;
      const data = { scheduleId, contact: { ...this.contact } };
      await this.createContact(data);

      this.clearForm();
    },
    isFormValid() {
      return (this.contact.name?.length > 4
        && this.contact.phoneNumber?.length > 4);
    },
    clearForm() {
      this.errorMsg = '';
      this.contact = {};
    },
  },
};
</script>
