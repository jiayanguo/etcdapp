FROM java:8
WORKDIR /app
COPY target/etcd*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]