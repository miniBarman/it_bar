import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import vuetify from './plugins/vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(VueResource)

new Vue ({
    el: '#app',
    vuetify,
    render: a => a(App)
})
