//校验用户名
function checkUsername() {
    var username = $("#reg_username").val();
    var reg_username = /^\w{3,10}$/
    var flag = reg_username.test(username);
    if (flag) { //若校验通过
        $("#reg_username").css("border", "");
    } else {
        $("#reg_username").css("border", "1px solid red");
    }
    return flag;
}
//校验密码
function checkPassword() {
    var password = $("#reg_password").val();
    var reg_password = /^\w{6,20}$/
    var flag = reg_password.test(password);
    if (flag) { //若校验通过
        $("#reg_password").css("border", "");
    } else {
        $("#reg_password").css("border", "1px solid red");
    }
    return flag;
}
//校验邮箱
function checkEmail() {
    var email = $("#email").val();
    var reg_email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var flag = reg_email.test(email);
    if (flag) { //若校验通过
        $("#eamil").css("border", "");
    } else {
        $("#email").css("border", "1px solid red");
    }
    return flag;
}


$(function() {
    //若校验不通过无法提交
    $("#regForm").submit(function() {
        return checkEmail() && checkPassword() && checkPhone() && checkUsername()
    })
    $("#reg_usernmae").blur(checkUsername);
    $("#reg_password").blur(checkPassword);
    $("#email").blur(checkEmail);
})


var register = new Vue({
    el: "#regForm",
    date: {
        username: "",
        password: "",
        email: ""
    },
    methods: {
        submit: function() {
            var that = this;
            axios.post("/register", {
                username: that.username,
                password: that.password,
                email: that.email
            }).then(function(response) {
                console.log(response);
            })
        }
    }
});