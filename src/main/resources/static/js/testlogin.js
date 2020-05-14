//校验用户名
function checkUsername() {
    var username = $("#log_account").val();
    var reg_username = /^\w{8,20}$/
    var flag = reg_username.test(username);
    if (flag) {    //若校验通过
        $("#log_account").css("border", "");
    } else {
        $("#log_account").css("border", "1px solid red");
    }
    return flag;
}
//校验密码
function checkPassword() {
    var password = $("#log_password").val();
    var reg_password = /^\w{8,20}$/
    var flag = reg_password.test(password);
    if (flag) {    //若校验通过
        $("#log_password").css("border", "");
    } else {
        $("#log_password").css("border", "1px solid red");
    }
    return flag;
}
$(function () {
    //若校验不通过无法提交
    $("#logForm").submit(function () {
        return checkUsername() && checkPassword();
    })
    $("#log_usernmae").blur(checkUsername);
    $("#log_password").blur(checkPassword);
})