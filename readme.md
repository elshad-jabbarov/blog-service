# Blog Service Application




### Prerequisites

What things you need to install the software and how to install them:

- Docker
- Java JDK 17
- Maven (if running outside Docker)

### Installing

A step-by-step series of examples that tell you how to get a development environment running.

#### Start with Docker

First, pull and run the PostgreSQL image:

```bash
docker run -d \
--name blog-postgres \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=blogdb \
-p 5432:5432 \
postgres:14.10
