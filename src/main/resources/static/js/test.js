var uploadBookimg = new Vue({
    el: '#test',
    data: {
        file: ''
    },
    methods: {
        fileChange(event) {
            var that = this;
            that.file = event.target.files[0];
            let param = new FormData();
            param.append("file", that.file);
            let config = { headers: { "Content-Type": "multipart/form-data" } };
            axios.post("/uploadBookimg", param, config)
                .then(response => {
                    console.log(response);
                })
        }
    }
});