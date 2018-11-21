/**
 * PON监控报告
 */
$(function($) {
	var panels = ["basicInfo", "runningState", "legend", "monitor"];
	for(var index in panels){
		bindClickFunc(panels[index]);
	}
  	var myChart = echarts.init(document.getElementById("mychart"));
  	myChart.showLoading();
	$.get('/static/data/ponData.json', function (ponData) {
	    myChart.hideLoading();
	    option = {
	        tooltip : {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'shadow',
	                label: {
	                    show: true
	                }
	            }
	        },
	        grid: {
	            top: '8%',
	            left: '0%',
	            right: '0%',
	            containLabel: true
	        },
	        calculable : true, //x、y轴是否可计算
	        legend: { //图例说明文字设置
	        	show: true,
	            data:['宽带', 'IPTV'], //需要和series对上才能显示
	            itemGap: 5,
	            x: 'center',
	            y: 'top',
	            selectedMode: 'multiple', //选择模式，默认开启图例开关，可选single，multiple
	        },
	        xAxis: [
	            {
	                type : 'category',
	                //name : '日期',
	                data : ponData.date,
	                axisTick: {
	                	show: false
	                },
	                axisLabel:{
		              	rotate: 270, 
		              	textStyle: {
		                 	fontSize: 12
		              	}
		            }
	            }
	        ],
	        yAxis: [
	            {
	                type : 'value',
	                //name : '数量',
	                axisTick: {
	                	show: true,
	                	inside: true
	                }
	            }
	        ],
	        series : [
	            {
	                name: '宽带',
	                type: 'bar',
	                data: ponData.BD
	            },
	            {
	                name: 'IPTV',
	                type: 'bar',
	                data: ponData.IPTV
	            }
	        ]
	    };
	    myChart.setOption(option);
	});
	
	window.onresize = function(){
       myChart.resize();
    }
});

// 绑定点击事件
function bindClickFunc(name){ 
	$('#' + name).on("click", function(){
		$("#"+ name +"Body").slideToggle();
		var className = $('#' + name + " .expandIcon > span").attr("class");
		if(className == "glyphicon glyphicon-chevron-up"){
			$('#' + name + " .expandIcon > span").attr("class", "glyphicon glyphicon-chevron-down");
		}else{
			$('#' + name + " .expandIcon > span").attr("class", "glyphicon glyphicon-chevron-up");
		}
  	})
}
// 查询城市信息
function getCityList(){
	$.get('/static/data/cities.json', function (data) {
		var cities = data.cities;
		$("#cityList").empty();
		if(cities.length <= 0){
			$("#cityList").append('<option value="">无</option>');
			return;
		}
		for(var index in cities){
			$("#cityList").append('<option value="'+ cities[index] +'">'+ cities[index] +'</option>')
		}
	})
	getAccountList();
}
// 查询账号信息
function getAccountList(){
	$.get('/static/data/account.json', function(data) {
		var accounts = data.accounts;
		$("#accountList").empty();
		for(var index in accounts){
			var account = accounts[index];
			console.log(account);
			$("#accountList").append(
			  '<tr class="gradeX">'
				+ '<td hidden>'+ account.id +'</td>'
				+ '<td>'+ account.area +'</td>'
				+ '<td>'+ account.brasIp +'</td>'
				+ '<td>'+ account.oltIp +'</td>'
				+ '<td>'+ account.oltDport +'</td>'
				+ '<td class="center" style="padding-top: 4px;"><button class="monitorClass">监控</button></td>'
				+ '</tr>');
		}
	})
}
// 分页
function paginationFunc(currentPage, numberOfPages, totalPages){
	$('#paginatorTag').bootstrapPaginator({
        bootstrapMajorVersion: 3, 		//对应的bootstrap版本
        currentPage: currentPage,		//当前页数，这里是用的EL表达式，获取从后台传过来的值
        numberOfPages: numberOfPages, 	//显示页数
        totalPages: totalPages,			//总页数，这里是用的EL表达式，获取从后台传过来的值
        shouldShowPage: true,			//是否显示该按钮
        useBootstrapTooltip: true,
        onPageClicked: function (event, originalEvent, type, page) {
            
        }
    });
}
paginationFunc(1, 3, 20000);

var count = 1;
function refreshPagination(){
	paginationFunc(count++, 3, 20000);
}