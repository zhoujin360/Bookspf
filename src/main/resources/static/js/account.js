var app = new Vue({
    el: "#box",
    data: {
        password: "",
        phone: "",
        realname: "",
        address: "",
        errmes: '',
        isShow: false,
        passwordShow: true,
        phoneShow: true,
        realnameShow: true,
        addressShow: true
    },
    methods: {
        isShowInput(index) {
            if (index == 0) this.phoneShow = !this.phoneShow;
            if (index == 1) this.realnameShow = !this.realnameShow;
            if (index == 2) this.addressShow = !this.addressShow;
            if (index == 3) this.passwordShow = !this.passwordShow;
        },
        changePassword() {
            var that = this;
            var passwordReg = /^[0-9A-Za-z]{6,20}$/;
            if (!passwordReg.test(that.password) || that.password == '') {
                that.errmes = "密码格式错误";
                that.isShow = true;
            } else {
                that.isShow = false;
                axios.post("/changePassword", {
                    password: that.password
                }).then(response => {
                    alert(response.data.mes);
                })
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
                        window.location.reload();
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
                        window.location.reload();
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
                        window.location.reload();
                    }
                })
            }

        }
    }
})