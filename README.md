# Sistema Acadêmico para o IC UFBA

## Componente Curricular

### IC045: Tópicos em Sistemas de Informação e Web I

## [Drive](https://drive.google.com/drive/folders/1QJ4PaNYhIkvsSdNPGOQN3nP7j8SYYtgN?usp=sharing)

## [Requisitos](https://docs.google.com/document/d/1Dzjv17Old3uu1rwtQg_xaMUMJ1OL9CvtbSKM_gnplww/edit?usp=drive_link)

## [Modelagem do banco de dados](https://dbdesigner.page.link/28BjhNgupwdhX9Tp8)

## [Protótipo](https://www.figma.com/file/43HvdK6cT0hJ4XjSFZDL04/SIGA---IC045?type=design&mode=design&t=ogiUXiYnDVzzZ5J4-1)
## [Documento de Arquitetura de Software](https://docs.google.com/document/d/1b8DwGg7oZ-APcK7_UBkhEQnsvwaKZnzY/edit?usp=sharing&ouid=114509522047919530579&rtpof=true&sd=true)

## Tecnologias
1. Java
2. SpringBoot
3. JPA

## Time de Desenvolvimento

| Nome           | Cargo                                   |
| -------------- | --------------------------------------- |
| Karen Botelho  | Gerente do Projeto e desenvolvedor      |
| Gustavo Mendel | Vice Gerente do Projeto e desenvolvedor |
| Elis Marcela   | Desenvolvedor                           |
| Victor Andrade | Desenvolvedor                           |
| Glauber        | Desenvolvedor                           |
| Igor Dantas    | Desenvolvedor                           |
| Lávio          | Desenvolvedor                           |
| Vitor de Jesus | Desenvolvedor                           |
| Lucas Natanael | Desenvolvedor                           |

## Setup

#### Primeiros passos
1. Instale [Git](https://gitforwindows.org/)
2. Instale [Eclipse](https://www.eclipse.org/downloads/)
3. Clone o repositório da nuvem para a sua máquina local
4. Importe o projeto Maven no Eclipse pela opção "Existing Maven Project"
5. Aguarde o Eclipse terminar de realizar os downloads necessários para a importação
6. 

#### Segunda parte 
1. Crie um arquivo `.env.properties` na raiz do projeto
2. Configure as seguintes variáveis que serão utilizadas no application.properties para configuração com o MySQL:
   1. DATABASE_URL
   2. DATABASE_USERNAME
   3. DATABASE_PASSWORD
3. Rode a migration `Database.sql` para criação do banco de dados
  1. Rode o comando `mysql -u root -p` e digite a senha do seu MySQL
  2. Localize o caminho do `Database.sql` e rode o comando `source <CAMINHO_DO_ARQUIVO>` dentro do terminal do MySQL
