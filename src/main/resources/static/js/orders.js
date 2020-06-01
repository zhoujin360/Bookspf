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
    // el: "#orderList",
    el: "#order_information",
    data: {
        orders: [],
        Show: true
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
        isShow() {
            this.Show = false;
            checkOrder.Show = true;
        },
        externalCheckOrder(orderid) {
            checkOrder.checkOrder(orderid);
        }
    }
});

function externalGetOrderList(index, str) {
    getOrderList.getOrderList(index, str);
}
// var order = new Vue({
//     el: "#box",
//     data: {
//         flag: false,
//     },
//     methods: {
//         showDetailed: function () {
//             this.flag = true;
//         }
//     }
// })
/*************************************************/

//搜索订单
var orderSearch = new Vue({
    // el: "#orderSearch",
    el: "#search",
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
    // el: "#checkOrder",
    el: "#order_detailed",
    data: {
        orderid: '',
        paid: '',
        ordersinfo: [],
        Show: false
    },
    methods: {
        checkOrder(orderid) {
            var that = this;
            axios.post("/checkOrder", {
                orderid: orderid
            }).then(response => {
                console.log(response);
                that.orderid = response.data.ordersinfo[0].orderid;
                that.paid = response.data.ordersinfo[0].paid;
                that.ordersinfo = response.data.ordersinfo;
            })
        },
        isShow() {
            this.Show = false;
            getOrderList.Show = true;
        }
    }
});

/*************************************************/
