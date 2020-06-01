var app = new Vue({
        el: "#box",
        data: {
            phone: "",
            realname: "",
            address: ""
        },
        methods: {
            changePassword() {
                var that = this;
                axios.posr("/changePassword", {
                    password: password
                }).then(response => {
                    alert(response.data.mes);
                })
            },
            changePhone() {
                var that = this;
                axios.post("/changePhone", {
                    phone: that.phone
                }).then(response => {
                    if (response.data.status) {
                        window.location.reload();
                    }
                })
            },
            changeRealname() {
                var that = this;
                axios.post("/changeRealname", {
                    realname: that.realname
                }).then(response => {
                    if (response.data.status) {
                        window.location.reload();
                    }
                })
            },
            changeAddress() {
                var that = this;
                alert(that.address)
                axios.post("/changeAddress", {
                    address: that.address
                }).then(response => {
                    if (response.data.status) {
                        window.location.reload();
                    }
                })
            }
        }
    })
    // 用户信息修改按钮模块
var phoneBtn = document.querySelector("#phoneBtn");
var phone = document.querySelector("#phone");
var userPhone = document.querySelector("#userPhone");
var realnameBtn = document.querySelector("#realnameBtn");
var realname = document.querySelector("#realname");
var userRealName = document.querySelector("#userRealName");
var addressBtn = document.querySelector("#addressBtn");
var address = document.querySelector("#address");
var userAddress = document.querySelector("#userAddress");

phoneBtn.onclick = function() {
    userPhone.disabled = "";
    phone.style.display = "none";
    userPhone.style.display = "inline-block";
    userPhone.style.borderBottom = "2px solid #639DFF";
    userPhone.style.backgroundColor = "#FFFFFF";
}
userPhone.onblur = function() {
    var reg = /^1[358][0-9]{9}$/;
    var flag = reg.test(userPhone.value);
    if (!flag) {
        userPhone.placeholder = "电话格式不正确"
    } else {
        app.changePhone();
    }
    userPhone.disabled = "disabled";
    userPhone.style.borderBottom = "none";
    userPhone.style.backgroundColor = "#FFFFFF";
}
realnameBtn.onclick = function() {
    userRealName.disabled = "";
    realname.style.display = "none";
    userRealName.style.display = "inline-block";
    userRealName.style.borderBottom = "2px solid #639DFF";
    userRealName.style.backgroundColor = "#FFFFFF";
}
userRealName.onblur = function() {
    var reg = /^[\u4e00-\u9fa5 ]{1,14}$/
    var flag = reg.test(userRealName.value);
    if (!flag) {
        userRealName.placeholder = "姓名格式不正确"
    } else {
        app.changeRealname();
    }
    userRealName.disabled = "disabled";
    userRealName.style.borderBottom = "none";
    userRealName.style.backgroundColor = "#FFFFFF";
}
addressBtn.onclick = function() {
    userAddress.disabled = "";
    address.style.display = "none";
    userAddress.style.display = "inline-block";
    userAddress.style.borderBottom = "2px solid #639DFF";
    userAddress.style.backgroundColor = "#FFFFFF";
}
userAddress.onblur = function() {
    var reg = /^[\u4e00-\u9fa5 ]{2,14}$/;
    var flag = reg.test(userAddress.value);
    if (!flag) {
        userAddress.placeholder = "地址格式不正确"
    } else {
        app.changeAddress();
    }
    userAddress.disabled = "disabled";
    userAddress.style.borderBottom = "none";
    userAddress.style.backgroundColor = "#FFFFFF";
}