# euComida - Backend

**Projeto MVP do euComida**, um sistema de pedidos de comida por delivery inspirado no iFood. Esta aplicação representa a base arquitetural e funcional de um sistema robusto, seguro e escalável para operar pedidos entre clientes, restaurantes e entregadores.

---

## 🤖 Arquitetura do Projeto

### 🛠️ Tecnologias e Frameworks Utilizados

| Tecnologia | Finalidade |
|------------|------------|
| Java 21 | Linguagem principal |
| Spring Boot 3.5.2 | Framework principal para construção da API |
| Spring Security | Gerenciamento de autenticação/autorizacão |
| Spring Data JPA | Integração com banco relacional |
| PostgreSQL | Banco de dados relacional |
| Docker | Containerização da aplicação |
| OpenAPI (Swagger UI) | Documentação automática da API |

---

### 🗃️ Estrutura do Banco de Dados

Optou-se por um **banco relacional (PostgreSQL)** para garantir consistência nas transações e integridade referencial, facilitando a escalabilidade vertical.

#### Principais Entidades

- **User**: Representa clientes, restaurantes ou entregadores.
- **Order**: Contém informações do pedido feito por um cliente.
- **OrderItem**: Lista de produtos dentro de um pedido.

Todos os relacionamentos seguem a normalização padrão para evitar redundância e garantir integridade dos dados.

---

### 🔒 Estratégia de Autenticação/Autorização

A API utiliza **JWT (JSON Web Token)**.

- **Login**: Usuários autenticam via username e senha.
  - No momento o usuário está mockado para testes, mas pode ser integrado com um serviço de autenticação real.
  - Usuário para realização de testes: `username: user_teste, password: teste123`.
- **Token JWT**: Gerado com assinatura e tempo de expiração.
- **Segurança**:
    - Rotas protegidas, futuramente por perfis de acesso (cliente, restaurante, entregador).
    - Token enviado no header: `Authorization: Bearer <token>`

---

### 🛠️ Escalabilidade e Segurança da API

#### Escalabilidade

- **API Stateless**: permite múltiplas instâncias sem dependência de sessão.
- **Pronta para containers**: executável via Docker e docker-compose, facilitando deploy em orquestradores como Kubernetes ou ECS.
- **Separacão de responsabilidades**: camadas de controller, service, repository e security bem definidas.

#### Segurança

- **Autenticação robusta** com Spring Security + JWT.
- **Tratamento global de exceções**.
- **Validações de entrada** via `@Valid`.

---

## 🚀 Executando o Projeto Localmente

### Pré-requisitos

- [Java 21](https://jdk.java.net/archive/) e [Maven 3.9+](https://maven.apache.org/)
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/) instalados

### Passos

1. Clone o repositório e acesse a pasta do projeto:
   ```bash
   git clone https://github.com/igorlimeira/eucomida.git
   cd eucomida
   ```
2. Construa as imagens Docker e inicie os containers (PostgreSQL, Auth e Purchase Orders):
    - No Linux ou MacOS, execute:
   ```bash
   ./assets/docker-build-and-run.sh
   ```
    - No Windows, execute:
   ```bash
    assets\docker-build-and-run.bat
    ```
3. Após o `docker-compose` subir os serviços, as APIs ficarão acessíveis em:
    - http://localhost:8080 para o serviço de Pedidos
    - http://localhost:8081 para o serviço de Autenticação
4. A documentação OpenAPI (Swagger) pode ser acessada em 
   - PurchaseOrders: `http://localhost:8080/swagger-ui/index.html#`
   - Auth: `http://localhost:8081/swagger-ui/index.html#`.

Caso prefira rodar localmente sem Docker, utilize o Maven:
```bash
mvn mvn -pl auth spring-boot:run
```
```bash
mvn -pl purchase-orders spring-boot:run
```
(será necessário um banco PostgreSQL ativo conforme `application.yml`.)

---
