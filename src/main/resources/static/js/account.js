//top
var top = new Vue({
    el: "#top",
    methods: {
        logout() {
            axios.post("/logout")
                .then(function () {
                    window.location.reload();
                })
        }
    }
});



var app = new Vue({
    el: "#box",
    data: {
        phone: "",
        realname: "",
        address: "",
        errmes: '',
        isShow: false,
        phoneShow: true,
        realnameShow: true,
        addressShow: true
    },
    methods: {
        isShowInput(index) {
            if (index == 0) this.phoneShow = !this.phoneShow;
            if (index == 1) this.realnameShow = !this.realnameShow;
            if (index == 2) this.addressShow = !this.addressShow;
        },
        changePassword() {
            var that = this;
            var passwordReg = /^[0-9A-Za-z]{6,20}$/;
            axios.posr("/changePassword", {
                password: password
            }).then(response => {
                alert(response.data.mes);
            })
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
                    } else {

                    }
                })
            }

        },
        changeRealname() {
            var that = this;
            var realnameReg = /^[\u4e00-\u9fa5]{2,8}$/;
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