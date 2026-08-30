# Movie Locations API

API REST desenvolvida com Java e Spring Boot para consulta de informações sobre filmes e seus locais de gravação.

O projeto disponibiliza endpoints para consulta do acervo por título, prefixo do título e diretor, além de recursos de documentação, monitoramento e testes.

## Funcionalidades

- Consulta de filmes cadastrados
- Consulta de filmes por título
- Consulta de filmes por prefixo do título
- Consulta de filmes por diretor
- Consulta de informações relacionadas aos locais de gravação
- Tratamento global de exceções
- Documentação da API com Swagger/OpenAPI
- Monitoramento e métricas com Spring Boot Actuator
- Testes unitários com JUnit 5 e Mockito
- Execução utilizando Docker

## Tecnologias

- Java 21+
- Spring Boot
- Spring Web
- Spring WebFlux
- Reactor
- Springdoc OpenAPI
- Spring Boot Actuator
- JUnit 5
- Mockito
- Maven
- Docker

## Requisitos

- Java 21+
- Maven
- Docker (opcional)

## Executando o projeto

Clone o repositório:

```bash
git clone https://github.com/bispobr/java-spring-filmes.git
cd java-spring-filmes
```

Execute a aplicação com Maven:

```bash
mvn spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

## Swagger / OpenAPI

Com a aplicação em execução, a documentação da API pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

## Actuator

Endpoint de saúde da aplicação:

```text
http://localhost:8080/actuator/health
```

Os demais endpoints expostos pelo Actuator dependem da configuração da aplicação.

## API Endpoints

### Listar todos os filmes

```http
GET /Filmes/TodosFilmes
```

Retorna a listagem dos filmes disponíveis no acervo.

### Buscar por título

```http
POST /Filmes/titulo
```

Parâmetro:

| Parâmetro | Tipo | Descrição |
|---|---|---|
| `busca` | `String` | Título utilizado na busca. |

### Buscar por prefixo

```http
POST /Filmes/buscaprefixo
```

Parâmetro:

| Parâmetro | Tipo | Descrição |
|---|---|---|
| `busca` | `String` | Prefixo utilizado na busca. |

### Buscar por diretor

```http
POST /Filmes/diretor
```

Parâmetro:

| Parâmetro | Tipo | Descrição |
|---|---|---|
| `busca` | `String` | Nome do diretor utilizado na busca. |

## Tratamento de exceções

A aplicação utiliza `@RestControllerAdvice` para centralizar o tratamento das exceções relacionadas às requisições da API.

## Testes

Execute os testes com:

```bash
mvn test
```

O projeto utiliza JUnit 5 e Mockito para os testes automatizados.

## Docker

Gere o pacote da aplicação:

```bash
mvn clean package
```

Gere a imagem Docker:

```bash
docker build -t filme .
```

Execute o container:

```bash
docker run -p 8080:8080 filme
```

## Estrutura simplificada

```text
Cliente
   │
   ▼
API REST
   │
   ├── Consulta por título
   ├── Consulta por prefixo
   └── Consulta por diretor
          │
          ▼
     Dados de filmes
```

## Status

Projeto desenvolvido para praticar construção de APIs REST com Spring Boot, consultas de dados, tratamento de exceções, testes automatizados, documentação OpenAPI, monitoramento e execução em containers.
