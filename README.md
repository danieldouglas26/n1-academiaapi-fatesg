# Prova - API REST de Gestão de Academia

## Descrição Breve da Solução

Esta é uma API REST desenvolvida em Java com Spring Boot para o gerenciamento de uma academia. A API permite gerenciar alunos, planos, treinos e pagamentos, seguindo uma arquitetura em camadas (Controller, Service, Repository) e aplicando as boas práticas de desenvolvimento de APIs REST.

O projeto utiliza:
- **Java 17** e **Spring Boot 3**
- **Maven** para gerenciamento de dependências
- **Spring Data JPA** para persistência de dados
- **Banco de Dados H2** em modo arquivo para simplicidade
- **DTOs (Data Transfer Objects)** para a comunicação entre cliente e servidor
- **Swagger/OpenAPI** para documentação automática dos endpoints
- **Versionamento de API** (`/api/v1/`)

## Instruções para Rodar o Projeto

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.8 ou superior

### Passos
1.  **Clone o repositório:**
    ```bash
    git clone <https://github.com/danieldouglas26/n1-academiaapi-fatesg.git>
    cd academia-api
    ```

2.  **Compile e execute o projeto usando o Maven:**
    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080`.

### Acessando a Documentação e o Banco
-   **Documentação da API (Swagger):**
    Acesse a interface do Swagger UI no seu navegador para testar os endpoints:
    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

-   **Console do Banco de Dados H2:**
    Acesse o console do H2 para visualizar os dados diretamente no banco:
    [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    -   **JDBC URL:** `jdbc:h2:file:./academiadb`
    -   **User Name:** `sa`
    -   **Password:** `password`

## Roteiro de Testes com Swagger UI

Para garantir que os testes funcionem corretamente, siga a ordem abaixo, pois ela respeita os relacionamentos entre os dados (um aluno precisa de um plano para ser criado, por exemplo).

### 1. Criar um Plano
- **Endpoint:** `POST /api/v1/planos`
- **Como testar:**
    1. No Swagger, abra a seção `Planos` e o endpoint `POST /api/v1/planos`.
    2. Clique em "Try it out".
    3. No corpo da requisição (`Request body`), insira:
       ```json
       {
         "nome": "Plano Mensal",
         "valor": 99.90
       }
       ```
    4. Clique em "Execute" e **anote o `id`** do plano retornado.

### 2. Criar um Treino
- **Endpoint:** `POST /api/v1/treinos`
- **Como testar:**
    1. Na seção `Treinos`, abra o endpoint `POST /api/v1/treinos`.
    2. Clique em "Try it out".
    3. No `Request body`, insira:
       ```json
       {
         "nome": "Treino de Adaptação",
         "descricao": "Foco em exercícios básicos para iniciantes.",
         "nivel": "INICIANTE"
       }
       ```
    4. Clique em "Execute" e **anote o `id`** do treino retornado.

### 3. Criar um Aluno
- **Endpoint:** `POST /api/v1/alunos`
- **Como testar:**
    1. Na seção `Alunos`, abra o endpoint `POST /api/v1/alunos`.
    2. Clique em "Try it out".
    3. No `Request body`, use o `planoId` que você anotou:
       ```json
       {
         "nome": "João da Silva",
         "cpf": "123.456.789-00",
         "dataNascimento": "2000-05-15",
         "planoId": 1
       }
       ```
    4. Clique em "Execute" e **anote o `id`** do aluno retornado.

## Prints dos Endpoints Testados (Exemplos do Swagger)

A seguir estão os prints que documentam os testes realizados em cada endpoint da API.

### Gerenciamento de Planos

**1. Criando um Novo Plano (`POST /api/v1/planos`)**
- Request:
  ![Criar Plano (Request)](POST%20api-v1-planos%20(request).png)
- Response:
  ![Criar Plano (Response)](POST%20api-v1-planos%20(response).png)

**2. Listando Planos (`GET /api/v1/planos`)**
- Response:
  ![Listar Planos](GET%20api-v1-planos%20.png)

### Gerenciamento de Treinos

**1. Criando um Novo Treino (`POST /api/v1/treinos`)**
- Request:
  ![Criar Treino (Request)](POST%20api-v1-treinos%20(request).png)
- Response:
  ![Criar Treino (Response)](POST%20api-v1-treinos%20(response).png)

**2. Listando Treinos (`GET /api/v1/treinos`)**
- Response:
  ![Listar Treinos](GET%20api-v1-treinos.png)

**3. Deletando um Treino (`DELETE /api/v1/treinos/{id}`)**
- Response:
  ![Deletar Treino](DELETE%20api-v1-treinos-id.png)

### Gerenciamento de Alunos

**1. Criando um Novo Aluno (`POST /api/v1/alunos`)**
- Request:
  ![Criar Aluno (Request)](POST%20api-v1-alunos%20(request).png)
- Response:
  ![Criar Aluno (Response)](POST%20api-v1-alunos%20(response).png)

**2. Listando todos os Alunos (`GET /api/v1/alunos`)**
- Response:
  ![Listar Alunos](GET%20api-v1-alunos%20.png)

**3. Buscando Aluno por ID (`GET /api/v1/alunos/{id}`)**
- Response:
  ![Buscar Aluno por ID](GET%20api-v1-alunos-id%20.png)

**4. Atualizando um Aluno (`PUT /api/v1/alunos/{id}`)**
- Request:
  ![Atualizar Aluno (Request)](PUT%20api-v1-alunos-id%20(request).png)
- Response:
  ![Atualizar Aluno (Response)](PUT%20api-v1-alunos-id%20(response).png)

**5. Inativando um Aluno (`PATCH /api/v1/alunos/{id}/inativar`)**
- Response:
  ![Inativar Aluno](PATCH%20api-v1-alunos-id-inativar.png)

### Operações Associadas

**1. Associando um Treino a um Aluno (`POST /api/v1/treinos/{treinoId}/associar-aluno`)**
- Request:
  ![Associar Treino (Request)](POST%20api-v1-treinos-treinoId-associar-aluno%20(request).png)
- Response:
  ![Associar Treino (Response)](POST%20api-v1-treinos-treinoId-associar-aluno%20(response).png)

**2. Registrando um Pagamento (`POST /api/v1/alunos/{id}/pagamentos`)**
- Request:
  ![Registrar Pagamento (Request)](POST%20api-v1-alunos-id-pagamentos%20(request).png)
- Response:
  ![Registrar Pagamento (Response)](POST%20api-v1-alunos-id-pagamentos%20(response).png)