FROM quay.io/quarkus/quarkus-micro-image:2.0
COPY target/*-runner /work/application
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
