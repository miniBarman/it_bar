var coctailApi = Vue.resource('/coctail{/id}');
var ingredientApi = Vue.resource('/ingredient{/id}');

Vue.component('coctail-row', {
    props: ['coctail'],
    template: '<div> <i>{{ coctail.id }}</i> {{ coctail.name }}</div>'
});

Vue.component('coctail-list', {
    props: ['coctails'],
    template: '<div> ' +
                '<div>List</div>' +
                '<coctail-row v-for = "coctail in coctails" :key="coctail.id" :coctail="coctail" />' +
              '</div>'
});

var app = new Vue({
  el: '#app',
  template: '<coctail-list :coctails="coctails"/>',
  data: {
      coctails: frontendData.coctails,
      profile: frontendData.profile
    },
});