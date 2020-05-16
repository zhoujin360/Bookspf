var app = new Vue({
    el: "#tt",
    data: {
        username: '',
        mes: ''
    },
    methods: {
        findUser: function() {
            var that = this;
            that.mes = that.username;
            axios.post("/findUser", that.username)
                .then(function(response) {
                    console.log(response);
                })
        }
    }
});