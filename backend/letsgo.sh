docker kill booking-service api-gateway-server eureka-server tickets-service config-server consul main-db
docker rm booking-service api-gateway-server eureka-server tickets-service config-server consul main-db
docker rmi backend-tickets-service backend-booking-service backend-config-server backend-eureka-server backend-api-gateway postgres consul
docker compose up
