var app = new Vue({
    el: "#box",
    data: {
    },
    methods: {
        addShopcar: function (bid) {
            var that = this;
            axios.post("/addShopcar").then(
                function (response) {
                    alert(1)
                    console.log(response);
                }, function (err) { })
        }
    }
})