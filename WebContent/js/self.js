/*$("#c1").click(function(){
	alert("sdfds");
	$("#s1").removeAttr("readOnly");
	$("#s2").removeAttr("readOnly").attr("type","text");
	$("#s3").removeAttr("readOnly");
	$("#s4").removeAttr("readOnly");
	$("#s5").removeAttr("readOnly");
	$("#s6").removeAttr("readOnly");
});
		
$("#c3").click(function(){
	alert("12345");
	$("#s7").removeAttr("readOnly");
	$("#s8").removeAttr("readOnly").attr("type","text");
	$("#s9").removeAttr("readOnly");
	$("#s10").removeAttr("readOnly");
	$("#s11").removeAttr("readOnly");
	$("#s12").removeAttr("readOnly");
});
		
		
//获取页面的每个按钮
var btns = document.getElementsByClassName("btn");
//获取内容盒子
var contents = document.getElementsByClassName("rtcont");
　　　　　//遍历每个按钮为其添加点击事件
   		for(var i=0;i<btns.length;i++){
            btns[i].index = i;
            btns[i].onclick = function(){
　　　　　　　　　　//对当前点击的按钮赋予active类名及显示当前按钮对应的内容
                for(var j=0;j<btns.length;j++){
					btns[j].className = btns[j].className.replace(' active', '').trim();
                    contents[j].className = contents[j].className.replace(' show', '').trim();
                }
				this.className = this.className + ' active';
                contents[this.index].className = contents[this.index].className + ' show';
            };
        }
*/

let vm = new Vue({
	el:"#app",
	data:{
		goods:[],
		types:[]
	},
	methods:{
		
	},
	mounted:function(){//页面加载完执行的方法
	
		axios.post("goods",qs.stringify({op:"findIndex"})).then(result =>{
			if(result.data.code ==200){
				this.types = result.data.data.types;
				this.goods = result.data.data.goods;
			}
		})
	}
	
})

function Search(){
	var pname="";
	pname = $.trim($(".search").val());
	if(pname==""){
		return;
	}
	localStorage.removeItem("pname");
	localStorage.setItem("pname",pname);
	location.href="liebiao.html";	
	
}
