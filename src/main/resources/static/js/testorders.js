//这个是order页面的js
var order = new Vue({
    el: "#box",
    data: {
        flag: false,
    },
    methods: {
        showDetailed: function () {
            this.flag = true;

        }
    }
})