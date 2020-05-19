var index = new Vue({
    el: "#box",
    data: {
        index: 0,
        imgArr: [
            "img/1.jpg",
            "img/2.jpg",
            "img/3.jpg",
            "img/4.jpg",
            "img/11.jpg",
            "img/9.jpg",
            "img/10.jpg"
        ]
    },
    methods: {
        //上一张图片
        last: function () {
            this.index--;
        },
        //下一张图片
        next: function () {
            this.index++;
        },
        // 轮播图切换
        rotation: function () {
            setInterval(() => {
                this.index++;
                if (this.index == 7) {
                    this.index = 0;
                }
            }, 2000);
        }
    }

})