//JavaScript Document
window.onload = function(){
	var proObj = document.getElementById("province");	//获取省份下拉对象
	var cityObj = document.getElementById("city");	//获取城市下拉对象
	var areaObj = document.getElementById("area");	//获取县、地区下拉对象
	
	//获取xml文件
	var xmlhttp;
	if(window.XMLHttpRequest){	//IE7 、Firefox、chorme、Opera等浏览器
		xmlhttp = new XMLHttpRequest();
	}else{	//低版本IE浏览器
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.open("GET", "city.xml", true);	//发送一个请求
	xmlhttp.send(null);	//发送请求参数，null说明没有参数
	
	var dom;
	xmlhttp.onreadystatechange = function(){	//回调函数
		if(this.readyState == 4 && this.status == 200){
			dom = xmlhttp.responseXML.documentElement;
			
			//获取所有省份
			var pros = dom.getElementsByTagName("province");
			
			for(var i = 0, len = pros.length; i < len; i++){
				addOption(pros[i],proObj);
			}
			
			var cities;
			
			//当省份发生改变时，城市里面的值也要相应改变
			proObj.onchange = function(){
				//获取当前选中的省份
				var flag = proObj.value;
				
				//清空城市中原有的值
				cityObj.length = 1;
				
				//根据这个值找到对应的节点node
				for(var i = 0, len = pros.length; i < len; i++){
					if(pros[i].nodeType == 1 && pros[i].getAttribute("name") == flag){
						//取出这个节点下的所有的城市city
						//cities = pros[i].childNodes;
						cities = pros[i].getElementsByTagName("city");
						
						for(var j = 0, len1 = cities.length; j < len1; j++){
							addOption(cities[j], cityObj);
						}
						//触发城市改变的事件
						cityObj.onchange();
						break;
					}
				}
			}
			
			//当城市发生改变的时候，地区也要相应发生改变
			cityObj.onchange = function(){
				var flag = cityObj.value;
				
				//清空地区
				areaObj.length = 1;
				
				var areas;
				for(var i = 0, len = cities.length; i < len; i++){
					if(cities[i].nodeType == 1 && cities[i].getAttribute("name") == flag){
						areas = cities[i].getElementsByTagName("area");
						for(var j = 0, len1 = areas.length; j < len1; j++){
							addOption(areas[j],areaObj);
						}
						break;
					}
				}
			}
		}
	};
}

//第一个参数是要添加得到节点，第二个是添加到那个下拉的对象中
function addOption(node, obj){
	if(node.nodeType == 1){	//如果是元素节点
		var opt = new Option();
		var txt = node.getAttribute("name");	//node.name
		opt.value = txt;
		opt.text =txt;
		obj.appendChild(opt);
	}
}