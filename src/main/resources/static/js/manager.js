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

//验证BID
function checkBid(bid) {
    let reg = /^[0-9]{1}[0-9]*$/;
    let flag = reg.test(bid);
    return flag;
}

// 验证13位数
function checkNum(num) {
    let reg = /^[0-9]{13}$/;
    let flag = reg.test(num);
    return flag;
}

//验证分类ID
function checkSortId(sortid) {
    let reg = /^[0-9]{1}[0-9]*$/;
    let flag = reg.test(sortid);
    return flag;
}

//验证图书价格
function checkPrice(bookprice) {
    let reg = /^[0-9.]+$/;
    let flag = reg.test(bookprice);
    return flag && bookprice > 0;
}
// 验证是否上架
function checkAdded(added) {
    let reg = /^[01]{1}$/;
    let flag = reg.test(added);
    return flag;
}


//删除确认
function confirmDel() {
    var info = prompt("请输入'确认'进行删除", "");
    if (info === "确认") {
        return true;
    } else {
        return false;
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
        },
        deleteBook(bid) {
            if (confirmDel()) {
                var that = this;
                axios.post("/deleteBook", {
                    bid: bid
                }).then(response => {
                    if (response.data.status) {
                        getBookList.getBookList();
                    }
                })
            } else {}

        },
        setBook(book) {
            alterBook.setBook(book);
        }
    }
});



function externalGetBookList() {
    getBookList.getBookList();
}

/*************************************************/

//添加图书

var addBook = new Vue({
    el: "#addBook",
    data: {
        bid: '',
        bookname: '',
        sortid: '',
        author: '',
        description: '',
        bookprice: '',
        added: '',
        bookimg: '',
        imgevent: '',
        errmes: '',
        imgName: '',
        isUpImg: false,
        errmesColor: false,
        isShow: false
    },
    methods: {
        submit: function() {
            var that = this;

            if (!checkBid(that.bid)) {
                that.showErrems("图书ID必须为正整数");
            } else if (that.bookname.length < 1) {
                that.showErrems("请输入图书名字");
            } else if (!checkSortId(that.sortid)) {
                that.showErrems("分类ID必须为正整数");
            } else if (that.author.length < 1) {
                that.showErrems("请输入作者名字");
            } else if (!checkPrice(that.bookprice)) {
                that.showErrems("价格输入格式错误");
            } else if (!checkAdded(that.added)) {
                that.showErrems("是否上架(0否/1是)");
            } else if (!that.isUpImg) {
                that.showErrems("请上传图书图片");
            } else {
                that.isShow = false;
                this.bookimg = this.imgevent.target.files[0];
                let config = { headers: { "Content-Type": "multipart/form-data" } };
                let param = new FormData();
                param.append("file", that.bookimg);
                param.append("bid", that.bid);
                param.append("bookname", that.bookname);
                param.append("sortid", that.sortid);
                param.append("author", that.author);
                param.append("description", that.description);
                param.append("bookprice", that.bookprice);
                param.append("added", that.added);
                axios.post("/addBook", param, config).then(response => {
                    if (response.data.status) {
                        that.bid = '';
                        that.bookname = '';
                        that.sortid = '';
                        that.author = '';
                        that.description = '';
                        that.bookprice = '';
                        that.added = '';
                        that.imgevent.target.files[0] = '';
                        that.isShow = true;
                        that.errmesColor = true;
                        that.errmes = response.data.mes;
                        getBookList.getBookList();
                    } else {
                        that.isShow = true;
                        that.errmesColor = false;
                        that.errmes = response.data.mes;
                    }
                })
            }
        },
        showErrems(mes) {
            this.errmes = mes;
            this.errmesColor = false;
            this.isShow = true;
        },
        fileChange(event) {
            this.imgevent = event;
            this.imgName = event.target.files[0].name;
            this.isUpImg = true;

        }
    }
})



//修改图书

var alterBook = new Vue({
    el: "#alterBook",
    data: {
        bid: '',
        bookname: '',
        sortid: '',
        author: '',
        description: '',
        bookprice: '',
        added: '',
        bookimg: '',
        imgevent: '',
        errmes: '',
        imgName: '',
        isUpImg: false,
        errmesColor: false,
        isShow: false
    },
    methods: {
        submit: function() {
            var that = this;

            if (that.bookname.length < 1) {
                that.showErrems("请输入图书名字");
            } else if (!checkSortId(that.sortid)) {
                that.showErrems("分类ID必须为正整数");
            } else if (that.author.length < 1) {
                that.showErrems("请输入作者名字");
            } else if (!checkPrice(that.bookprice)) {
                that.showErrems("价格输入格式错误");
            } else if (!checkAdded(that.added)) {
                that.showErrems("是否上架(0否/1是)");
            } else if (!that.isUpImg) {
                that.showErrems("请上传图书图片");
            } else {
                that.isShow = false;
                this.bookimg = this.imgevent.target.files[0];
                let config = { headers: { "Content-Type": "multipart/form-data" } };
                let param = new FormData();
                param.append("isUpImg", that.isUpImg);
                param.append("file", that.bookimg);
                param.append("bid", that.bid);
                param.append("bookname", that.bookname);
                param.append("sortid", that.sortid);
                param.append("author", that.author);
                param.append("description", that.description);
                param.append("bookprice", that.bookprice);
                param.append("added", that.added);
                axios.post("/alterBook", param, config).then(response => {
                    if (response.data.status) {
                        that.bookname = '';
                        that.sortid = '';
                        that.author = '';
                        that.description = '';
                        that.bookprice = '';
                        that.added = '';
                        that.imgevent.target.files[0] = '';
                        that.isShow = true;
                        that.errmesColor = true;
                        that.errmes = response.data.mes;
                        getBookList.getBookList();
                    } else {
                        that.isShow = true;
                        that.errmesColor = false;
                        that.errmes = response.data.mes;
                    }
                })
            }
        },
        showErrems(mes) {
            this.errmes = mes;
            this.errmesColor = false;
            this.isShow = true;
        },
        fileChange(event) {
            this.imgevent = event;
            this.imgName = event.target.files[0].name;
            this.isUpImg = true;
        },
        setBook(book) {
            this.bid = book.bid;
            this.bookname = book.bookname;
            this.sortid = book.sortid;
            this.author = book.author;
            this.description = book.description;
            this.bookprice = book.bookprice;
            this.added = book.added;
            this.bookimg = '';
            this.imgName = '';
            this.isUpImg = false;
        }
    }
})

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
            if (confirmDel()) {
                var that = this;
                axios.post("/deleteSort", {
                    sortid: sortid
                }).then(response => {
                    if (response.data.status) {
                        getSortList.getSortList();
                    }
                })
            } else {}
        },
        setSort(sort) {
            alterSort.setSort(sort);
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
            if (!checkSortId(that.sortid)) {
                that.showErrems("分类ID必须为正整数")
            } else if (that.sortname == '') {
                that.showErrems("分类名不能为空")
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
                        that.showErrems(response.data.mes);
                    }
                })
            }
        },
        showErrems(mes) {
            this.errmes = mes;
            this.errmesColor = false;
            this.isShow = true;
        }
    }
});

