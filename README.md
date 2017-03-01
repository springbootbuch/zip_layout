# zip_layout

This is an example how to use zip_layout with an actual use case: You have a dependency on a license restricted file (here: Oracle JDBC driver), that needs to be excluded from the fat-jar.

First, you define those dependencies as `runtime`, so that you can test against them:

```
<dependency>
    <groupId>com.oracle.jdbc</groupId>
    <artifactId>ojdbc7</artifactId>
    <version>${oracle.version}</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>com.oracle.jdbc</groupId>
    <artifactId>orai18n</artifactId>
    <version>${oracle.version}</version>
    <scope>runtime</scope>
</dependency>
```

Next, configure the `spring-boot-maven-plugin` and exclude those from the fat-jar. Also: change the jar-layout to ZIP. You'll still get a JAR file, but one that doesn't use the `JarLauncher`, but the `PropertiesLauncher` that provides some more interesting features, among them `loader.path`. `loader.path` can be defined through environment or Java System property and provides a list of external libraries, that are added to the classpath. This argument cannot be specified as program argument.

To test this, configure your Oracle connection in application.properties and run the app with

```
java -Dloader.path=target/dependency/ojdbc7-12.1.0.2.jar,target/dependency/oraclepki-12.1.0.2.jar,target/dependency/orai18n-12.1.0.2.jar,target/dependency/osdt_cert-12.1.0.2.jar,target/dependency/osdt_core-12.1.0.2.jar -jar target/zip_layout.jar
```

(The POM has the Oracle repositories configured and you'll get the dependencies out of it with `./mvnw dependency:copy-dependencies`)

and it will print the title of a famous Austrian song.

See [springbootbuch.de](http://info.michael-simons.eu/tag/spring-boot-buch/) for more about the upcoming Spring Boot Buch.