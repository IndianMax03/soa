#!/bin/bash

docker kill booking-service booking-service-rest api-gateway-server eureka-server tickets-service config-server consul main-db
docker rm booking-service booking-service-rest api-gateway-server eureka-server tickets-service config-server consul main-db
docker rmi backend-tickets-service backend-booking-service backend-booking-service-rest backend-config-server backend-eureka-server backend-api-gateway postgres consul
docker compose up
