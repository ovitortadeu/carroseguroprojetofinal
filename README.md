
# carroseguroprojetofinal




### Integrantes do Grupo

| Nome Completo                | 
|------------------------------|
| Vitor Tadeu Soares de Sousa  |
| Pedro Paulo Barretta         | 
|Felipe Ulson Sora             | 

## Descrição Geral

Este projeto é uma API RESTful desenvolvida em Java para gerenciar um sistema de carros e problemas associados a eles. Ele permite que usuários executem operações de criação, leitura, atualização e exclusão (CRUD) para as entidades `Carro` e `Problema` e outras relacionadas ao `Usuario`. Com a API, é possível registrar novos carros, associá-los a problemas específicos, consultar detalhes, e atualizar ou remover registros conforme necessário.

## Tecnologias Utilizadas

- **Java**: Linguagem principal para o desenvolvimento backend.
- **Banco de Dados Relacional** SQLDeveloper: Armazena informações sobre carros e problemas e usuários.
- **Insomnia**: Para testar endpoints e documentar a API.

## Funcionalidades

A API RESTful oferece as seguintes operações para as entidades `Carro` e `Problema` e `Usuario`:

- **Criar** novos registros.
- **Consultar** registros individuais ou listas completas de carros e problemas e informações do usuário.
- **Atualizar** dados de carros, problemas e de usuários.
- **Excluir** registros do banco de dados.

### Endpoints Principais

- **Carro**
  - `POST /carro`: Cria um novo registro de carro.
  - `GET /carro`: Retorna uma lista de todos os carros.
  - `PUT /{idCarro}`: Atualiza informações de um carro.
  - `DELETE /{idCarro}`: Remove um carro do sistema.

- **Problema**
  - `POST /problemas`: Cria um novo problema associado a um carro.
  - `GET /problemas`: Retorna uma lista de todos os problemas.
  - `PUT /{idProblema}`: Atualiza informações de um problema.
  - `DELETE /{idProblema}`: Remove um problema do sistema.

## Estrutura do Projeto

O projeto foi organizado em pacotes, seguindo um design pattern de Transfer Object (TO), Business Object (BO), Data Access Object (DAO) e Resource, proporcionando uma arquitetura limpa e manutenível:

- **Resource**: Define os endpoints RESTful e gerencia a entrada e saída de dados para os usuários.
- **BO (Business Object)**: Contém a lógica de negócios da aplicação, intermediando entre o Resource e o DAO.
- **DAO (Data Access Object)**: Define a interface para comunicação com o banco de dados, abstraindo o acesso aos dados e facilitando o uso de operações de CRUD.
- **TO (Transfer Object)**: Contém as classes de entidades `Carro` e `Problema`, representando as tabelas do banco de dados e servindo como objetos de transferência de dados entre as camadas.

## Configuração do Banco de Dados

As configurações para conexão com o banco de dados estão no arquivo `application.properties`, permitindo que a aplicação se conecte a um banco de dados Oracle SQLDeveloper . A configuração básica do banco inclui URL, nome de usuário, senha e opções de criação automática de tabelas.

## Exemplo de Fluxo de Uso

1. Um cliente envia uma requisição `POST /carros` para registrar um novo carro.
2. A API responde com os detalhes do carro recém-criado.
3. O cliente pode associar problemas ao carro através do endpoint `POST /problemas`.
4. Com os endpoints `GET`, é possível recuperar listas de carros ou problemas, assim como detalhes específicos.
5. Através dos métodos `PUT` e `DELETE`, é possível atualizar ou remover registros conforme necessário.

## Benefícios do Projeto

- **Simplicidade e Clareza**: A API é fácil de usar, com endpoints intuitivos e padrões RESTful.
- **Modularidade**: A separação em pacotes facilita manutenção e expansão do sistema.
- **Escalabilidade**: A estrutura permite que novos módulos ou entidades sejam adicionados facilmente.
- **Gerenciamento de Dados Completo**: As operações CRUD oferecem controle total sobre os registros do sistema.

