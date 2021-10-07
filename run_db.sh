mvn clean install -DskipTests
cd application
mvn spring-boot:run -P sql -Dspring.profiles.active=sql
