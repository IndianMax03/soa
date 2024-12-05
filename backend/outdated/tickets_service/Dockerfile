FROM quay.io/wildfly/wildfly:33.0.2.Final-jdk21

COPY deploy/module.xml /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/
COPY deploy/postgresql-42.7.4.jar /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/
COPY deploy/standalone.xml /opt/jboss/wildfly/standalone/configuration/

COPY deploy/tickets-service.jks /opt/jboss/wildfly/standalone/configuration/
COPY deploy/tickets-service.crt tickets-service.crt

USER root
RUN keytool -importcert -keystore $JAVA_HOME/lib/security/cacerts -storepass admin_pass -alias tickets-service -trustcacerts -file tickets-service.crt -noprompt
USER jboss

COPY target/tickets_service.war /opt/jboss/wildfly/standalone/deployments/
