var top = new Vue({
    el: "#top",
    data: {
        bookname: ''
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
            axios.post("/search", {
                bookname: that.bookname
            }).then(response => {
                if (response.data.status) {
                    window.location.replace(response.data.mes);
                } else {
                    alert(response.data.mes);
                }
            })
        }
    }
});