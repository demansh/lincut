mvn clean install -DskipTests
cd application
mvn spring-boot:run -Psql -Dspring-boot.run.profiles=sql
