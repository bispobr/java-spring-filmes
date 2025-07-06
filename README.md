# Movie Locations API

Esta API REST fornece informações sobre filmes. Os usuários podem consultar onde um filme foi gravado, além de acessar outros dados relacionados, como título, ano e elenco.

## Funcionalidades

- Obtenção de informações detalhadas sobre filmes.
- Visualização dos locais onde o filme foi filmado.
- Filtros de busca por:
    - Nome completo do filme.
    - Prefixo do nome do filme .
    - Diretor do filme.

## Tecnologias Utilizadas

- **Java + Spring Boot** – Framework principal da aplicação
- **Lombok** – Utilização da anotação `@Slf4j` para geração de logs.
- **Springdoc OpenAPI (Swagger)** – Documentação da API.
- **Spring Boot Actuator** – Exposição de endpoints para monitoramento e métricas da aplicação.
- **Integração Swagger + Actuator** – Exibição conjunta da documentação e status da API.

## Requisitos

- Java 21+
- Maven


## Executando o Projeto

1. Clone o repositório:

```bash
git clone hhttps://github.com/bispobr/Java-Spring-filme.git
```

## Como Usar

1. Inicie a aplicação
2. A API está acessível através do endereço http://localhost:8080
3. A documentação da API está acessível através do Link http://localhost:8080/swagger-ui/index.html#/
4. O endpoint de saúde e métricas do Actuator está acessível através do Link http://localhost:8080/actuator/health

## API Endpoints
API contem o seguinte endpoint :

```http request
GET /filme - lista filmes  
```
| Parâmetro   | Tipo       | Descrição                           |
|:------------| :--------- | :---------------------------------- |
| `titulo`    | `String` | **Opcional**. O nome do usuário 

```http request
GET /filme/prefixo - lista filmes prefixo
```
| Parâmetro | Tipo       | Descrição                           |
|:----------| :--------- | :---------------------------------- |
| `prefixo` | `String` | **Obrigatorio**. O prefixo do filme 

```http request
GET /filme/diretor - lista filmes prefixo
```
| Parâmetro | Tipo       | Descrição                           |
|:----------| :--------- | :---------------------------------- |
| `diretor` | `String` | **Opcional**. O diretor do filme 

