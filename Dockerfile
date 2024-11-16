FROM quay.io/wildfly/wildfly:latest-jdk17
COPY target/logon.war /opt/jboss/wildfly/standalone/deployments/
