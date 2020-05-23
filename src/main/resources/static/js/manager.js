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

//页面切换
var bookInfo = document.getElementById("bookInfo");
var sortInfo = document.getElementById("sortInfo");
var orderReview = document.getElementById("orderReview");
var saleInfo = document.getElementById("saleInfo");
var purchaseInfo = document.getElementById("purchaseInfo");
var stockInfo = document.getElementById("stockInfo");

function isMain(str) {
    if (str == "bookInfo") {
        bookInfo.className = "mainItemShow";
        sortInfo.className = "mainItemHidden";
        orderReview.className = "mainItemHidden";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "sortInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemShow";
        orderReview.className = "mainItemHidden";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "orderReview") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderReview.className = "mainItemShow";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "saleInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderReview.className = "mainItemHidden";
        saleInfo.className = "mainItemShow";
        purchaseInfo.className = "mainItemHidden";
        stockInfo.className = "mainItemHidden";
    } else if (str == "purchaseInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderReview.className = "mainItemHidden";
        saleInfo.className = "mainItemHidden";
        purchaseInfo.className = "mainItemShow";
        stockInfo.className = "mainItemHidden";
    } else if (str == "stockInfo") {
        bookInfo.className = "mainItemHidden";
        sortInfo.className = "mainItemHidden";
        orderReview.className = "mainItemHidden";
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
function addBookPurchaseItem() {
    let add = document.getElementById("bookPurchase");
    add.innerHTML += "<div class='bookPurchaseItem'><span>图书ID</span>：<input type='text'><span>进货价格</span>：<input type='text'><span>ISBN</span>：<input type='text'></div>"
}