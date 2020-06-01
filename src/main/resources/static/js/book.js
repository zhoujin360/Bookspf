//top
var top = new Vue({
    el: "#top",
    methods: {
        logout() {
            axios.post("/logout")
                .then(function() {
                    window.location.reload();
                })
        }
    }
});