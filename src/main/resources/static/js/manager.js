//箭头旋转
var rotate90 = new Vue({
    el: "#navitems",
    data: {
        isShow: 0
    },
    methods: {
        rotateT: function(index) {
            if (this.isShow == index) this.isShow = 0;
            else this.isShow = index;
        }
    }
})

/*************************************************/

//登出
var logout = new Vue({
    el: "#logout",
    methods: {
        logout() {
            axios.post("/logout")
                .then(function() {
                    window.location.reload();
                })
        }
    }
});

/*************************************************/

//页面切换
var bookInfo = document.getElementById("bookInfo");
var sortInfo = document.getElementById("sortInfo");
var orderInfo = document.getElementById("orderInfo");
var saleInfo = document.getElementById("saleInfo");
var purchaseInfo = document.getElementById("purchaseInfo");
var stockInfo = document.getElementById("stockInfo");

function isMain(str) {
    if (str == "bookInfo") {
        bookInfo.className = "mainItemShow";
        sortInfo.className = "mainItemHidden";
        orderInfo.className = "mainItemHidden";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "sortInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemShow";
        orderInfo.className = "mainItemHidden";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "orderReview") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderInfo.className = "mainItemShow";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "saleInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderInfo.className = "mainItemHidden";
        saleInfo.className = "mainItemShow";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "purchaseInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderInfo.className = "mainItemHidden";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemShow";
        stockInfo.className = "mainItemHidden";
    } else if (str == "stockInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderInfo.className = "mainItemHidden";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemShow";
    }
}

/*************************************************/

//窗口和遮罩层的隐藏与显示
var isAction = false;
var that;
var block = document.getElementById("actionBlock");

function action(str) {
    if (str !== "block") that = document.getElementById(str);
    this.isAction = !this.isAction;
    if (isAction) {
        that.className = "window actionShow";
        block.className = "actionShow";
    } else {
        that.className = "window actionHidden";
        block.className = "actionHidden";
    }
}

/*************************************************/

//获得图书信息列表
var getBookList = new Vue({
    el: "#bookList",
    data: {
        books: []
    },
    methods: {
        getBookList() {
            var that = this;
            axios.get("/getBookList")
                .then(response => {
                    that.books = response.data.books;
                })
        }
    }
});

function externalGetBookList() {
    getBookList.getBookList();
}

/*************************************************/

//添加图书暂时不做   还没想好怎么做

/*************************************************/

//获取分类信息列表
var getSortList = new Vue({
    el: "#sortList",
    data: {
        sorts: []
    },
    methods: {
        getSortList() {
            var that = this;
            axios.get("/getSortList")
                .then(response => {
                    that.sorts = response.data.sorts;
                })
        },
        deleteSort(sortid) {
            var that = this;
            axios.post("/deleteSort", {
                sortid: sortid
            }).then(response => {
                if (response.data.status) {
                    getSortList.getSortList();
                }
            })
        }
    }
});

function externalGetSortList() {
    getSortList.getSortList();
}

/*************************************************/

//添加分类信息
var addSort = new Vue({
    el: "#addSort",
    data: {
        sortid: '',
        sortname: '',
        errmes: '',
        errmesColor: false,
        isShow: false
    },
    methods: {
        submit: function() {
            var that = this;
            if (that.sortid == '' || that.sortid.length < 1 || isNaN(that.sortid)) {
                that.errmes = "分类ID必须为正整数";
                that.errmesColor = false;
                that.isShow = true;
            } else if (that.sortname == '') {
                that.errmes = "分类名不能为空";
                that.errmesColor = false;
                that.isShow = true;
            } else {
                that.isShow = false;
                axios.post("/addSort", {
                    sortid: that.sortid,
                    sortname: that.sortname
                }).then(response => {
                    if (response.data.status) {
                        that.sortid = '';
                        that.sortname = '';
                        that.isShow = true;
                        that.errmesColor = true;
                        that.errmes = response.data.mes;
                        getSortList.getSortList();
                    } else {
                        that.isShow = true;
                        that.errmesColor = false;
                        that.errmes = response.data.mes;
                    }
                })
            }
        }
    }
});

/*************************************************/

//获取订单信息列表
var getOrderList = new Vue({
    el: "#orderList",
    data: {
        orders: []
    },
    methods: {
        getOrderList() {
            var that = this;
            axios.get("/getOrderList")
                .then(response => {
                    console.log(response);
                    that.orders = response.data.orders;
                })
        },
        externalCheckOrder(orderid) {
            checkOrder.checkOrder(orderid);
        }
    }
});

function externalGetOrderList() {
    getOrderList.getOrderList();
}

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
            axios.post("/checkOrder", {
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

//获取销售记录列表
var getSaleList = new Vue({
    el: "#saleList",
    data: {
        sales: []
    },
    methods: {
        getSaleList() {
            var that = this;
            axios.get("/getSaleList")
                .then(response => {
                    console.log(response);
                    that.sales = response.data.sales;
                })
        }
    }
});

function externalGetSaleList() {
    getSaleList.getSaleList();
}

/*************************************************/

//获取库存记录列表
var getStockList = new Vue({
    el: "#stockList",
    data: {
        stocks: []
    },
    methods: {
        getStockList() {
            var that = this;
            axios.get("/getStockList")
                .then(response => {
                    console.log(response);
                    that.stocks = response.data.stocks;
                })
        }
    }
});

function externalGetStockList() {
    getStockList.getStockList();
}


/*************************************************/
function addBookPurchaseItem() {
    let add = document.getElementById("bookPurchase");
    add.innerHTML += "<div class='bookPurchaseItem'><span>图书ID</span>：<input type='text'><span>进货价格</span>：<input type='text'><span>ISBN</span>：<input type='text'></div>"
}