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
- **Tratamento de Exceções** - @RestControllerAdvice
- **JUnit 5 + Mockito** – Testes Unitarios
- **Docker** – criação, implantação e gerenciamento de aplicações dentro de contêineres.
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

## Como Rodar em um Container (Opcional)

1. Construa o projeto

```bash
mvn clean package 
```

2. Gere a Imagem Docker, com o Docker  instalado execute:


```bash
docker build -t filme . 
```

3. Execute o Container

```bash
docker run -p 8080:8080 filme
```

## API Endpoints
API contem o seguinte endpoint :

```http request
GET /Filmes/TodosFilmes - Retorna listagem com todos os filmes do acervo  
```

```http request
POST /Filmes/titulo - Retorna uma lista de filmes por titulo
```
| Parâmetro | Tipo       | Descrição                           |
|:----------| :--------- | :---------------------------------- |
| `busca`   | `String` | **Obrigatorio**. O titulo do filme 

```http request
POST /Filmes/buscaprefixo - Retorna uma lista de filmes por prefixo
```
| Parâmetro | Tipo       | Descrição                           |
|:----------| :--------- | :---------------------------------- |
| `busca`   | `String` | **Obrigatorio**. O prefixo do filme 

```http request
POST /Filmes/diretor - Retorna uma lista de filmes dirigidos pelo diretor 
```
| Parâmetro | Tipo       | Descrição                           |
|:----------| :--------- | :---------------------------------- |
| `busca`   | `String` | **Obrigatorio**. O prefixo do filme 
