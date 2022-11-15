# template_Servlet_Jsp

Template for web backends using servlet/jsp. Brings a tomcat.

Using:
 
  * ant for build
  * ivy for dependencies
  * commons-cli for command line
  * groovy for showing build results
  * slf4j for logging
  
Check out Servlet.java and index.jsp for web app starting points.

## Usage

Show help:

```
ant help
```

Start web app in tomcat:

```
ant web.start
```

Stop tomcat:

```
ant web.stop
```
