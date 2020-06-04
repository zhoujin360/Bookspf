var login = new Vue({
    el: "#loginForm",
    data: {
        username: "",
        password: "",
        captcha: "",
        code: "",
        errmes: "",
        isShow: false
    },
    mounted() {
        this.changeCode();
    },
    methods: {
        submit: function() {
            var that = this;
            var usernameReg = /^[A-Za-z0-9]{3,20}$/;
            var passwordReg = /^[0-9A-Za-z]{6,20}$/;
            if (!usernameReg.test(that.username) || that.username == '') {
                that.isShow = true;
                that.errmes = '用户名格式错误';
            } else if (!passwordReg.test(that.password) || that.password == '') {
                that.isShow = true;
                that.errmes = '密码格式错误';
            } else if (that.code == '') {
                that.isShow = true;
                that.errmes = '验证码不能为空';
            } else {
                that.isShow = false;
                axios.post("/login", {
                    username: that.username,
                    password: that.password,
                    captcha: that.captcha
                }).then(function(response) {
                    if (response.data.status) {
                        window.location.reload();
                    } else {
                        that.isShow = true;
                        that.errmes = response.data.mes;
                    }
                })
            }
        },
        changeCode() {
            var that = this;
            axios.post("/changeCode")
                .then(response => {
                    that.code = response.data;
                })
        }
    }
});