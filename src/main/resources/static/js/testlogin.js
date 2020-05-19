window.addEventListener("load", function () {
    var logForm = document.querySelector("#logForm");
    var password = document.querySelector("#log_password");
    var username = document.querySelector('#log_account');
    //校验用户名
    function checkPassword() {
        var reg_password = /^\w{6,20}$/;
        var flag = reg_password.test(password.value);
        if (flag) {
            password.style.border = "";
        } else {
            password.style.border = "1px solid red";
        }
        return flag;
    }
    //校验密码
    function checkUsername() {
        var reg_username = /^\w{3,10}$/
        var flag = reg_username.test(username.value);
        if (flag) {
            username.style.border = "";
        } else {
            username.style.border = "1px solid red";
        }
        return flag;
    }
    // logForm.onsubmit = function () {
    //     return checkUsername() && checkPassword();
    // }

    username.onblur = function () {
        checkUsername();
    }

    password.onblur = function () {
        checkPassword();
    }

})


var login = new Vue({
    el: "#loginForm",
    data: {
        username: "",
        password: "",
        msg: ""
    },
    methods: {
        submit: function () {
            var that = this;
            axios.post("/login", {
                username: that.username,
                password: that.password
            }).then(function (response) {
                console.log(response);
                if (!response.data.status) {
                    that.msg = response.data.mes + "<br/>";
                }

            })
        }
    }
});