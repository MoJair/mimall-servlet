let login = new Vue({
	el: "#login_info",
	data: {
		onlogin: false,
		loginName: "匿名",
		loginId: "",
		cartCount: 0
	},
	mounted: function(){
		axios.get("member", {params:{op:"info"}}).then(result => {
			//console.info(result);
			if(result.data.code == 200) {
				this.onlogin = true;
				this.loginName = result.data.data.uname;
				this.loginId = result.data.data.uid;
			} else {
				this.onlogin = false;
			}
		})
	}
})