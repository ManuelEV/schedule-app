<template>
    <layout-default>
        <div class="flex flex-wrap">
          <div class="w-full">
            <ul class="flex mb-0 list-none flex-wrap pt-3 pb-4 flex-row">
              <li class="-mb-px mr-2 last:mr-0 flex-auto text-center">
                <a class="text-lg font-bold uppercase px-5 py-3 shadow-lg
                  rounded block leading-normal text-gray-700
                  bg-secondary-500 hover:bg-secondary-300 cursor-pointer"
                 v-on:click="toggleTabs(1)">
                  Lista de contactos
                </a>
              </li>
              <li class="-mb-px mr-2 last:mr-0 flex-auto text-center">
                <a class="text-lg font-bold uppercase px-5 py-3 shadow-lg
                rounded block leading-normal text-gray-700
                bg-secondary-500 hover:bg-secondary-300 cursor-pointer"
                v-on:click="toggleTabs(2)">
                  Crear nuevo contacto
                </a>
              </li>
            </ul>
            <div class="relative flex flex-col min-w-0 break-words
            w-full mb-6 rounded">
              <div class="px-4 py-5 flex-auto">
                <div class="tab-content tab-space">
                  <div v-bind:class="{'hidden': openTab !== 1, 'block': openTab === 1}">

                    <contacts-cards-wrapper />

                  </div>
                  <div v-bind:class="{'hidden': openTab !== 2, 'block': openTab === 2}">

                    <create-contact-form />

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    </layout-default>
</template>

<script>

import LayoutDefault from '@/components/layouts/LayoutDefault.vue';
import ContactsCardsWrapper from '@/components/wrappers/ContactsCardsWrapper.vue';
import CreateContactForm from '@/components/forms/CreateContactForm.vue';

export default {
  name: 'Contacts',
  components: {
    LayoutDefault,
    ContactsCardsWrapper,
    CreateContactForm,
  },
  data() {
    return {
      openTab: 1,
    };
  },
  methods: {
    toggleTabs(tabNumber) {
      this.openTab = tabNumber;
    },
  },
};
</script>
