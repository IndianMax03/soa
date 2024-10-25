FROM payara/server-full

COPY deploy/booking-service.jks /opt/payara/appserver/glassfish/domains/domain1/config
COPY deploy/tickets-service.crt tickets-service.crt

USER root
RUN keytool -importcert -keystore /opt/payara/appserver/glassfish/domains/domain1/config/cacerts.p12 -storepass changeit -alias tickets-service -trustcacerts -file tickets-service.crt -noprompt
USER payara

COPY target/booking_service.war /opt/payara/deployments
