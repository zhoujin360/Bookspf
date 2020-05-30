var order = new Vue({
    el: '#orders',
    data: {
        orderList: []
    },
    mounted: (
        function() {
            var that = this;
            axios.post("/orders")
                .then(response => {
                    that.orderList = response.data.orders;
                })
        }
    )
});