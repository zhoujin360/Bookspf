//top
var top = new Vue({
    el: "#top",
    methods: {
        logout() {
            axios.post("/logout")
                .then(function() {
                    window.location.reload();
                })
        }
    }
});

function noLogin() {
    alert("请登录再操作")
}

function changeBid(bid) {
    app.bid = bid;
}


var app = new Vue({
    el: "#box",
    data: {
        bid: ""
    },
    methods: {
        addShopcar: function() {
            var that = this;
            axios.post("/addShopcar", {
                bid: that.bid
            }).then(function(response) {
                alert(response.data.mes);
            })
        },
        buy: function() {
            var that = this;
            axios.post("/buyBook", {
                bid: that.bid
            }).then(function(response) {
                alert(response.data.mes);
            })
        }
    }
})