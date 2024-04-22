**Docker commands to run the application**

docker run -d \
--name blog-postgres \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=blogdb \
-p 5432:5432 \
postgres:14.10



# **todo list** 

Global exception handling
Logging
layered tests 
docker compose
querydsl for jpa or graphql
mapstruct for mapping 

proper readme file with endpoints and how to run the application
