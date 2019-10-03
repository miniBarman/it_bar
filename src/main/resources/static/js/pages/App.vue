<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>
                ITbar
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar app>

        <v-content>
            <coctail-list :coctails="coctails"/>
        </v-content>
    </v-app>
</template>

<script>
    import CoctailList from "components/coctail/CoctailList.vue"

    export default {
        components: {
            CoctailList
        },
        data() {
            return{
                profile: frontendData.profile,
                coctails: frontendData.coctails
            }
        }
    }
</script>

<style>

</style>