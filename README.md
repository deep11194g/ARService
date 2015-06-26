##Web Service for AugmentedReality



#####Technologies used:
* Language: Java
* Framework: Struts2
* DB: MongoDB
* Service type: RESTful(Jersey implementation)

_WAR file present in dist folder(for deployment)_


#####Database structure:
* __DB name__: AugReality
* __Collections__: login, devices
* __Description__:
	* _login_ - Collection holds login credentials(username and password) of users 
	* _device_ - Collection holds a document for every user. Each document has an array of device details.

	
#####MongoDB setup:
* Install MongoDB
* Default port: 27017
* Run MongoDB instance(mongod.exe)
* Use MongoShell(mongo.exe) or [RoboMongo](http://robomongo.org/) tool
* MongoDB [documentation](http://docs.mongodb.org/manual/)


#####Web Service Methods:
* __getJson()__: Get the "data.json" file containing device details for the given user. 
	* __*Type: GET*__
	* __*URL*__: http://localhost:8084/AugmentedRealityService/webresources/generic/<username>
* __postDevice()__: Receive form field values via PUT request and enter a device document in DB collection.
	* __*Type: PUT*__
	* __*URL*__: http://localhost:8084/AugmentedRealityService/webresources/generic/add
* __editDevice()__: Receive updated location values and update existing records in DB collection.
	* __*Type: POST*__
	* __*URL*__: http://localhost:8084/AugmentedRealityService/webresources/generic/edit

	
#####About Struts framework:
* Struts is an MVC framework of Java
* These links will give a clear idea of a basic Struts app: 
	* https://netbeans.org/kb/docs/web/quickstart-webapps-struts.html
	* http://www.quickprogrammingtips.com/struts2/struts-2-netbeans-tutorial.html
