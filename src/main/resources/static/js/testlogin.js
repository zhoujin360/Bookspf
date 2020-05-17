//校验用户名
function checkUsername() {
    var username = $("#log_account").val();
    var reg_username = /^\w{3,10}$/
    var flag = reg_username.test(username);
    if (flag) { //若校验通过
        $("#log_account").css("border", "");
    } else {
        $("#log_account").css("border", "1px solid red");
    }
    return flag;
}
//校验密码
function checkPassword() {
    var password = $("#log_password").val();
    var reg_password = /^\w{6,20}$/
    var flag = reg_password.test(password);
    if (flag) { //若校验通过
        $("#log_password").css("border", "");
    } else {
        $("#log_password").css("border", "1px solid red");
    }
    return flag;
}
$(function() {
    //若校验不通过无法提交
    $("#logForm").submit(function() {
        return checkUsername() && checkPassword();
    })
    $("#log_usernmae").blur(checkUsername);
    $("#log_password").blur(checkPassword);
})

var login = new Vue({
    el: "#loginForm",
    date: {
        username: "",
        password: ""
    },
    methods: {
        submit: function() {
            var that = this;
            axios.post("/login", {
                username: that.username,
                password: that.password
            }).then(function(response) {
                console.log(response);
            })
        }
    }
});