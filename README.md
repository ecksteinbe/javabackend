Masterthesis JavaBackend
========================

Logback/Glassfish Configuration
-------------------------------
1. copy these jars from src/main/setup/glassfish/ into `%GLASSFISH_HOME%/glassfish/lib/endorsed`
   * jul-to-slf4j.jar
   * slf4j-api.jar
   * logback-classic.jar
   * logback-core.jar
2. copy `hibernate3.jar` from src/main/setup/glassfish/ into `%GLASSFISH_HOME%/glassfish/lib`
3. copy `src/main/setup/logback.xml` into `%GLASSFISH_HOME%/glassfish/domains/domain1/config/`
4. copy `src/main/setup/custom_logging.properties` into `%GLASSFISH_HOME%/glassfish/domains/domain1/config/`
5. add following lines into `domain.xml` (`<java-config>...</java-config>`)

        <jvm-options>-Dlog4j.configuration=file:///${com.sun.aas.instanceRoot}/config/custom_logging.properties</jvm-options>
        <jvm-options>-Dlogback.configurationFile=file:///${com.sun.aas.instanceRoot}/config/logback.xml</jvm-options>

6. restart server

Glassfish setup datasource
--------------------------
1 download MySQL-connector:
  * `http://dev.mysql.com/downloads/connector/j/`
  * cp `mysql-connector-java-5.1.21-bin.jar` into `%GLASSFISH_HOME%/glassfish/lib`
   
2 setup JDBC Connection Pool
  * open `http://localhost:4848` Common Tasks -> Resources -> JDBC -> JDBC Connection Pools
  * Fill in the following information:

        Name: backend
        Resources Type: javax.sql.DataSource
        Database Vendor: MySQL        
  * click next
  * Additional Properties:

        databaseName: backend
        User: backend
        Password: backend
        ServerName: localhost        
  * click finish
   
3 setup JDBC Resource

  * Common Tasks -> Resources -> JDBC -> JDBC Connection Resources
  * click new

        JNDI Name: jdbc/backendDS
        Pool Name: backend
  * click ok to save

Application Configuration
-------------------------
* cp src/main/resources/backend.template.properties ${user.home}/backend.properties
* Insert your individual Settings in ${user.home}/backend.properties
* Example Path ${user.home}: "C:\Users\MATHAF"

If you prefer to store your property-file somewhere else, you can configure a 
different path at: src/main/resources/backend_config.xml

REST-Calls: 
-----------

1. **Receive Places (GOOGLE API - format)**

        curl -k -i -H "Accept: application/json" --basic -u franz:123 -X GET "https://localhost:8181/JavaBackend/rest/secure/places?location=48.13661,11.57709"

2. **Receive Transactions**

   2.1 Successful:

        curl -k -i -H "Accept: application/json" --basic -u franz:123 -X GET "https://127.0.0.1:8181/JavaBackend/rest/secure/bankaccount/1/transactions"
    
   2.2 Error:

        curl -k -i -H "Accept: application/json" --basic -u franz:123 -X GET "https://127.0.0.1:8181/JavaBackend/rest/secure/bankaccount/2/transactions"

NetBeans Setup:
---------------
1. BUILD process
    
        Set Java JDK 1.6 in Project Properties -> Build -> Compile


Install dependencies to local repository:
-----------------------------------------

* mvn install:install-file -DgroupId=com.paypal -DartifactId=merchant-java-sdk -Dpackaging=jar -Dversion=1.2.95 -Dfile=src\main\setup\maven\merchant-java-1.2.95\dist\merchant-java-sdk_1.2.95.jar -DgeneratePom=true

* mvn install:install-file -DgroupId=com.paypal.sdk -DartifactId=paypal-auth-signature -Dpackaging=jar -Dversion=1.0 -Dfile=src\main\setup\maven\paypal-auth-signature-1.0.jar -DgeneratePom=true

* mvn install:install-file -DgroupId=com.google.gdata -DartifactId=gdata-client -Dpackaging=jar -Dversion=1.0 -Dfile=src\main\setup\maven\gdata-client-1.0.jar -DgeneratePom=true

* mvn install:install-file -DgroupId=com.google.gdata -DartifactId=gdata-youtube -Dpackaging=jar -Dversion=2.0 -Dfile=src\main\setup\maven\gdata-youtube-2.0.jar -DgeneratePom=true

* mvn install:install-file -DgroupId=com.google.gdata -DartifactId=gdata-core -Dpackaging=jar -Dversion=1.0 -Dfile=src\main\setup\maven\gdata-core-1.0.jar -DgeneratePom=true

* mvn install:install-file -DgroupId=com.google.gdata -DartifactId=gdata-media -Dpackaging=jar -Dversion=1.0 -Dfile=src\main\setup\maven\gdata-media-1.0.jar -DgeneratePom=true
