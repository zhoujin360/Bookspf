var app = new Vue({
    el: "#bigBox",
    data: {
        password: "",
        rePassword: "",
        balance: "",
        userBalance: "",
        phone: "",
        userPhone: "",
        realname: "",
        userRealname: "",
        address: "",
        userAddress: "",
        errmes: '',
        emsg: '',
        isShow: false,
        changePwdisShow: false,
        phoneShow: true,
        errmesColor: false,
        realnameShow: true,
        addressShow: true,
        balanceShow: true
    },
    directives: {
        focus: {
            update: function(el, { value }) {
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
            if (index == 3) this.changePwdisShow = !this.changePwdisShow;
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
                if (confirmRechange(that.balance)) {
                    axios.post("/changeBalance", {
                        balance: that.balance
                    }).then(response => {
                        if (response.data.status) {
                            that.userBalance = response.data.mes;
                        } else {
                            alert(response.data.mes);
                        }
                    })
                }
            }
        },
        changePassword() {
            var that = this;
            var passwordReg = /^[0-9A-Za-z]{6,20}$/;
            if (that.password === that.rePassword) {
                if (!passwordReg.test(that.password) || that.password == '') {
                    that.emsg = "密码必须为6-20位字母或数字组合";
                    that.errmesColor = false;
                    that.isShow = true;
                } else {
                    that.isShow = false;
                    axios.post("/changePassword", {
                        password: that.password
                    }).then(response => {
                        // that.password = "";
                        // that.rePassword = "";
                        // alert(response.data.mes);
                        if (response.data.status) {
                            that.errmesColor = true;
                        } else {
                            that.errmesColor = false;
                        }
                        that.emsg = response.data.mes;
                        that.isShow = true;
                    })
                }
            } else {
                that.password = "";
                that.rePassword = "";
                // alert("两次输入密码不一致");
                that.errmesColor = false;
                that.emsg = "两次输入密码不一致";
                that.isShow = true;
            }
        },
        lostFocus() {
            this.password = "";
            this.rePassword = "";
            this.passwordShow = true;
            this.rePasswordShow = false;
        },
        changePhone() {
            var that = this;
            var phoneReg = /^1[358][0-9]{9}$/
            if (!phoneReg.test(that.phone) || that.phone == '') {
                that.errmes = "电话格式错误";
                that.isShow = true;
            } else {
                that.isShow = false;
                if (confirmRechange(that.phone)) {
                    axios.post("/changePhone", {
                        phone: that.phone
                    }).then(response => {
                        if (response.data.status) {
                            that.userPhone = response.data.mes;
                        } else {
                            alert(response.data.mes);
                        }
                    })
                }
            }

        },
        changeRealname() {
            var that = this;
            var realnameReg = /^[\u4e00-\u9fa5]{2,16}$/;
            if (!realnameReg.test(that.realname) || that.realname == '') {
                that.errmes = "真实姓名格式错误";
                that.isShow = true;
            } else {
                that.isShow = false;
                if (confirmRechange(that.realname)) {
                    axios.post("/changeRealname", {
                        realname: that.realname
                    }).then(response => {
                        if (response.data.status) {
                            that.userRealname = response.data.mes;
                        } else {
                            alert(response.data.mes);
                        }
                    })
                }
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
                if (confirmRechange(that.address)) {
                    axios.post("/changeAddress", {
                        address: that.address
                    }).then(response => {
                        if (response.data.status) {
                            that.userAddress = response.data.mes;
                        } else {
                            alert(response.data.mes);
                        }
                    })
                }
            }

        }
    }
})


function confirmRechange(str) {
    var info = prompt("修改值:" + str + ",请输入'确认'进行修改", "");
    if (info === "确认") {
        return true;
    } else {
        return false;
    }
}