//箭头旋转
var rotate90 = new Vue({
    el: "#navitems",
    data: {
        isShow: false
    },
    methods: {
        rotateT: function() {
            this.isShow = !this.isShow;
        }
    }
})

/**************************************/

//main区域隐藏显示
function isMain() {
    let managerInfo = document.getElementById("managerInfo");
    managerInfo.className = "mainItemShow"
}

/**************************************/

//窗口隐藏显示
var isAction = false;
var that;
var block = document.getElementById("actionBlock");

function action(str) {
    if (str !== "block") that = document.getElementById(str);
    this.isAction = !this.isAction;
    if (isAction) {
        that.className = "window actionShow";
        block.className = "actionShow";
    } else {
        that.className = "window actionHidden";
        block.className = "actionHidden";
    }
}

/**************************************/

//登出
var logout = new Vue({
    el: "#logout",
    methods: {
        logout() {
            axios.post("/logout")
                .then(function() {
                    window.location.reload();
                })
        }
    }
});

/**************************************/

//管理员列表获取 and 删除管理员

var getManagerList = new Vue({
    el: "#managerList",
    data: {
        managers: []
    },
    methods: {
        getManagerList() {
            var that = this;
            axios.get("/getManagerList")
                .then(response => {
                    that.managers = response.data.users;
                })
        },
        setUid(uid) {
            alterAdmin.uid = uid;
        },
        deleteAdmin(uid) {
            var that = this;
            axios.post("/deleteAdmin", {
                uid: uid
            }).then(response => {
                if (response.data.status) {
                    getManagerList.getManagerList();
                }
            })
        }
    }
});

function externalGetManagerList() {
    getManagerList.getManagerList();
}

/**************************************/

//提交添加管理员

function checkUsername(username) {
    let usernameReg = /^[A-Za-z0-9]{3,20}$/;
    if (username == '' || !usernameReg.test(username)) {
        return true;
    }
}

function checkPassword(password) {
    let passwordReg = /^[0-9A-Za-z]{6,20}$/;
    if (password == '' || !passwordReg.test(password)) {
        return true;
    }
}

function checkEmail(email) {
    let emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if (email == '' || !emailReg.test(email)) {
        return true;
    }
}

function checkRealname(realname) {
    let realnameReg = /^[\u4e00-\u9fa5]{2,16}$/;
    if (realname == '' || !realnameReg.test(realname)) {
        return true;
    }
}



var addAdmin = new Vue({
    el: "#addAdmin",
    data: {
        uid: '',
        username: '',
        password: '',
        email: '',
        realname: '',
        errmes: '',
        errmesColor: false,
        isShow: false
    },
    methods: {
        submit: function() {
            var that = this;
            if (that.uid == '' || that.uid.length < 4 || that.uid.length > 8 || isNaN(that.uid)) {
                that.showErrmes("UID必须为4-8位纯数字");
            } else if (checkUsername(that.username)) {
                that.showErrmes("用户名必须为3-20位数字或字母组合");
            } else if (checkPassword(that.password)) {
                that.showErrmes("密码必须为6-20位字母或数字组合");
            } else if (checkEmail(that.email)) {
                that.showErrmes("邮箱格式错误");
            } else if (checkRealname(that.realname)) {
                that.showErrmes("真实姓名格式错误");
            } else {
                that.isShow = false;
                axios.post("/addAdmin", {
                    uid: that.uid,
                    username: that.username,
                    password: that.password,
                    email: that.email,
                    realname: that.realname
                }).then(response => {
                    if (response.data.status) {
                        that.uid = '';
                        that.username = '';
                        that.password = '';
                        that.email = '';
                        that.realname = '';
                        that.isShow = true;
                        that.errmesColor = true;
                        that.errmes = response.data.mes;
                        getManagerList.getManagerList();
                    } else {
                        that.isShow = true;
                        that.errmesColor = false;
                        that.errmes = response.data.mes;
                    }
                })
            }
        },
        showErrmes(str) {
            this.errmes = str;
            this.errmesColor = false;
            this.isShow = true;
        }
    }
})

/**************************************/

var alterAdmin = new Vue({
    el: "#alterAdmin",
    data: {
        uid: '',
        username: '',
        password: '',
        email: '',
        realname: '',
        errmes: '',
        errmesColor: false,
        isShow: false
    },
    methods: {
        submit: function() {
            var that = this;
            if (checkUsername(that.username)) {
                that.showErrmes("用户名必须为3-20位数字或字母组合");
            } else if (checkPassword(that.password)) {
                that.showErrmes("密码必须为6-20位字母或数字组合");
            } else if (checkEmail(that.email)) {
                that.showErrmes("邮箱格式错误");
            } else if (checkRealname(that.realname)) {
                that.showErrmes("真实姓名格式错误");
            } else {
                that.isShow = false;
                axios.post("/alterAdmin", {
                    uid: that.uid,
                    username: that.username,
                    password: that.password,
                    email: that.email,
                    realname: that.realname
                }).then(response => {
                    if (response.data.status) {
                        that.username = '';
                        that.password = '';
                        that.email = '';
                        that.realname = '';
                        that.isShow = true;
                        that.errmesColor = true;
                        that.errmes = response.data.mes;
                        getManagerList.getManagerList();
                    } else {
                        that.isShow = true;
                        that.errmesColor = false;
                        that.errmes = response.data.mes;
                    }
                })
            }
        },
        showErrmes(str) {
            this.errmes = str;
            this.errmesColor = false;
            this.isShow = true;
        }
    }
})