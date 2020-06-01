//top
var top = new Vue({
    el: "#top",
    methods: {
        logout() {
            axios.post("/logout")
                .then(function() {
                    window.location.reload();
                })
        }
    }
});

window.addEventListener("load", function() {
    var username = document.querySelector("#reg_username");
    var password = document.querySelector("#reg_password");
    var email = document.querySelector("#email");
    var regForm = document.querySelector("#regForm");
    var err_msg = document.querySelector("#err_msg");
    //校验用户名
    function checkUsername() {
        var reg_username = /^\w{3,10}$/
        var flag = reg_username.test(username.value);
        if (flag) { //若校验通过
            username.style.border = "";
            err_msg.innerHTML = "";
        } else {
            username.style.borderBottom = "3px solid #F26552";
            err_msg.innerHTML = "请输入正确的用户名<br/>";
        }
        return flag;
    }
    //校验密码
    function checkPassowrd() {
        var reg_password = /^\w{6,20}$/;
        var flag = reg_password.test(password.value);
        if (flag) {
            password.style.border = "";
            err_msg.innerHTML = "";
        } else {
            password.style.borderBottom = "3px solid #F26552";
            err_msg.innerHTML = "请输入正确的密码<br/>";
        }
        return flag;
    }
    //校验邮箱
    function checkEmail() {
        var reg_email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        var flag = reg_email.test(email.value);
        if (flag) { //若校验通过
            email.style.border = "";
            err_msg.innerHTML = "";
        } else {
            email.style.borderBottom = "3px solid #F26552";
            err_msg.innerHTML = "请输入正确的邮箱<br/>";
        }
        return flag;
    }

    // regForm.onsumbit = function () {
    //     return checkEmail() && checkPassword() && checkPhone() && checkUsername();
    // }

    username.onblur = function() {
        checkUsername();
    }

    password.onblur = function() {
        checkPassowrd();
    }

    email.onblur = function() {
        checkEmail();
    }
})

var register = new Vue({
    el: "#regForm",
    data: {
        username: "",
        password: "",
        email: "",
        msg: ""
    },
    methods: {
        submit: function() {
            var that = this;
            axios.post("/register", {
                username: that.username,
                password: that.password,
                email: that.email
            }).then(function(response) {
                // console.log(response);
                that.msg = response.data.mes;
            })
        },

    }
});