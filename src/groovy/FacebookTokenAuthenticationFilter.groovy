import grails.plugins.springsecurity.SpringSecurityService
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.facebook.FacebookAuthenticationToken
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

/*
class FacebookTokenAuthenticationFilter extends  AbstractAuthenticationProcessingFilter {
	def grailsApplication
	FacebookService facebookService
	SpringSecurityService springSecurityService
  
	public static final String FACEBOOK_SECURITY_FORM_ACCESS_TOKEN_KEY = "j_facebooktoken"
  
	private boolean postOnly = true
  
	public FacebookTokenAuthenticationFilter() {
	  super("/j_facebook_security_check")
	}
  
	@Override
	public void afterPropertiesSet() {
	  super.setFilterProcessesUrl(grailsApplication.config.facebook.security.filter.url)
	}
  
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,  HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
	  if (postOnly && !request.getMethod().equals("POST")) {
		throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod())
	  }
  
	  String accessToken = request.getParameter(FACEBOOK_SECURITY_FORM_ACCESS_TOKEN_KEY)
	  def profile = facebookService.getProfile(accessToken)
	  if(profile) {
		//this is where your logic goes...
		return this.getAuthenticationManager().authenticate(new FacebookAuthenticationToken(profile.id, accessToken))
	  }
  
	  return null;
	}
}
*/