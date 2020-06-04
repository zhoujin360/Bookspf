var app = new Vue({
    el: "#box",
    data: {
        password: "",
        balance: "",
        phone: "",
        userPhone: "",
        realname: "",
        address: "",
        errmes: '',
        isShow: false,
        passwordShow: true,
        phoneShow: true,
        realnameShow: true,
        addressShow: true,
        balanceShow: true
    },
    directives: {
        focus: {
            update: function (el, { value }) {
                if (value) {
                    el.focus();
                }
            }
        }
    },
    methods: {
        isShowInput(index) {
            if (index == 0) this.phoneShow = !this.phoneShow;
            if (index == 1) this.realnameShow = !this.realnameShow;
            if (index == 2) this.addressShow = !this.addressShow;
            if (index == 3) this.passwordShow = !this.passwordShow;
            if (index == 4) this.balanceShow = !this.balanceShow;
        },
        changeBalance() {
            var that = this;
            var balanceReg = /^[0-9]{1,5}$/;
            if (!balanceReg.test(that.balance) || that.balance == '') {
                that.errmes = "充值数输入错误";
                that.isShow = true;
            } else {
                that.isShow = false;
                axios.post("/changeBalance", {
                    balance: that.balance
                }).then(response => {
                    console.log(response.data.mes);
                    that.balance = response.data.mes;
                    console.log(that.balance);
                })
            }
        },
        changePassword() {
            var that = this;
            var passwordReg = /^[0-9A-Za-z]{6,20}$/;
            if (!passwordReg.test(that.password) || that.password == '') {
                that.errmes = "密码必须为6-20位字母或数字组合";
                that.isShow = true;
            } else {
                that.isShow = false;
                if (confirmDel()) {
                    axios.post("/changePassword", {
                        password: that.password
                    }).then(response => {
                        alert(response.data.mes);
                    })
                } else {
                    alert("修改错误");
                }

            }
        },
        changePhone() {
            var that = this;
            var phoneReg = /^1[358][0-9]{9}$/
            if (!phoneReg.test(that.phone) || that.phone == '') {
                that.errmes = "电话格式错误";
                that.isShow = true;
            } else {
                that.isShow = false;
                axios.post("/changePhone", {
                    phone: that.phone
                }).then(response => {
                    if (response.data.status) {
                        that.userPhone = response.data.mes;
                    }
                })
            }

        },
        changeRealname() {
            var that = this;
            var realnameReg = /^[\u4e00-\u9fa5]{2,16}$/;
            if (!realnameReg.test(that.realname) || that.realname == '') {
                that.errmes = "真实姓名格式错误";
                that.isShow = true;
            } else {
                that.isShow = false
                axios.post("/changeRealname", {
                    realname: that.realname
                }).then(response => {
                    if (response.data.status) {
                        that.realname = response.data.mes;
                    }
                })
            }
        },
        changeAddress() {
            var that = this;
            var addressReg = /^[\u4e00-\u9fa5]{6,28}$/;
            if (!addressReg.test(that.address) || that.address == '') {
                that.errmes = "地址格式错误";
                that.isShow = true;
            } else {
                that.isShow = false;
                axios.post("/changeAddress", {
                    address: that.address
                }).then(response => {
                    if (response.data.status) {
                        that.realname = response.data.mes;
                    }
                })
            }

        }
    }
}
)
function confirmDel() {
    var info = prompt("请再次输入密码", "");
    if (info === app.password) {
        return true;
    } else {
        return false;
    }
}
function confirmRecharge() {
    var info = prompt("请输入'确认'", "");
    if (info === app.password) {
        return true;
    } else {
        return false;
    }
}