var app = new Vue({
    el: "#box",
    data: {
        email: "",
        phone: "",
        realname: "",
        address: ""
    }
})
// 用户信息修改按钮模块
var emailBtn = document.querySelector("#emailBtn");
var user_email = document.querySelector("#user_email");
var userEmail = document.querySelector("#userEmail");
var phoneBtn = document.querySelector("#phoneBtn");
var phone = document.querySelector("#phone");
var userPhone = document.querySelector("#userPhone");
var realnameBtn = document.querySelector("#realnameBtn");
var realname = document.querySelector("#realname");
var userRealName = document.querySelector("#userRealName");
var addressBtn = document.querySelector("#addressBtn");
var address = document.querySelector("#address");
var userAddress = document.querySelector("#userAddress");
emailBtn.onclick = function () {
    userEmail.disabled = "";
    user_email.style.display = "none";
    userEmail.style.display = "inline-block";
    userEmail.style.borderBottom = "2px solid #639DFF";
    userEmail.style.backgroundColor = "#F5F5F5";
}
userEmail.onblur = function () {
    var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var flag = reg.test(userEmail.value);
    if (!flag) {
        userEmail.placeholder = "邮箱格式不正确"
    }
    userEmail.disabled = "disabled";
    userEmail.style.borderBottom = "none";
    userEmail.style.backgroundColor = "#F5F5F5";
}
phoneBtn.onclick = function () {
    userPhone.disabled = "";
    phone.style.display = "none";
    userPhone.style.display = "inline-block";
    userPhone.style.borderBottom = "2px solid #639DFF";
    userPhone.style.backgroundColor = "#FFFFFF";
}
userPhone.onblur = function () {
    var reg = /^1[358][0-9]{9}$/;
    var flag = reg.test(userPhone.value);
    if (!flag) {
        userPhone.placeholder = "电话格式不正确"
    }
    userPhone.disabled = "disabled";
    userPhone.style.borderBottom = "none";
    userPhone.style.backgroundColor = "#FFFFFF";
}
realnameBtn.onclick = function () {
    userRealName.disabled = "";
    realname.style.display = "none";
    userRealName.style.display = "inline-block";
    userRealName.style.borderBottom = "2px solid #639DFF";
    userRealName.style.backgroundColor = "#FFFFFF";
}
userRealName.onblur = function () {
    var reg = /^[\u4e00-\u9fa5 ]{1,14}$/
    var flag = reg.test(userRealName.value);
    if (!flag) {
        userRealName.placeholder = "姓名格式不正确"
    }
    userRealName.disabled = "disabled";
    userRealName.style.borderBottom = "none";
    userRealName.style.backgroundColor = "#FFFFFF";
}
addressBtn.onclick = function () {
    userAddress.disabled = "";
    address.style.display = "none";
    userAddress.style.display = "inline-block";
    userAddress.style.borderBottom = "2px solid #639DFF";
    userAddress.style.backgroundColor = "#FFFFFF";
}
userAddress.onblur = function () {
    var reg = /^[\u4e00-\u9fa5 ]{2,14}$/;
    var flag = reg.test(userAddress.value);
    if (!flag) {
        userAddress.placeholder = "地址格式不正确"
    }
    userAddress.disabled = "disabled";
    userAddress.style.borderBottom = "none";
    userAddress.style.backgroundColor = "#FFFFFF";
}