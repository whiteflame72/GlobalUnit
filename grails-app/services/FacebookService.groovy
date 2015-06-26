class FacebookService {
	final static def GRAPH_URL = "https://graph.facebook.com/"
	def getProfile(String accessToken) {
	  if(accessToken) {
		def urlString = GRAPH_URL + "me/?access_token=" + accessToken
  
		log.debug urlString
		URL url = new URL(urlString)
		JSON.parse(url.getText())
	  }
	}
}