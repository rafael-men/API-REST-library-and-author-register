# API CRUD Bookshelf Management - Spring Boot

Este é um projeto simples em **Java** utilizando **Spring Boot** para a construção de um sistema CRUD (Create, Read, Update, Delete) de **Livros** e **Autores**. O objetivo é permitir a criação, leitura, atualização e exclusão de registros de livros e autores com persistência de dados utilizando **JPA** e um banco de dados relacional.

---

## 🚀 Funcionalidades

### 📚 CRUD de Autores
- **Cadastrar Autor**: Criação de um novo autor com campos como nome, data de nascimento e biografia.
- **Listar Autores**: Exibição de todos os autores cadastrados.
- **Atualizar Autor**: Edição dos dados de um autor existente.
- **Excluir Autor**: Remoção de um autor do banco de dados.

### 📖 CRUD de Livros
- **Cadastrar Livro**: Criação de um novo livro com atributos como título, ISBN, preço, data de publicação e o autor associado.
- **Listar Livros**: Exibição de todos os livros cadastrados.
- **Atualizar Livro**: Edição das informações de um livro existente.
- **Excluir Livro**: Remoção de um livro do banco de dados.

---

## 🛠️ Tecnologias Usadas
- **Spring Boot**: Framework principal para criação de APIs REST.
- **Spring Data JPA**: Para gerenciamento de persistência de dados.
- **Banco de Dados Relacional**: PostgreSQL.
- **Spring Web**: Para construção de rotas e APIs REST.

---

## 🗂️ Estrutura do Projeto
- **`Author`**: Entidade representando um autor.
- **`Book`**: Entidade representando um livro com associação ao autor.
- **Controllers**: Controladores REST que gerenciam as requisições HTTP.
- **Services**: Camada de lógica de negócio.
- **Repositories**: Repositórios JPA para interação com o banco de dados.

---

## 🌐 Exemplos de Endpoints

### Autores
- **`POST /authors`**: Cadastrar um novo autor.
- **`GET /authors`**: Listar todos os autores.
- **`GET /authors/{id}`**: Obter detalhes de um autor específico.
- **`PUT /authors/{id}`**: Atualizar informações de um autor.
- **`DELETE /authors/{id}`**: Excluir um autor.
- **`PATCH /authors/{id}`**: Atualizar parcialmente um autor.

### Livros
- **`POST /books`**: Cadastrar um novo livro.
- **`GET /books`**: Listar todos os livros.
- **`GET /books/{id}`**: Obter detalhes de um livro específico.
- **`PUT /books/{id}`**: Atualizar informações de um livro.
- **`DELETE /books/{id}`**: Excluir um livro.
- **`PATCH /books/{id}`**: Atualizar parcialmente um livro.

---

## 🐳 Passo a Passo para Executar o Projeto com Docker Compose

A seguir, apresentamos um passo a passo detalhado para executar o projeto utilizando **Docker Compose**. Este processo inclui a configuração do ambiente para o **backend** e **banco de dados**, bem como a inicialização do sistema.

### Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas na sua máquina:
- **Docker**: Para conteinerização da aplicação.
- **Docker Compose**: Para orquestração dos contêineres.

### Construção e Execução do Projeto
1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/seu-usuario/api-crud-bookshelf.git
   cd api-crud-bookshelf
## 🐳 Iniciando o Projeto com Docker Compose

Siga os passos abaixo para executar o projeto utilizando **Docker Compose**.

### Passos para Execução

1. **Inicie o Docker Compose**:
   No diretório raiz do projeto, execute o comando:
   ```bash
   docker-compose up --build
