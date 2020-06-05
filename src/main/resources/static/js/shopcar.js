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
        buy: function () {
            if (this.shopcarList != null) {
                if (confirmBuy()) {
                    axios.post("/settlement").then(
                        function (response) {
                            alert(response.data.mes);
                            app.getShopcarList();
                        }
                    )
                } else {
                    alert("购买失败");
                }
            }
        },
        changeNum: function (bid, booknumber, index) {
            var that = this;
            if (index == 0) {
                if (booknumber > 1) {
                    booknumber -= 1;
                } else {
                    that.deleteShopcarOfBid(bid);
                }
            } else booknumber += 1;
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
function confirmBuy() {
    var info = prompt("总价:" + app.total + ",请输入'购买'确认");
    if (info == '购买') {
        return true;
    } else {
        return false;
    }
}