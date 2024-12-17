FROM openjdk:11
COPY target/booking-service-1.jar booking-service-1.jar

COPY deploy/booking-service.jks booking-service.jks
COPY deploy/tickets-service.crt tickets-service.crt

USER root
RUN keytool -importcert -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -alias booking-service -trustcacerts -file tickets-service.crt -noprompt

ENTRYPOINT ["java", "-jar", "booking-service-1.jar"]
