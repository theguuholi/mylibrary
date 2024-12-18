# build
# hub.docker.com para pegar o registries utilizar o maven
FROM maven:3.9.9-amazoncorretto-23-alpine as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# run
FROM amazoncorretto:23-jdk
WORKDIR /app
COPY --from=build ./build/target/*.jar ./app.jar
EXPOSE 8080
EXPOSE 9001

ENV DATASOURCE_URL=''
ENV DATASOURCE_USERNAME=''
ENV DATASOURCE_PASSWORD=''
ENV GOOGLE_CLIENT_ID=''
ENV GOOGLE_CLIENT_SECRET=''
ENV SPRING_PROFILES_ACTIVE='production'
ENV TZ='America/Sao_Paulo'

ENTRYPOINT ["java", "-jar", "app.jar"]