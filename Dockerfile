FROM amazoncorretto:11 as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ddd_common-v1.0.0.jar ./
RUN ./mvnw deploy:deploy-file -Durl=file:./src/main/resources/repo -Dfile=ddd_common-v1.0.0.jar -DgroupId=org.tomo -DartifactId=ddd_common -Dpackaging=jar -Dversion=1.0.0
RUN ./mvnw dependency:resolve


FROM base as test
RUN ./mvnw dependency:resolve-plugins -Punittest,integrationtest
COPY src ./src
ADD "https://www.random.org/cgi-bin/randbyte?nbytes=10&format=h" skipcache
CMD tail -f /dev/null

FROM base as build
COPY src ./src
RUN ./mvnw package
