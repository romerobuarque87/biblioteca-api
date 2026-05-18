# Biblioteca API

Projeto desenvolvido para a disciplina de Programação Cliente Servidor.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- H2 Database
- Swagger OpenAPI
- Lombok

---

## Funcionalidades implementadas

### Categorias
- Listar categorias
- Buscar categoria por ID

### Autores
- Listar autores
- Buscar autor por ID

### Livros
- Listar livros
- Buscar livro por ID

### Leitores
- Listar leitores
- Buscar leitor por ID

### Empréstimos
- Listar empréstimos
- Buscar empréstimo por ID

---

## Estrutura do projeto

O projeto está organizado em:

- entity
- repository
- controller
- service
- exception

---

## Banco de dados

O projeto utiliza banco H2 em memória.

### H2 Console

Acesse:

```txt
http://localhost:8080/h2-console
```

### Configuração

JDBC URL:

```txt
jdbc:h2:mem:projetodb
```

Usuário:

```txt
sa
```

Senha:

```txt

```

---

## Swagger

Documentação da API disponível em:

```txt
http://localhost:8080/swagger-ui/index.html
```

---

## Como executar o projeto

No terminal do VS Code execute:

```bash
.\mvnw spring-boot:run
```

---

## Entidades implementadas

- Categoria
- Autor
- Livro
- Leitor
- Emprestimo

---

## Integrantes

- Romero Cesar Buarque
- Roberto Maia
- Gabriel da Silva Vieira