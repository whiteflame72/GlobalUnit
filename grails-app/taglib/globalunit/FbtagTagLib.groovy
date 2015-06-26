package globalunit

import grails.converters.JSON

class FbtagTagLib {
//	static namespace = "fcbk"
//	
//	  def resources = {attrs, body ->
//		if(!grailsApplication.config.facebook.applicationId) {
//		  out << "<div>facebook.applicationId not defined in the Config.groovy!</div>"
//		} else {
//		  out << render(template:"/tags/facebookResources")
//		}
//	  }
	//=== reference: http://grails.org/plugin/facebook-graph
	def facebookGraphService
	def fbInfo = { attrs ->
		if (session.facebook) {
		  def myInfo = JSON.parse (facebookGraphService.getFacebookProfile().toString() )

		  out << "<br/>id" << myInfo.id
		  out << "<br/>first_name:" << myInfo.first_name
		  out << "<br/>Last Name:" << myInfo.last_name
		  out << "<br/>Gender:" << myInfo.gender
		  out << "<br/>Timezone:" << myInfo.timezone
		  out << "<br/>Home Town:" << myInfo.hometown
		  out << "<br/>Link:" << myInfo.link
		  out << "<br/>Photo:" << "<img src='${facebookGraphService.getProfilePhotoSrc(myInfo.id);}'/>"

		  out << "<br/>Friends:" << facebookGraphService.getFriends().toString()

		} else {
		  out << "Not logged in to Facebook"
		}
	}
}
