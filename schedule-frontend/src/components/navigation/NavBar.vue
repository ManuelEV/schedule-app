<template>
    <div class="w-full bg-primary-500">
        <div class="py-5 w-full">
          <router-link to="/" tag="a" custom
            v-slot="{ navigate }"
          >
            <a @click="navigate" role="link" class="cursor-pointer">
              <div class="text-secondary-200 font-bold text-2xl px-5">
                SHCHEDULE APP
              </div>
            </a>
          </router-link>
        </div>
    </div>
</template>

<script>
export default {
  name: 'NavBar',
};
</script>
