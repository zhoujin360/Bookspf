var register = new Vue({
    el: "#regForm",
    data: {
        username: "",
        password: "",
        email: "",
        captcha: "",
        code: "",
        errmes: "",
        isShow: false,
    },
    mounted() {
        this.changeCode();
    },
    methods: {
        submit: function () {
            var that = this;
            var usernameReg = /^[A-Za-z0-9]{3,20}$/;
            var passwordReg = /^[0-9A-Za-z]{6,20}$/;
            var emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if (!usernameReg.test(that.username) || that.username == '') {
                that.isShow = true;
                that.errmes = '用户名必须为3-20位数字或字母组合';
            } else if (!passwordReg.test(that.password) || that.password == '') {
                that.isShow = true;
                that.errmes = '密码必须为6-20位字母或数字组合';
            } else if (that.email == '' || !emailReg.test(that.email)) {
                that.isShow = true;
                that.errmes = '邮箱格式错误';
            } else if (that.code == '') {
                that.isShow = true;
                that.errmes = '验证码不能为空';
            } else {
                that.isShow = false;
                axios.post("/register", {
                    username: that.username,
                    password: that.password,
                    email: that.email,
                    captcha: that.captcha
                }).then(function (response) {
                    if (!response.data.status) {
                        that.isShow = true;
                        that.errmes = response.data.mes;
                    } else {
                        console.log(response.data)
                        window.location.replace("/login");
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