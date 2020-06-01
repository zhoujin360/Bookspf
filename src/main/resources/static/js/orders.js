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

//获取订单信息列表
var getOrderList = new Vue({
    el: "#orderList",
    data: {
        orders: []
    },
    mounted: function() {
        this.getOrderList(0, "");
    },
    methods: {
        getOrderList(index, str) {
            var that = this;
            axios.post("/getOrderList", {
                index: index,
                str: str
            }).then(response => {
                that.orders = response.data.orders;
            })
        },
        externalCheckOrder(orderid) {
            checkOrder.checkOrder(orderid);
        }
    }
});

function externalGetOrderList(index, str) {
    getOrderList.getOrderList(index, str);
}

/*************************************************/

//搜索订单
var orderSearch = new Vue({
    el: "#orderSearch",
    data: {
        orderid: '',
        createtime: ''
    },
    methods: {
        orderSearch() {
            if (this.orderid != '') externalGetOrderList(1, this.orderid);
            else if (this.createtime != '') externalGetOrderList(2, this.createtime);
            else externalGetOrderList(0, "");
        }
    }
});

/*************************************************/

//查看订单
var checkOrder = new Vue({
    el: "#checkOrder",
    data: {
        orderid: '',
        paid: '',
        ordersinfo: []
    },
    methods: {
        checkOrder(orderid) {
            var that = this;
            axios.post("/checkOrderOfAdmin", {
                orderid: orderid
            }).then(response => {
                that.orderid = response.data.ordersinfo[0].orderid;
                that.paid = response.data.ordersinfo[0].paid;
                that.ordersinfo = response.data.ordersinfo;
            })
        }
    }
});

/*************************************************/