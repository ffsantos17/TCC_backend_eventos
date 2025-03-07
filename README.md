# Sistema Gerenciador de Eventos - Back-End (Spring Boot)

Este repositório contém o código-fonte do back-end do sistema gerenciador de eventos, desenvolvido utilizando o framework **Spring Boot** em Java. O back-end é responsável pela lógica de negócios, gerenciamento de eventos, inscrições e documentos, além de fornecer uma API RESTful para comunicação com o front-end.

## Tecnologias Utilizadas
- **Spring Boot**: Framework para desenvolvimento de APIs RESTful em Java.
- **Java**: Linguagem de programação principal.
- **MySQL**: Banco de dados para armazenamento de informações.
- **JWT**: Para autenticação e autorização de usuários.

## Como Executar o Projeto

### Pré-requisitos
- **Java JDK 11 ou superior**: Certifique-se de ter o JDK instalado.
- **MySQL**: Instale e configure o MySQL para o banco de dados.
- **Maven**: Para gerenciamento de dependências.

### Passos para Execução
1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/backend-eventos.git
2 . Navegue até a pasta do projeto:
    ```bash
    cd backend-eventos

3. Configure o banco de dados:
 - Crie um banco de dados no MySQL chamado eventos_db.
 - Atualize as credenciais do banco de dados no arquivo application.properties:
     ```bash
      spring.datasource.url=jdbc:mysql://localhost:3306/eventos_db
      spring.datasource.username=seu_usuario
      spring.datasource.password=sua_senha

4. Execute o projeto:
     ```bash
     mvn spring-boot:run


---

### **Estrutura do Projeto**

## Estrutura do Projeto
- `src/main/java/`: Contém o código-fonte principal.
  - `br/com/projeto/api_projeto/`: Pacote principal.
    - `controller/`: Controladores da API REST.
    - `services/`: Lógica de negócios.
    - `repositories/`: Repositórios para acesso ao banco de dados.
    - `model/`: Entidades do banco de dados.
    - `config/`: Configurações do projeto (ex: segurança, JWT).
- `src/main/resources/`: Arquivos de configuração e recursos.
  - `application.properties`: Configurações do Spring Boot.
