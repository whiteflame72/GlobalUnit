<html>

<head>
<fbg:resources/>
<title>Default Facebook Redirected Page</title>
</head>

<body>
	Welcome! Actually you should not see this if everything is working :( ...
<%
		  facebookGraphService.publishWall(message:"The message",
		           link:"http://www.example.com/article.html",
		           name:"The name of the link")		
%>	
</body>

</html>