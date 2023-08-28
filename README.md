# Sistema Acadêmico para o IC UFBA

## Componente Curricular

### IC045: Tópicos em Sistemas de Informação e Web I

## [Requisitos](https://docs.google.com/presentation/d/1a3dgAwdtRAF2NXGnoe2KquYpQRfYFTteFK93tTGccS0/edit?usp=sharing)

## [Modelagem do banco de dados](https://drawsql.app/teams/grupo2-3/diagrams/sistema-academico)

## [Protótipo](https://docs.google.com/document/d/1wK5GjQg-D7RmzVUL4DWulFPB6uAa7GnDpn7zJ90VuR4/edit?usp=sharing)

## Tecnologias
1. Java
2. SpringBoot
3. JPA

## Time de Desenvolvimento

|                                                                  |                                              |                                  |                                    |                                    |
| ---------------------------------------------------------------- | -------------------------------------------- | -------------------------------- | ---------------------------------- | ---------------------------------- |
|  **Karen Botelho**, Gerente do Projeto e desenvolvedor    |  **Gustavo Mendel**, Vice Gerente do Projeto | **Elis Marcela**, desenvolvedor |  **Victor Andrade**, desenvolvedor |  **Glauber**, desenvolvedor        |
|  **Igor Dantas**, desenvolvedor                                  |  **Samuel**, desenvolvedor                   |  **Judai Abdon**, desenvolvedor  |  **Lávio**, desenvolvedor          |  **Vitor de Jesus**, desenvolvedor |

Repositório do Backend

## Setup

1. Crie um arquivo `.env.properties` na raiz do projeto
2. Configure as seguintes variáveis que serão utilizadas no application.properties para configuração com o MySQL:
   1. DATABASE_URL
   2. DATABASE_USERNAME
   3. DATABASE_PASSWORD
3. Rode a migration `Database.sql` para criação do banco de dados
  1. Rode o comando `mysql -u root -p` e digite a senha do seu MySQL
  2. Localize o caminho do `Database.sql` e rode o comando `source <CAMINHO_DO_ARQUIVO>` dentro do terminal do MySQL
