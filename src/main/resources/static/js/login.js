var login = new Vue({
    el: "#loginForm",
    data: {
        username: "",
        password: "",
        errmes: "",
        isShow: false
    },
    methods: {
        submit: function () {
            var that = this;
            var usernameReg = /^[A-Za-z0-9]{3,20}$/;
            var passwordReg = /^[0-9A-Za-z]{6,20}$/;
            if (!usernameReg.test(that.username) || that.username == '') {
                that.isShow = true;
                that.errmes = '用户名格式错误';
            } else if (!passwordReg.test(that.password) || that.password == '') {
                that.isShow = true;
                that.errmes = '密码格式错误';
            } else {
                that.isShow = false;
                axios.post("/login", {
                    username: that.username,
                    password: that.password
                }).then(function (response) {
                    console.log(response);
                    if (response.data.status) {
                        window.location.reload();
                    } else {
                        that.errmes = response.data.mes;
                        that.isShow = true;
                    }
                })
            }
        }
    }
});