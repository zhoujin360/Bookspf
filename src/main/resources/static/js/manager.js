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

function addBookPurchaseItem() {
    let add = document.getElementById("bookPurchase");
    add.innerHTML += "<div class='bookPurchaseItem'><span>图书ID</span>：<input type='text'><span>进货价格</span>：<input type='text'><span>ISBN</span>：<input type='text'></div>"
}