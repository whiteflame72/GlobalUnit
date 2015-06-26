<%@ page import="globalunit.VZFacebook" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'VZFacebook.label', default: 'VZFacebook')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
<fbg:resources/>
<script type="text/javascript">
//      appId   : '178871652217601',
FB.init({
      appId   : '372088749483379',
      status  : true,
      cookie  : true,
      xfbml   : true,
      oauth: true	//since Oct 1, 2011
    });
	//check permission http://www.masteringapi.com/tutorials/how-to-check-if-user-has-certian-permission-facebook-api/22/
	FB.api({ method: 'fql.query', query: 'SELECT read_stream,offline_access,publish_stream FROM permissions WHERE uid=me()' }, function(resp) {
	    for(var key in resp[0]) {
	        if(resp[0][key] === "1") {
	            //console.log(key+' is granted')
	            alert(key+' is granted')
	        } else {
	            //console.log(key+' is not granted')
	            alert(key+' is not granted')
	        }
	    }
	});
</script>
<script type="text/javascript">
function facebookLogin() {
	FB.getLoginStatus(function(response) {
		if (response.authResponse) {
			window.location ="${createLink(controller:'login', action:'facebookLogin')}";
		}
	});
}
<% 
	def urlString =  request.scheme + "://" + request.serverName  + ":" + request.serverPort + "/" + grailsApplication.metadata.'app.name' + "/" + controllerName + "/"
%>
FB.Event.subscribe("auth.logout", function() {window.location = '${urlString}'});
//FB.Event.subscribe('auth.statusChange', updateButton);
</script>
	</head>
	<body>
		<a href="#list-VZFacebook" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li>
<fb:login-button scope="email,user_about_me,read_stream,publish_stream" autologoutlink="true" onlogin="facebookLogin();" >
</fb:login-button>
				</li>
			</ul>
		</div>
		<div id="list-VZFacebook" class="content scaffold-list" role="main">
<g:fbInfo/>
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${VZFacebookInstanceList}" status="i" var="VZFacebookInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${VZFacebookInstanceTotal}" />
			</div>
		</div>
	</body>
	
</html>
