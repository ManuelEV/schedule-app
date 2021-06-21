<template>
  <div>
    <button class="bg-secondary-500 text-gray-800 rounded px-2 text-sm"
    type="button"
    v-on:click="toggleModal()">
      Editar
    </button>
    <div v-if="showModal" class="overflow-x-hidden overflow-y-auto fixed inset-0 z-50
    outline-none focus:outline-none justify-center items-center flex"
    >
      <div class="relative w-auto my-6 mx-auto max-w-3xl">
        <!--content-->
        <div class="border-0 rounded-lg shadow-lg relative flex flex-col w-full
        bg-primary-500 outline-none focus:outline-none">
          <!--header-->
          <div class="flex items-start justify-between p-5 border-b border-solid
          border-blueGray-200 rounded-t">
            <h3 class="text-2xl font-semibold">
              Editar contacto
            </h3>
            <button class="p-1 ml-auto bg-transparent border-0 float-right
            text-3xl leading-none font-semibold outline-none focus:outline-none"
            v-on:click="toggleModal()">
              <span class="h-6 w-6 text-2xl block outline-none
              focus:outline-none">
                ×
              </span>
            </button>
          </div>
          <!--body-->
          <div class="relative p-6 flex-auto">
            <div class="w-full flex justify-center">
              <div class="w-full p-4 rounded flex flex-col">
                <div class="py-2 text-lg">
                  Nombre
                </div>
                <input
                  type="text"
                  class="text-gray-800 rounded px-4"
                  v-model="contact.name"
                />
                <div class="py-2 text-lg">
                  Número de contacto
                </div>
                <input
                  type="text"
                  class="text-gray-800 rounded px-4"
                  v-model="contact.phoneNumber"
                />

              </div>
          </div>

          </div>
          <div class="w-full text-center">
            <span class="h-2 text-sm text-red-500 w-full pb-4">
              {{errorMsg}}
            </span>
          </div>
          <!--footer-->
          <div class="flex items-center justify-end p-6 border-t rounded-b">
            <button class="bg-red-500 font-bold
            text-sm px-6 py-2 rounded outline-none
            focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
            type="button"
            v-on:click="onCancel()">
              Cancelar
            </button>
            <button class="bg-secondary-500 text-gray-800 rounded font-bold px-6 py-2 text-sm
            outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
            type="button"
            v-on:click="onUpdateContact()">
              Guardar cambios
            </button>
          </div>

        </div>
      </div>
    </div>
    <div v-if="showModal" class="opacity-25 fixed inset-0 z-40 bg-black"></div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'ContactEditModal',
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
  data() {
    return {
      showModal: false,
      contact: {
        name: this.name,
        phoneNumber: this.phoneNumber,
      },
      errorMsg: '',
    };
  },
  methods: {
    toggleModal() {
      this.showModal = !this.showModal;
    },
    ...mapActions(['updateContact']),
    async onUpdateContact() {
      if (!this.isFormValid()) {
        this.errorMsg = 'Los campos deben tener al menos 5 caracteres';
        return;
      }
      const { scheduleId } = this.$route.params;
      const contactId = this.id;
      const data = { contactId, scheduleId, contact: { ...this.contact } };
      await this.updateContact(data);

      this.clearForm();
    },
    isFormValid() {
      return (this.contact.name?.length > 4
        && this.contact.phoneNumber?.length > 4);
    },
    clearForm() {
      this.errorMsg = '';
      this.toggleModal();
    },
    onCancel() {
      this.contact = {
        name: this.name,
        phoneNumber: this.phoneNumber,
      };
      this.toggleModal();
    },
  },
};
</script>
