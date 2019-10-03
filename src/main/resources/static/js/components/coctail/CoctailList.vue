<template>
    <div>
        <div>List</div>
        <coctail-row v-for = "coctail in coctails" :key="coctail.id" :coctail="coctail" />
    </div>
</template>

<script>
    import CoctailRow from "components/coctail/CoctailRow.vue"

    export default {
        props: ['coctails'],
        components: {
            CoctailRow
        }
    }
</script>

<style>

</style>