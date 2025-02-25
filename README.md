
# Tech Manager

API RESTful destina ao gerenciamento básico de usuários.
Este projeto foi desenvolvido destinado a um desafio técnico de backend Java com o escopo de gerenciamento básico de usuários. 



## Tecnologias Utilizadas

* Java 21+
* Spring Boot 
* Spring Web 
* Spring Validation
* Spring Data JPA
* SpringDoc OpenApi
* Banco de Dados: PostgreSQL, H2
* Gradle
* MapStruct
* JUnit
* Mockito
## Pré-requisitos

Antes de rodar o projeto, você precisa ter o seguinte instalado:

* JDK 21+
* Gradle (se não for utilizar a funcionalidade de build do IDE)
* PostgreSQL 17
## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente:

`TM_DATABASE_URL` : url do banco de dados (exemplo: jdbc:postgresql://localhost:5432/techmanagerdb)

`TM_DATABASE_USERNAME` : Usuário de acesso

`TM_DATABASE_PASSWORD` : Senha de acesso

Obs.: Para usar o banco de dados em memória (H2) para teste, a configuração é automática e não requer modificações.
## Rodando o Projeto localmente

### 1. Clone o repositório

git clone https://github.com/kscosta/tech-manage-api.git

### 2. Acesse o diretório do projeto

### 3. Rodando o Projeto
#### Usando Gradle
```bash
gradlew bootRun
```
#### Usando a IDE
Se estiver usando uma IDE como IntelliJ ou Eclipse, você pode rodar a classe principal (normalmente, a classe anotada com @SpringBootApplication) diretamente.

### 4. Acessando a API
Após iniciar o projeto, a aplicação estará rodando em http://localhost:8080/api/

Você pode testar os endpoints da API utilizando o Postman ou qualquer outra ferramenta de sua preferência.
## Documentação da API

Após iniciar o projeto a documentação estará disponível no seguinte endereço:
http://localhost:8080/api/swagger-ui.html
## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  gradlew test
```
