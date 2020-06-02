var app = new Vue({
    el: "#box",
    data: {
        publishbook: [],
        publishbookList: [],
        rankList: [],
        currentIndex: 0,
        timer: null
    },
    mounted() {
        this.getPublishBook();
        this.getRankList();
    },
    methods: {
        //获取排行版
        getRankList: function() {
            var that = this;
            axios.get("/getRankList").then(
                function(response) {
                    that.rankList = response.data.books;
                },
                function(err) {}
            )
        },
        //获取出版图书
        getPublishBook: function() {
            var that = this;
            axios.get("/getPublishBook").then(
                function(response) {
                    that.publishbook = response.data.books[4];
                    that.publishbookList = response.data.books;
                },
                function(err) {}
            )
        },
        created() {
            //在DOM加载完成后，下个tick中开始轮播
            this.$nextTick(() => {
                this.timer = setInterval(() => {
                    this.nextImg()
                }, 4000)
            })
        },
        go() {
            this.timer = setInterval(() => {
                this.nextImg()
            }, 4000)
        },
        stop() {
            clearInterval(this.timer)
            this.timer = null
        },
        change(index) {
            this.currentIndex = index
        },
        nextImg() {
            this.currentIndex++;
            if (this.currentIndex > 6) {
                this.currentIndex = 0
            }
        },
        prevImg() {
            this.currentIndex--;
            if (this.currentIndex < 0) {
                this.currentIndex = 6
            }
        }
    }
})