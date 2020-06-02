var top = new Vue({
    el: "#top",
    data: {
        bid: ''
    },
    methods: {
        logout() {
            axios.post("/logout")
                .then(function() {
                    window.location.reload();
                })
        },
        search() {
            var that = this;
            alert(that.bid)
            axios.post("/search", {
                bid: that.bid
            })
        }
    }
});