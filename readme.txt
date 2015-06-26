Short

1. Copy your domain into domain and controllers into controllers directories
2. grails> run-app

This sample app is created based on the steps mentioned in the youtube video 
http://www.youtube.com/watch?v=PXHxo43hn34 (Getting Started with Grails - Part 1).

Customization -

1. Validation messages can be modified by changing grails-app/i18n/messages.properties
2. To keep your (test) data, you need to add the domain objects with save/addToXXX/moveFromXXX in grails-app/conf/BootStrap.groovy
3. Modify the look and feel of the scaffolding by following these steps -

a. Stop the app (if it is running)
b. Right click on the Grails project| pick Grails Tools| Open Grails Command Prompt
c. At the grails> install-templates
d. You should see templates/scaffolding directory under src

Scaffolding

http://grails.org/doc/latest/guide/scaffolding.html

Mongodb

. Download it from http://www.mongodb.org/downloads
. Install http://grails.org/plugin/mongodb-morphia plugin
. Create domain by following http://jkuehn.github.com/gorm-mongodb/
. bin/ms.sh

Facebook

Install http://code.google.com/p/spring-security-facebook/downloads/detail?name=spring-security-facebook-1.0.0-ALPHA.jar
http://alexduan.com/tag/spring-security/ - grails facebook-graph plugin is NOT OAuth 2.0!

grails create-tag-lib fbtag

http://alexduan.com/2011/08/04/grails-facebook-logging-in/ 

On linux or OSX, add an entry to /etc/hosts; on windows, edit c:\windows\system32\drivers\etc\hosts. Add an entry like this:
127.0.0.1 local-dev.mydomain.com local-dev
http://stackoverflow.com/questions/8013008/facebook-authentication-unsafe-javascript-attempt-to-access-frame-with-url

Issue:
returned HTTP response code: 400 for URL: https://graph.facebook.com/oauth/access_token
http://developers.facebook.com/docs/authentication/

Spring Security

http://alexduan.com/2011/02/18/setting-up-spring-security-with-grails/
Note:
In order to resolve org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils et. al., one need to invoke -
grails install-plugin spring-security-acl
Close the project and reopen/refresh should fix the compilation error.
