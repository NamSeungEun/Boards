/**
 * 
 */
function getXMLHttpRequest(){
	
	//XMLHttpRequest 객체 생성
if(window.XMLHttpRequest){//Non-Microsoft browser 마이크로소프트사의 브라우저가아니면
		
		return new XMLHttpRequest;
		
	}else if(window.ActiveXObject){//Microsoft browser 마이크로소프트사의 브라우저면.
		
		try {//버전 5.0이후
			
			return new ActiveXObject("Msxml2.XMLHTTP");
			
		} catch (e) {//버전 5.0이전
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
}

//Ajax 요청

var httpRequest = null;

function sendRequest(url,params,callback,method){
	
	httpRequest = getXMLHttpRequest();
	
	//method처리
	var httpMethod = method ? method : "GET";
	
	if (httpMethod!="GET" && httpMethod!="POST") {
		httpMethod = "GET";
	}
	
	//data 처리
	var httpParams = (params==null || params=="") ? null : params;
	
	//url 처리
	var httpUrl = url;
	
	if(httpMethod=="GET" && httpParams!=null){
		
		httpUrl = httpUrl + "?" + httpParams;
	}
	
	httpRequest.open(httpMethod,httpUrl,true);
	httpRequest.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	httpRequest.onreadystatechange = callback;
	httpRequest.send(httpMethod=="POST" ? httpParams : null);
	
	
	
}



























