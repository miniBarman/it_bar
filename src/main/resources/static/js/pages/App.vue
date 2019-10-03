<template>
    <coctail-list :coctails="coctails"/>
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