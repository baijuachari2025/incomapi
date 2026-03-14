# incomapi
Incom API - coding challenge

## 1. Getting started

clone the repo, build and run API
- git clone https://github.com/baijuachari2025/bussale-api.git

- run docker to launch postgres and pgadmin
- mvn clean install spring-boot:run
- the applicaiton will be runnign at localhost:8080

## 2. Database access

Open PGAdmin at http://localhost:5050  
Login with admin@admin.com / admin  
Right-click "Servers" → "Register" → "Server"  
Fill in the connection details:  
General tab → Name: anything (e.g. local)  
Connection tab:  
Host: postgres (the service name, NOT localhost)  
Port: 5432  
Database: baiju  
Username: myuser  
Password: secret

## 3. Docker

docker compose up  
docker compose down [-v optional to remove]

To remove:

docker volume ls  
docker volume rm  [image ID]

## 4. Swagger

Access Swagger UI at: http://localhost:8080/swagger-ui/index.html  




