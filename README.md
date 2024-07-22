
# Loan Grant Service

## Descrição do Projeto
Projeto para realização de empréstimos.

## Tecnologias Utilizadas
- Spring Boot
- Spring Data
- Spring MVC
- Java 11

## Instruções de Instalação
Para instalar e iniciar o serviço, utilize o comando:
```bash
docker compose up --build
```

## Instruções de Uso
### Criar Pessoa
#### Request
```http
POST /api/person
```
#### Body
```json
{
    "name": "User test",
    "identifier": "12345678901",
    "birthDate": "1990-05-15"
}
```
#### Response
```http
201 CREATED
Content-Type: application/json
```
```json
{
    "id": 1,
    "name": "User test",
    "identifier": "12345678901",
    "birthDate": "1990-05-15"
}
```

### Atualizar Pessoa
#### Request
```http
PUT /api/person/1
```
#### Body
```json
{
    "name": "User test edit",
    "identifier": "03336784188",
    "birthDate": "1990-05-16"
}
```
#### Response
```http
200 OK
Content-Type: application/json
```
```json
{
    "id": 1,
    "name": "User test edit",
    "identifier": "03336784188",
    "birthDate": "1990-05-16"
}
```

### Criar Empréstimo
#### Request
```http
POST /api/loans
```
#### Body
```json
{
    "personId": 1,
    "loanAmount": 10000.00,
    "numberOfInstallments": 12
}
```
#### Response
```http
201 CREATED
Content-Type: application/json
```
```json
{
    "loanId": 1,
    "personId": 1,
    "loanAmount": 10000.00,
    "numberOfInstallments": 12,
    "status": "PENDING"
}
```

### Consultar Pessoa
#### Request
```http
GET /api/person/1
```
#### Response
```http
200 OK
Content-Type: application/json
```
```json
{
    "id": 1,
    "name": "User test",
    "identifier": "12345678901",
    "birthDate": "1990-05-15"
}
```

## Estrutura do Projeto
A estrutura do projeto é organizada da seguinte forma:
```
├── loan-grant/
│   ├── docker-compose.yml
│   ├── Dockerfile
│   ├── loan-grant-service.json
│   ├── pom.xml
│   ├── README.md
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/
│   │   │   │   │   ├── loan/
│   │   │   │   │   │   ├── grant/
│   │   │   │   │   │   │   ├── config/
│   │   │   │   │   │   │   ├── controller/
│   │   │   │   │   │   │   ├── dto/
│   │   │   │   │   │   │   ├── exceptions/
│   │   │   │   │   │   │   ├── message/
│   │   │   │   │   │   │   │   ├── consumer/
│   │   │   │   │   │   │   ├── model/
│   │   │   │   │   │   │   ├── repository/
│   │   │   │   │   │   │   ├── service/
│   │   │   │   │   │   │   ├── validation/
│   │   │   ├── resources/
│   │   │   │   ├── application.properties
│   │   │   │   ├── static/
│   │   │   │   │   ├── *
│   │   │   │   ├── templates/
│   │   │   │   │   ├── *
```


## Execução dos tests
Os testes pode ser executados da seguinte forma forma:
```
docker compose -f docker-compose.test.yml up --build -d
./mvnw test
docker compose -f docker-compose.test.yml down
```