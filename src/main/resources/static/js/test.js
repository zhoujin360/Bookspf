var app = new Vue({
    el: "#tt",
    data: {
        username: '',
        mes: ''
    },
    methods: {
        findUid: function() {
            var that = this;
            that.mes = that.username;
            axios.post("/findUid", that.username)
                .then(function(response) {
                    alert(response.data);
                    that.mes = response.data;
                })



        }
    }
});