/*************************************************/

/*************************************************/

//修改分类信息
var alterSort = new Vue({
    el: "#alterSort",
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
            if (that.sortname == '') {
                that.showErrems("分类名不能为空")
            } else {
                that.isShow = false;
                axios.post("/alterSort", {
                    sortid: that.sortid,
                    sortname: that.sortname
                }).then(response => {
                    if (response.data.status) {
                        that.sortname = '';
                        that.isShow = true;
                        that.errmesColor = true;
                        that.errmes = response.data.mes;
                        getSortList.getSortList();
                    } else {
                        that.showErrems(response.data.mes);
                    }
                })
            }
        },
        showErrems(mes) {
            this.errmes = mes;
            this.errmesColor = false;
            this.isShow = true;
        },
        setSort(sort) {
            this.sortid = sort.sortid;
            this.sortname = sort.sortname;
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
            axios.post("/getOrderListOfAdmin", {
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
        uid: '',
        createtime: ''
    },
    methods: {
        orderSearch() {
            if (this.orderid != '') externalGetOrderList(1, this.orderid.trim());
            else if (this.uid != '') externalGetOrderList(2, this.uid.trim());
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
            axios.post("/checkOrderOfAdmin", {
                orderid: orderid
            }).then(response => {
                that.orderid = response.data.orders[0].orderid;
                that.paid = response.data.orders[0].paid;
                that.ordersinfo = response.data.orders;
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
            axios.post("/getSaleList", {
                index: index,
                str: str
            }).then(response => {
                that.sales = response.data.sales;
            })
        },
        externalCheckSale(saleid) {
            checkSale.checkSale(saleid);
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
            if (this.saleid != '') externalGetSaleList(1, this.saleid.trim());
            else if (this.isbn != '') externalGetSaleList(2, this.isbn.trim());
            else if (this.saletime != '') externalGetSaleList(3, this.saletime);
            else externalGetSaleList(0, "");
        }
    }
});

/*************************************************/


//查看库存详情
var checkSale = new Vue({
    el: "#checkSale",
    data: {
        saleid: '',
        salesinfo: []
    },
    methods: {
        checkSale(saleid) {
            var that = this;
            axios.post("/checkSale", {
                saleid: saleid
            }).then(response => {
                that.saleid = response.data.sales[0].saleid;
                that.salesinfo = response.data.sales;
            })
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
            axios.post("/getPurchaseList", {
                index: index,
                str: str
            }).then(response => {
                that.purchases = response.data.purchases;
            })

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
            if (this.purchaseid != '') externalGetPurchaseList(1, this.purchaseid.trim());
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
                that.purchaseid = response.data.purchases[0].purchaseid;
                that.purchasesinfo = response.data.purchases;
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
            axios.post("/getStockList", {
                index: index,
                str: str
            }).then(response => {
                that.stocks = response.data.stocks;
            })
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
            if (this.stockid != '') externalGetStockList(1, this.stockid.trim());
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
            if (!checkNum(that.purchaseid)) {
                that.showErrems("进货ID必须为13位正整数");
            } else if (!checkBid(that.bid)) {
                that.showErrems("图书ID必须为正整数");
            } else if (!checkNum(that.isbn)) {
                that.showErrems("ISBN必须为13位正整数");
            } else if (!checkPrice(that.purchaseprice)) {
                that.showErrems("价格输入格式错误");
            } else if (that.purchasetime == '') {
                that.showErrems("进货时间不能为空");
            } else {
                that.isShow = false;
                axios.post("/addPurchase", {
                    purchaseid: that.purchaseid,
                    bid: that.bid,
                    isbn: that.isbn,
                    purchaseprice: that.purchaseprice,
                    purchasetime: that.purchasetime
                }).then(response => {
                    console.log(response)
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
        },
        showErrems(mes) {
            this.errmes = mes;
            this.errmesColor = false;
            this.isShow = true;
        }
    }
});