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

var isAction = false;
var addAdmin = document.getElementById("addAdmin");
var block = document.getElementById("actionBlock");

function action() {
    this.isAction = !this.isAction;
    if (isAction) {
        addAdmin.className = "actionShow";
        block.className = "actionShow";
    } else {
        addAdmin.className = "actionHidden";
        block.className = "actionHidden";
    }
}