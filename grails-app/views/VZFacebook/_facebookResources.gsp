<!-- source: http://alexduan.com/tag/spring-security/ -->
<div id="fb-root"></div>
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script>
  window.fbAsyncInit = function() {
    FB.init({appId: '${grailsApplication.config.facebook.applicationId}', status:true, cookie:true, xfbml:true, oauth:true, channel:'http://www.alexduan.com:8080/channel'});
    function updateButton(response) {
      var button = document.getElementById('fb-auth');

      if (response.authResponse) {
        //user is already logged in and connected
        var userInfo = document.getElementById('user-info');
        FB.api('/me', function(response) {
          userInfo.innerHTML = '<img src="https://graph.facebook.com/'
              + response.id + '/picture">' + response.name;
          button.innerHTML = 'Logout';
        });
        button.onclick = function() {
          FB.logout(function(response) {
            var userInfo = document.getElementById('user-info');
            userInfo.innerHTML="";
          });
        };
      } else {
        //user is not connected to your app or logged out
        button.innerHTML = 'Login';
        button.onclick = function() {
          FB.login(function(response) {
            if (response.authResponse) {
              FB.api('/me', function(response) {
                var userInfo = document.getElementById('user-info');
                userInfo.innerHTML = '<img src="https://graph.facebook.com/'
                + response.id + '/picture" style="margin-right:5px"/>'
                + response.name;
              });
            } else {
              //user cancelled login or did not grant authorization
            }
          }, {scope:'email'});
        }
      }
    }
    FB.getLoginStatus(updateButton);
    FB.Event.subscribe('auth.statusChange', updateButton);
    FB.Event.subscribe('auth.login', function(response) {
      $('<input>').attr({type:'hidden',
        id:'accessToken',
        name:'${com.alexduan.services.security.FacebookTokenAuthenticationFilter.FACEBOOK_SECURITY_FORM_ACCESS_TOKEN_KEY}',
        value:response.authResponse.accessToken}).appendTo('#hiddenFacebookLoginForm');
      $('#hiddenFacebookLoginForm').submit();
     });
  };
  (function() {
    var e=document.createElement('script');
    e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
    e.async = true;
    document.getElementById('fb-root').appendChild(e);
  }());
</script>
<form action="${grailsApplication.config.facebook.security.filter.url}" method='POST' id="hiddenFacebookLoginForm"></form>