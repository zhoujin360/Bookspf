// 轮播图模块
var leftBtn = document.getElementById("left");
var rightBtn = document.getElementById("right");
var cont = document.querySelector(".cont");
var items = document.getElementsByClassName("item");
var points = document.getElementsByClassName("point");
var index = 0;
var timer = null;

var clearActive = function () {
    for (var i = 0; i < items.length; i++) {
        items[i].className = 'item';
        points[i].className = 'point';
    }
}
var goIndex = function () {
    clearActive();
    items[index].className = 'item active';
    points[index].className = 'point active';
}
right.onclick = function () {
    if (index < items.length - 1) {
        index++;
    } else {
        index = 0;
    }
    goIndex();
}
left.onclick = function () {
    if (index > 0) {
        index--;
    } else {
        index = items.length - 1;
    }
    goIndex();
}

timer = setInterval(() => {
    right.onclick();
}, 4000)

cont.onmouseover = function () {
    clearInterval(timer);
}
cont.onmouseout = function () {
    timer = setInterval(() => {
        right.onclick();
    }, 4000)
}
for (var i = 0; i < points.length; i++) {
    points[i].addEventListener('click', function () {
        var ponintIndex = this.getAttribute("data-index");
        index = ponintIndex;
        goIndex();
    })
}
//轮播图结束
var app = new Vue({
    el: "#box",
    data: {
        publishbook: [],
        publishbookList: [],
        rankList: []
    },
    mounted() {
        this.getPublishBook();
        this.getRankList();
    },
    methods: {
        //获取排行版
        getRankList: function () {
            var that = this;
            axios.get("/getRankList").then(
                function (response) {
                    that.rankList = response.data.books;
                }, function (err) {

                }
            )
        },
        //获取出版图书
        getPublishBook: function () {
            var that = this;
            axios.get("/getPublishBook").then(
                function (response) {
                    that.publishbook = response.data.books[4];
                    that.publishbookList = response.data.books;
                }, function (err) { })
        }
    }
})
