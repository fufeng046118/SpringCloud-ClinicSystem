FROM java:8

COPY *.jar /app.jar

CMD ["--server.port=7001"]

EXPOSE 7001

ENTRYPOINT ["java","-jar","/app.jar"]