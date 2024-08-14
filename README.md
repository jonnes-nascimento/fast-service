# ⚡Fast-Service - Sistema de gestão de pedidos

## FIAP - Tech Challenge - Fase 1


### Sistema de auto-atendimento para controle de pedidos.

O objetivo deste sistema é auxiliar no controle de pedidos de uma lanchonete, atuando em toda a sua cadeia de produção.
O sistema conta com cadastro de clientes, que podem se identificar através de seu CPF, cadastro de produtos e controle de pagamento, preparação e entrega do pedido.

### Link para a documentação das API's do projeto (OpenAPI):
http://localhost:8080/swagger-ui/index.html

### Link para a documentação (Domain Driven Design) com o Event Storming do projeto:
https://miro.com/app/board/uXjVK39qXjg=/?share_link_id=216852517891

## Para executar a aplicação:

### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker compose build --no-cache

O comando acima gerará os conteineres de aplicação e banco de dados.

### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação, na raíz do projeto (onde se encontra o arquivo docker-compose.yml):

    docker compose up

### 3. Acessar a aplicação
A aplicação estará disponível na seguinte URL:

    http://localhost:8080/

Para facilitar o acesso aos endpoints, disponibilizamos a seguinte collection para ser importada no Postman:  

[Fast-Service.postman_collection.json](Fast-Service.postman_collection.json)

## Informações Úteis

### Informações banco de dados:
    database-name: fast-service
    username: fast-service-user
    password: fs2024

