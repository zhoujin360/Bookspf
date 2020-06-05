var app = new Vue({
    el: "#box",
    data: {
        shopcarList: [],
        total: "",
    },
    mounted() {
        this.getShopcarList();
    },
    methods: {
        buy: function() {
            axios.post("/settlement").then(
                function(response) {
                    alert(response.data.mes);
                    app.getShopcarList();
                }
            )
        },
        changeNum: function (bid, booknumber, index) {
            var that = this;
            if (index == 0) {
                if (booknumber > 0) {
                    booknumber -= 1;
                } else {
                    booknumber = 0;
                }
            } else {
                if (booknumber < 100) {
                    booknumber += 1;
                } else {
                    alert("请联系客服");
                }
            }
            axios.post("/changeNum", {
                booknumber: booknumber,
                bid: bid
            }).then(function (response) {
                if (response.data.status) {
                    app.getShopcarList();
                }
            })
        },
        getShopcarList: function () {
            var that = this;
            axios.get("/getShopcarList").then(
                function (response) {
                    that.shopcarList = response.data.shopcars;
                    if (that.shopcarList != null) {
                        that.total = response.data.shopcars[0].total;
                    } else that.total = 0;
                }
            )
        },
        deleteShopcarOfBid: function (bid) {
            var that = this;
            axios.post("/deleteShopcarOfBid", {
                bid: bid,
            }).then(function (response) {
                if (response.data.status) {
                    app.getShopcarList();
                }
            })
        }
    }
})