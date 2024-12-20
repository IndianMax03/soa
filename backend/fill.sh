#!/bin/bash

# tickets
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Славный", "coordinates": {"x": 12.1,"y": 11.1},"price": 600,"type": "VIP","venue": {"name": "Полянка","capacity": 550,"type": "BAR","address": {"zipCode": "8800-555-3535"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Могучий", "coordinates": {"x": 76.6,"y": 17.22},"price": 400,"type": "BUDGETARY","venue": {"name": "Смуглянка","capacity": 200,"type": "BAR","address": {"zipCode": "3-14-15-92-6"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Прекрасный", "coordinates": {"x": 4.8,"y": 99.44},"price": 200,"type": "CHEAP","venue": {"name": "Картридер","capacity": 63,"type": "BAR","address": {"zipCode": "6-18-22-112-3"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Великий", "coordinates": {"x": 223.9,"y": 654.16},"price": 200,"type": "CHEAP","venue": {"name": "Дмесг","capacity": 82,"type": "BAR","address": {"zipCode": "89191-112-471"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Мудрый", "coordinates": {"x": 452.1,"y": 31.1},"price": 200,"type": "CHEAP","venue": {"name": "Корнелиус","capacity": 12,"type": "BAR","address": {"zipCode": "8481-993-1920"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Гуччи", "coordinates": {"x": 4349.670205771923,"y": 330.1898894967539},"price": 2000,"type": "VIP","venue": {"name": "Бублибу","capacity": 314,"type": "CINEMA","address": {"zipCode": "2389-60958-123"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Прада", "coordinates": {"x": 7015.584816216965,"y": 3394.0882992127563},"price": 2000,"type": "VIP","venue": {"name": "Просеко","capacity": 15,"type": "CINEMA","address": {"zipCode": "12309-123-3141"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Луи", "coordinates": {"x": 4967.97694465325,"y": 2662.834362671055},"price": 2000,"type": "VIP","venue": {"name": "Аэротруба","capacity": 92,"type": "CINEMA","address": {"zipCode": "198427-12938-123"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Фенди", "coordinates": {"x": 5174.834623156434,"y": 5515.016719878412},"price": 2000,"type": "VIP","venue": {"name": "Палладинова пята","capacity": 61,"type": "CINEMA","address": {"zipCode": "9348-129381-12"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Бэлэнс", "coordinates": {"x": 6000.015964780268,"y": 751.0348151907334},"price": 2000,"type": "VIP","venue": {"name": "Крикет таймлапс","capacity": 88,"type": "CINEMA","address": {"zipCode": "12398-09410"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Вечерний", "coordinates": {"x": 10.2,"y": 30.4},"price": 1000,"type": "VIP","venue": {"name": "Звездная","capacity": 700,"type": "BAR","address": {"zipCode": "1234-5678-9012"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Веселый", "coordinates": {"x": 99.9,"y": 1.1},"price": 500,"type": "BUDGETARY","venue": {"name": "Солнечная","capacity": 300,"type": "BAR","address": {"zipCode": "9876-5432-1011"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Романтический", "coordinates": {"x": 23.4,"y": 56.7},"price": 1500,"type": "VIP","venue": {"name": "Луна","capacity": 400,"type": "CINEMA","address": {"zipCode": "1000-1111-2222"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Спортивный", "coordinates": {"x": 78.9,"y": 23.1},"price": 800,"type": "BUDGETARY","venue": {"name": "Стадион","capacity": 1000,"type": "BAR","address": {"zipCode": "3333-4444-5555"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Праздничный", "coordinates": {"x": 45.6,"y": 78.9},"price": 2000,"type": "VIP","venue": {"name": "Праздник","capacity": 500,"type": "CINEMA","address": {"zipCode": "6666-7777-8888"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Детский", "coordinates": {"x": 12.3,"y": 45.6},"price": 600,"type": "BUDGETARY","venue": {"name": "Сказочный","capacity": 200,"type": "BAR","address": {"zipCode": "9999-0000-1111"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Ночной", "coordinates": {"x": 56.7,"y": 12.3},"price": 1200,"type": "VIP","venue": {"name": "Ночной клуб","capacity": 800,"type": "CINEMA","address": {"zipCode": "2222-3333-4444"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Музыкальный", "coordinates": {"x": 89.0,"y": 67.8},"price": 900,"type": "BUDGETARY","venue": {"name": "Концертный зал","capacity": 1200,"type": "BAR","address": {"zipCode": "5555-6666-7777"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Театральный", "coordinates": {"x": 34.5,"y": 23.4},"price": 1800,"type": "VIP","venue": {"name": "Театр","capacity": 600,"type": "STADIUM","address": {"zipCode": "8888-9999-0000"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Танцевальный", "coordinates": {"x": 21.1,"y": 78.9},"price": 700,"type": "BUDGETARY","venue": {"name": "Танцпол","capacity": 400,"type": "BAR","address": {"zipCode": "1111-2222-3333"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Свадебный", "coordinates": {"x": 98.7,"y": 56.4},"price": 3000,"type": "VIP","venue": {"name": "Замок","capacity": 1000,"type": "STADIUM","address": {"zipCode": "4444-5555-6666"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Корпоративный", "coordinates": {"x": 45.6,"y": 12.3},"price": 1600,"type": "BUDGETARY","venue": {"name": "Офис","capacity": 500,"type": "BAR","address": {"zipCode": "7777-8888-9999"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Гастрономический", "coordinates": {"x": 67.8,"y": 34.5},"price": 2500,"type": "VIP","venue": {"name": "Ресторан","capacity": 300,"type": "CINEMA","address": {"zipCode": "0000-1111-2222"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Фестивальный", "coordinates": {"x": 10.1,"y": 98.7},"price": 1100,"type": "BUDGETARY","venue": {"name": "Площадь","capacity": 2000,"type": "BAR","address": {"zipCode": "3333-4444-5555"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Экстремальный", "coordinates": {"x": 56.7,"y": 21.1},"price": 1900,"type": "VIP","venue": {"name": "Парк","capacity": 700,"type": "STADIUM","address": {"zipCode": "6666-7777-8888"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Исторический", "coordinates": {"x": 78.9,"y": 45.6},"price": 800,"type": "BUDGETARY","venue": {"name": "Музей","capacity": 300,"type": "BAR","address": {"zipCode": "9999-0000-1111"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Культурный", "coordinates": {"x": 34.5,"y": 67.8},"price": 2200,"type": "VIP","venue": {"name": "Галерея","capacity": 500,"type": "CINEMA","address": {"zipCode": "2222-3333-4444"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Образовательный", "coordinates": {"x": 23.4,"y": 12.3},"price": 900,"type": "BUDGETARY","venue": {"name": "Университет","capacity": 1000,"type": "BAR","address": {"zipCode": "5555-6666-7777"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Религиозный", "coordinates": {"x": 89.0,"y": 56.7},"price": 1500,"type": "VIP","venue": {"name": "Храм","capacity": 400,"type": "STADIUM","address": {"zipCode": "8888-9999-0000"}}}'
curl -o /dev/null -X POST http://localhost:9912/tickets -H "Content-Type: application/json" -k -d '{"name": "Экологический", "coordinates": {"x": 12.3,"y": 21.1},"price": 600,"type": "BUDGETARY","venue": {"name": "Лес","capacity": 200,"type": "BAR","address": {"zipCode": "1111-2222-3333"}}}'

# persons
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "maxim","password": "maxmax", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "ksenia","password": "kiskis", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "viktor","password": "vikvik", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "ivan","password": "iviv", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "anton","password": "antant", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "igor","password": "igig", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "ilya","password": "ilil", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "kate","password": "kaka", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "artem","password": "artart", "balance": 10000}'
curl -o /dev/null -X POST http://localhost:9912/persons -H "Content-Type: application/json" -k -d '{"username": "copa","password": "copcop", "balance": 10000}'

# sell
curl -o /dev/null -X POST http://localhost:9912/booking/sell/1/1/600 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/5/2/200 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/10/3/2000 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/15/4/2000 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/20/5/700 -k

curl -o /dev/null -X POST http://localhost:9912/booking/sell/2/6/400 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/6/7/2000 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/11/8/1000 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/16/9/600 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/21/10/3000 -k

curl -o /dev/null -X POST http://localhost:9912/booking/sell/3/1/200 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/7/1/2000 -k
curl -o /dev/null -X POST http://localhost:9912/booking/sell/12/1/500 -k
