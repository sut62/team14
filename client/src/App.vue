<template>
  <v-app>
    <v-app-bar app>
         <div>
          <v-btn class="ma-2" color="purple" dark >
            <v-icon large >mdi-home</v-icon>
          </v-btn>
        </div>
      </v-app-bar>
    <v-content style = "background-color: #ffcece">
      <router-view />
    </v-content>
  </v-app>
</template>

<script>
</script>
