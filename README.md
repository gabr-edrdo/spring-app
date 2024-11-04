# API para cadastro e gerenciamento de pessoas

O projeto trata de um desafio técnico que tinha por objetivo:

> Desenvolver uma aplicação web simples para cadastro e gerenciamento de pessoas utilizando Angular no front-end e Java Spring Boot no back-end. A aplicação deve permitir o cadastro de pessoas com validação de CPF no front-end e preenchimento automático de endereço via CEP, além da listagem, pesquisa e gerenciamento dos registros cadastrados. O armazenamento dos dados deve ser realizado em um banco de dados SQLite.

Portanto, aqui está descrita a implementação da API back-end utilizando Java e Spring Boot.
O demo da aplicação pode ser conferido [aqui](https://spring-latest.onrender.com/api/pessoas). Ele está executando em um plano de hospedagem gratuito, o que gera reinicialização do contêiner e perda de dados persistentes, uma limitação que ocorre apenas pelo serviço do *freemium*.

## 🚀 Começando

### 📋 Pré-requisitos

- Java 21 (JDK)
- Maven
- Docker (opcional)

### 🔧 Instalação

O clone do repositório trará todos os arquivos necessários para a execução do projeto, sendo apenas necessária a instalação das dependências via Maven (declaradas no arquivo *pom.xml*). Utilizando a IDE *Intellij*, a instalação pode ser realizada graficamente, assim como a execução do servidor local.
O banco de dados será criado automaticamente pela aplicação.
Caso seja necessário a geração de uma imagem para contêiner, está presente também um *Dockerfile* com especificações. Esse passo não é obrigatório e se fez necessário apenas pela necessidade da hospedagem, mais informações para build e deploy podem ser encontradas neste [blog](https://hostingtutorials.dev/blog/free-spring-boot-host-with-render). 

### ⚙️ Executando os testes

Os testes unitários estão alocados em *'spring-app\src\test'*.

## 📦 Implementação

O servidor por padrão escuta na porta 8080. Foram implementados os seguintes endpoints:
- **GET** /api/pessoas - Listar todas as pessoas
- **GET** /api/pessoas/{id} - Obter detalhes de uma pessoa
- **POST** /api/pessoas - Criar uma nova pessoa
- **PUT** /api/pessoas/{id} - Atualizar uma pessoa existente
- **DELETE** /api/pessoas/{id} - Deletar uma pessoa

A documentação gerada com Swagger pode ser obtida [aqui](https://spring-latest.onrender.com/swagger-ui/index.html).

## 🛠️ Construído com
	
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework web
* [Spring Initializr](https://start.spring.io/) - Gerador de boilerplate do framework web
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [SQLite](https://www.sqlite.org/) - Banco de dados

## 📌 Dependências

-   **spring-boot-starter-data-jpa**: permite a integração com bancos de dados.
    
-   **spring-boot-starter-web**: fornece suporte a RESTful APIs.
    
-   **spring-boot-starter-validation**: inclui suporte à validação de dados.
    
-   **spring-boot-devtools**: fornece ferramentas úteis para desenvolvimento, como reinicialização automática.
    
-   **springdoc-openapi-starter-webmvc-ui**: facilita a geração de documentação da API usando o OpenAPI (Swagger).
    
-   **sqlite-jdbc**: implementação do driver JDBC para o SQLite.
    
-   **hibernate-community-dialects**: dialetos do Hibernate para suportar diferentes bancos de dados.
    
-   **lombok**: gera automaticamente métodos comuns como getters, setters, construtores, etc.
    
-   **spring-boot-starter-test**: inclui bibliotecas necessárias para realizar testes unitários.

## 📄 Licença

[MIT](https://choosealicense.com/licenses/mit/).

## 🎁 Agradecimentos

Apesar de já ter estudado a linguagem Java, esta é minha primeira API feita com Spring Boot, nesse caminho de descobertas me deparei com ótimos materiais na internet:
* [Fernanda Kipper](https://www.youtube.com/playlist?list=PLNCSWIsR6ADI_wMAx9F-Iu8Hs9HHxj4sb) com a melhor didática já vista no Youtube.
* [Giuliana Bezerra](https://www.youtube.com/playlist?list=PLiFLtuN04BS0pzDbDiuSh-Mt-ifgiiogs) por revelar os atalhos.
* [Equipe do Baeldung](https://www.baeldung.com/) pelos artigos práticos e certeiros.
