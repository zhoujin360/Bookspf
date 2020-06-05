function noLogin() {
    alert("请登录再操作")
}

function changeInfo(bid, price) {
    app.bid = bid;
    app.price = price;
}


var app = new Vue({
    el: "#box",
    data: {
        bid: "",
        price: ""
    },
    methods: {
        addShopcar: function () {
            var that = this;
            axios.post("/addShopcar", {
                bid: that.bid
            }).then(function (response) {
                alert(response.data.mes);
            })
        },
        buy: function () {
            var that = this;
            if (confirmBuy()) {
                axios.post("/buyBook", {
                    bid: that.bid
                }).then(function (response) {

                    alert(response.data.mes);
                })
            } else {
                alert("购买失败");
            }
        }
    }
})
function confirmBuy() {
    var info = prompt("书本价格:" + app.price + ",请输入'购买'确认");
    alert(prompt)
    if (info == '购买') {
        return true;
    } else {
        return false;
    }
}