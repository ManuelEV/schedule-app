import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    schedules: [],
    contacts: {},
  },
  getters: {
    getContactsByScheduleId: (state) => (scheduleId) => (state.contacts[`${scheduleId}`]),
    getAllContacts: (state) => state.contacts,
  },
  mutations: {
    updateAllSchedules(state, schedules) {
      state.schedules = schedules;
    },
    appendSchedule(state, schedule) {
      state.schedules.push(schedule);
    },
    updateSchedule(state, schedule) {
      const index = state.schedules.findIndex((s) => s.id === schedule.id);
      state.schedules[index] = schedule;
    },
    removeSchedule(state, id) {
      const index = state.schedules.findIndex((s) => s.id === id);
      state.schedules.splice(index, 1);
    },
    updateAllContacts(state, data) {
      const { scheduleId, contacts } = data;
      state.contacts[`${scheduleId}`] = contacts;
    },
    appendContact(state, data) {
      const { scheduleId, contact } = data;
      state.contacts[`${scheduleId}`].push(contact);
    },
    editContact(state, data) {
      const { scheduleId, contact } = data;
      const index = state.contacts[`${scheduleId}`].findIndex((c) => c.id === contact.id);
      const newState = state.contacts;
      newState[`${scheduleId}`][index] = contact;
      // for reactivity reasons 😐
      state.contacts = {};
      state.contacts = newState;
    },
    removeContact(state, data) {
      const { contactId, scheduleId } = data;
      const index = state.contacts[`${scheduleId}`].findIndex((c) => c.id === contactId);
      state.contacts[`${scheduleId}`].splice(index, 1);
    },
  },
  actions: {
    async getSchedules({ commit }) {
      const response = await axios
        .get(`${process.env.VUE_APP_API_URL}/schedules`);
      const { data, status } = response;
      commit('updateAllSchedules', data);
      return status;
    },
    async createSchedule({ commit }, schedule) {
      const response = await axios
        .post(`${process.env.VUE_APP_API_URL}/schedules`, { ...schedule });
      const { data, status } = response;
      delete data.contacts;
      commit('appendSchedule', data);
      return status;
    },
    async updateSchedule({ commit }, requestData) {
      const { id, schedule } = requestData;
      const response = await axios
        .put(`${process.env.VUE_APP_API_URL}/schedules/${id}`, { ...schedule });
      const { data, status } = response;
      delete data.contacts;
      commit('updateSchedule', data);
      return status;
    },
    async deleteSchedule({ commit }, id) {
      const response = await axios
        .delete(`${process.env.VUE_APP_API_URL}/schedules/${id}`);
      const { status } = response;
      commit('removeSchedule', id);
      return status;
    },
    async getContacts({ commit }, scheduleId) {
      const response = await axios
        .get(`${process.env.VUE_APP_API_URL}/contacts?scheduleId=${scheduleId}`);
      const { data, status } = response;
      commit('updateAllContacts', { scheduleId, contacts: data });
      return status;
    },
    async createContact({ commit }, requestData) {
      const { contact, scheduleId } = requestData;
      const response = await axios
        .post(`${process.env.VUE_APP_API_URL}/contacts?scheduleId=${scheduleId}`, { ...contact });
      const { data, status } = response;
      commit('appendContact', { scheduleId, contact: { ...data } });
      return status;
    },
    async updateContact({ commit }, requestData) {
      const { contactId, scheduleId, contact } = requestData;
      const response = await axios
        .put(`${process.env.VUE_APP_API_URL}/contacts/${contactId}`, { ...contact });
      const { data, status } = response;
      commit('editContact', { scheduleId, contact: { ...data } });
      return status;
    },
    async deleteContact({ commit }, requestData) {
      const { contactId, scheduleId } = requestData;
      const response = await axios
        .delete(`${process.env.VUE_APP_API_URL}/contacts/${contactId}`);
      const { status } = response;
      commit('removeContact', { contactId, scheduleId });
      return status;
    },
  },
  modules: {
  },
});
