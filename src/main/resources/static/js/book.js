var app = new Vue({
    el: "#box",
    data: {

    },
    methods: {
        addShopcar: function () {
            axios.get("/addShopcar").then(
                function (response) {
                    console.log(response);
                })
        }
    }
})