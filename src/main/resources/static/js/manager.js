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









/*************************************************/

//获取订单信息列表
var getOrderList = new Vue({
    el: "#orderList",
    data: {
        orders: []
    },
    methods: {
        getOrderList(index, str) {
            var that = this;
            if (index == 0) {
                axios.post("/getOrderList", {
                    index: 0,
                    str: str
                }).then(response => {
                    that.orders = response.data.orders;
                })
            } else if (index == 1) {
                axios.post("/getOrderList", {
                    index: 1,
                    str: str
                }).then(response => {
                    that.orders = response.data.orders;
                })
            } else if (index == 2) {
                axios.post("/getOrderList", {
                    index: 2,
                    str: str
                }).then(response => {
                    that.orders = response.data.orders;
                })
            } else if (index == 3) {
                axios.post("/getOrderList", {
                    index: 3,
                    str: str
                }).then(response => {
                    that.orders = response.data.orders;
                })
            }
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
        uid: '',
        createtime: ''
    },
    methods: {
        orderSearch() {
            if (this.orderid != '') externalGetOrderList(1, this.orderid);
            else if (this.uid != '') externalGetOrderList(2, this.uid);
            else if (this.createtime != '') externalGetOrderList(3, this.createtime);
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










/*************************************************/

//获取销售记录列表
var getSaleList = new Vue({
    el: "#saleList",
    data: {
        sales: []
    },
    methods: {
        getSaleList(index, str) {
            var that = this;
            if (index == 0) {
                axios.post("/getSaleList", {
                    index: 0,
                    str: str
                }).then(response => {
                    that.sales = response.data.sales;
                })
            } else if (index == 1) {
                axios.post("/getSaleList", {
                    index: 1,
                    str: str
                }).then(response => {
                    that.sales = response.data.sales;
                })
            } else if (index == 2) {
                axios.post("/getSaleList", {
                    index: 2,
                    str: str
                }).then(response => {
                    that.sales = response.data.sales;
                })
            } else if (index == 3) {
                axios.post("/getSaleList", {
                    index: 3,
                    str: str
                }).then(response => {
                    that.sales = response.data.sales;
                })
            }
        }
    }
});

function externalGetSaleList(index, str) {
    getSaleList.getSaleList(index, str);
}

/*************************************************/

//搜索销售记录
var saleSearch = new Vue({
    el: "#saleSearch",
    data: {
        saleid: '',
        isbn: '',
        saletime: ''
    },
    methods: {
        saleSearch() {
            if (this.saleid != '') externalGetSaleList(1, this.saleid);
            else if (this.isbn != '') externalGetSaleList(2, this.isbn);
            else if (this.saletime != '') externalGetSaleList(3, this.saletime);
            else externalGetSaleList(0, "");
        }
    }
});

/*************************************************/









/*************************************************/

//获取进货记录列表
var getPurchaseList = new Vue({
    el: "#purchaseList",
    data: {
        purchases: []
    },
    methods: {
        getPurchaseList(index, str) {
            var that = this;
            if (index == 0) {
                axios.post("/getPurchaseList", {
                    index: 0,
                    str: str
                }).then(response => {
                    that.purchases = response.data.purchases;
                })
            } else if (index == 1) {
                axios.post("/getPurchaseList", {
                    index: 1,
                    str: str
                }).then(response => {
                    that.purchases = response.data.purchases;
                })
            } else if (index == 2) {
                axios.post("/getPurchaseList", {
                    index: 2,
                    str: str
                }).then(response => {
                    that.purchases = response.data.purchases;
                })
            }
        },
        externalCheckPurchase(purchaseid) {
            checkPurchase.checkPurchase(purchaseid);
        }
    }
});

function externalGetPurchaseList(index, str) {
    getPurchaseList.getPurchaseList(index, str);
}

/*************************************************/

//搜索进货记录
var purchaseSearch = new Vue({
    el: "#purchaseSearch",
    data: {
        purchaseid: '',
        purchasetime: ''
    },
    methods: {
        purchaseSearch() {
            if (this.purchaseid != '') externalGetPurchaseList(1, this.purchaseid);
            else if (this.purchasetime != '') externalGetPurchaseList(2, this.purchasetime);
            else externalGetPurchaseList(0, "");
        }
    }
});

/*************************************************/

//查看进货详情
var checkPurchase = new Vue({
    el: "#checkPurchase",
    data: {
        purchaseid: '',
        purchasesinfo: []
    },
    methods: {
        checkPurchase(purchaseid) {
            var that = this;
            axios.post("/checkPurchase", {
                purchaseid: purchaseid
            }).then(response => {
                that.purchaseid = response.data.purchasesinfo[0].purchaseid;
                that.purchasesinfo = response.data.purchasesinfo;
            })
        }
    }
});

/*************************************************/











/*************************************************/

//获取库存记录列表
var getStockList = new Vue({
    el: "#stockList",
    data: {
        stocks: []
    },
    methods: {
        getStockList(index, str) {
            var that = this;
            if (index == 0) {
                axios.post("/getStockList", {
                    index: 0,
                    str: str
                }).then(response => {
                    that.stocks = response.data.stocks;
                })
            } else if (index == 1) {
                axios.post("/getStockList", {
                    index: 1,
                    str: str
                }).then(response => {
                    that.stocks = response.data.stocks;
                })
            } else if (index == 2) {
                axios.post("/getStockList", {
                    index: 2,
                    str: str
                }).then(response => {
                    that.stocks = response.data.stocks;
                })
            }
        },
        externalCheckStock(stockid) {
            checkStock.checkStock(stockid);
        }
    }
});

function externalGetStockList(index, str) {
    getStockList.getStockList(index, str);
}

/*************************************************/

//搜索库存记录
var stockSearch = new Vue({
    el: "#stockSearch",
    data: {
        stockid: '',
        stocktime: ''
    },
    methods: {
        stockSearch() {
            if (this.stockid != '') externalGetStockList(1, this.stockid);
            else if (this.stocktime != '') externalGetStockList(2, this.stocktime);
            else externalGetStockList(0, "");
        }
    }
});

/*************************************************/


//查看库存详情
var checkStock = new Vue({
    el: "#checkStock",
    data: {
        stockid: '',
        stocksinfo: []
    },
    methods: {
        checkStock(stockid) {
            var that = this;
            axios.post("/checkStock", {
                stockid: stockid
            }).then(response => {
                that.stockid = response.data.stocks[0].stockid;
                that.stocksinfo = response.data.stocks;
            })
        }
    }
});

/*************************************************/










//添加进货记录
var addPurchase = new Vue({
    el: "#addPurchase",
    data: {
        purchaseid: '',
        bid: '',
        isbn: '',
        purchaseprice: '',
        purchasetime: '',
        errmes: '',
        errmesColor: false,
        isShow: false
    },
    methods: {
        submit: function() {
            var that = this;
            axios.post("/addPurchase", {
                purchaseid: that.purchaseid,
                bid: that.bid,
                isbn: that.isbn,
                purchaseprice: that.purchaseprice,
                purchasetime: that.purchasetime
            }).then(response => {
                if (response.data.status) {
                    that.purchaseid = '';
                    that.bid = '';
                    that.isbn = '';
                    that.purchaseprice = '';
                    that.purchasetime = '';
                    that.isShow = true;
                    that.errmesColor = true;
                    that.errmes = response.data.mes;
                    getPurchaseList.getPurchaseList(0, "");
                } else {
                    that.isShow = true;
                    that.errmesColor = false;
                    that.errmes = response.data.mes;
                }
            })
        }
    }
});