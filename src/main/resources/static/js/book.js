var app = new Vue({
    el: "#box",
    data: {
        bid: ""
    },
    methods: {
        addShopcar: function () {
            var that = this;
            axios.post("/addShopcar", {

            }).then(
                function (response) {

                    console.log(response);
                }, function (err) { })
        }
    }
})