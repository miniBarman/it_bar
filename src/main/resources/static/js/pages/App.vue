<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>
                ITbar
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">
                {{profile.username}}
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" v-bind:value=csrfToken />
                    <input type="submit" value="Sign Out"/>
                </form>
            </span>
            <span v-if="!profile">
                <login-form />
            </span>
        </v-app-bar app>

        <v-content>
            <coctail-list :coctails="coctails"/>
        </v-content>
    </v-app>
</template>

<script>
    import CoctailList from "components/coctail/CoctailList.vue"
    import LoginForm from "components/toolbar/LoginForm.vue"

    export default {
        components: {
            CoctailList,
            LoginForm
        },
        data() {
            return{
                profile: frontendData.profile,
                coctails: frontendData.coctails,
                csrfToken: csrfToken
            }
        }
    }
</script>

<style>

</style>