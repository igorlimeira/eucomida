# Especificação Técnica - Próximos Passos

Este documento descreve as atividades sugeridas para os times de backend, frontend e mobile darem continuidade ao projeto **euComida**.

## Backend

1. **Melhoria no Serviço de Autenticação**
    - Criar as tabelas `tb_user` e `tb_user_profile` para registro de usuários e controle de perfis (cliente, entregador, admin).
    - Os profiles inicialmente podem ser categorizados em um ENUM (EUserProfile) com valores como `CLIENTE`, `ENTREGADOR`, `ADMIN`, `RESTAURANT`.
    - Implementar entidades JPA correspondentes e relacionar `tb_user` com `tb_user_profile` (um-para-muitos).
    - Atualizar serviço de autenticação (`auth`) para criar e validar tokens considerando perfis de acesso.
    - Ajustar configurações de segurança do Spring Security para roles baseadas em `user_profile`.
    - Migrar dados de usuários existentes (se houver) e criar endpoints para cadastro/gerenciamento de usuários.

2. **Cadastro de Produtos**
    - Criar tabela e entity relacionada a restaurantes (`tb_restaurant`) esta tabela deve possuir relacionamento com `tb_user` muitos-para-muitos, pois uma vez que um restaurante foi criado poderá ser compartilhado com vários usuários (ex.: gerentes, atendentes).
    - Criar a tabela `tb_products` com relacionamento à tabela `tb_restaurant` (restaurantes).
    - Implementar endpoints de CRUD para produtos, permitindo que restaurantes cadastrem, atualizem e excluam seus itens de menu.
    - Aplicar validações (nome, descrição, preço, disponibilidade).
    - Atualizar documentação OpenAPI para expor os novos endpoints.

3. **Testes Automatizados**
    - Ampliar cobertura de testes unitários utilizando JUnit e Mockito.
    - Adicionar testes de integração para fluxos principais (ex.: criação de pedidos, autenticação).
    - Configurar Cucumber para testes de aceitação (BDD), criando cenários que validem casos de uso end-to-end.

## Frontend Web

1. **Tela de Criação de Pedido**
    - Desenvolver interface para seleção de produtos e montagem do pedido.
    - Integrar com o backend para listar restaurantes e seus respectivos produtos.
    - Link do swagger para consulta de endpoints: [Swagger UI](http://localhost:8080/swagger-ui/index.html).
    - Os produtos por enquanto devem ser mockados, mas a estrutura deve permitir fácil integração com o backend posteriormente.
    - Implementar validações de formulário (preenchimento obrigatório, formato de dados, a combinar).

2. **Tela de Consulta de Status do Pedido**
    - Criar página para que o cliente acompanhe o status (em preparo, a caminho, entregue).
    - Implementar atualização em tempo real (WebSocket ou polling) para mudanças de status.

![Mockup de exemplo](/assets/docs/images/orders_web.png)

## Aplicativo Mobile

1. **Tela de Criação de Pedido (Mobile)**
    - Seguir a mesma lógica da versão web, adaptando para UX mobile (Android/iOS).
    - Utilizar componentes nativos para seleção de itens e finalização do pedido.

2. **Tela de Consulta de Status do Pedido (Mobile)**
    - Exibir timeline do pedido com atualizações de status.
    - Enviar notificações push quando houver mudanças significativas (ex.: pedido saiu para entrega).

![Mockup de exemplo](/assets/docs/images/orders_mobile.png)
## Próximas Etapas Recomendadas

- Definir todos os possíveis status de pedidos (ex.: PENDENTE, EM_PREPARO, EM_ENTREGA, ENTREGUE, CANCELADO), e sincronizar com o frontend e mobile.
- Definir prioridades e atribuir responsáveis para cada tarefa.
- Preparar scripts de migração de banco para as novas tabelas (avaliar se é necessário usar Flyway ou Liquibase).
- Configurar pipelines de CI/CD com execução automática de testes.
- Planejar sprints curtas para validar as novas funcionalidades de forma incremental.
- Verificar possibilidade de uso de key management para proteger chaves de API e senhas sensíveis.
- Avaliar uso de gateway API para centralizar autenticação e roteamento de serviços.