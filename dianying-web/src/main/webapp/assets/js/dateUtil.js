//获取当前日志格式为（"YYYY-MM-DD"）
function getDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var yearStr = year+"";
	var monthStr = month+"";
	var dayStr = day+"";

	if(month<10){
		monthStr = "0"+month;
	}
	if(day<10){
		dayStr = "0"+day;
	}
	var result = yearStr+"-"+monthStr+"-"+dayStr;
	return result;
	
}

function skip(url){
	window.location.href = url+"&&startDate="+getDate()+"&&endDate="+getDate();
}
