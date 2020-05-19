var uploadFile=new Vue({
	el: "#tt",
	methods: {
		upload : function(){
			var file = document.getElementById("tf");
			var formdata=new FormData(file);
			let config={ headers:{'Content-Type':'multiline/form-data'} };
			axios.post("/uploadFile",formdata,config)
			.then(response=>{
				console.log(response);
			})
		}
	}
});