docker kill tickets_service main-db booking_service
docker rm tickets_service main-db booking_service
docker rmi backend-tickets-service backend-booking-service postgres
docker compose up
