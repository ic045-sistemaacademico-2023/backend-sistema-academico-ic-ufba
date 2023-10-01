# Sistema Acadêmico para o IC UFBA

## Componente Curricular

### IC045: Tópicos em Sistemas de Informação e Web I

## [Drive](https://drive.google.com/drive/folders/1QJ4PaNYhIkvsSdNPGOQN3nP7j8SYYtgN?usp=sharing)

## [Requisitos](https://docs.google.com/document/d/1Dzjv17Old3uu1rwtQg_xaMUMJ1OL9CvtbSKM_gnplww/edit?usp=drive_link)

## [Modelagem do banco de dados](https://dbdesigner.page.link/28BjhNgupwdhX9Tp8)

## [Protótipo](https://www.figma.com/file/43HvdK6cT0hJ4XjSFZDL04/SIGA---IC045?type=design&mode=design&t=ogiUXiYnDVzzZ5J4-1)
## [Documento de Arquitetura de Software](https://docs.google.com/document/d/1b8DwGg7oZ-APcK7_UBkhEQnsvwaKZnzY/edit?usp=sharing&ouid=114509522047919530579&rtpof=true&sd=true)

## [Plano de Teste](https://docs.google.com/document/d/14-j3w0bYvJGLhpsmsXG_i86Z4zQMQpVhaE_vOw9HVgU/edit)
https://docs.google.com/document/d/14-j3w0bYvJGLhpsmsXG_i86Z4zQMQpVhaE_vOw9HVgU/edit

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

## Set up

#### Primeira parte: Instalação geral do projeto
1. Instale [Git](https://gitforwindows.org/)
2. Instale [Eclipse](https://www.eclipse.org/downloads/)
3. Clone o repositório da nuvem para a sua máquina local
4. Importe o projeto Maven no Eclipse pela opção "Existing Maven Project"
5. Aguarde o Eclipse terminar de realizar os downloads necessários para a importação
6. Instale [MySQL Workbench](https://dev.mysql.com/downloads/windows/installer/8.0.html)
7. Adicione uma conexão no MySQL Workbench e configure as credenciais de acesso
8. Reinicie seu computador ou siga os passos dessa [thread](https://stackoverflow.com/questions/41818827/mysql-error-1045-access-denied-for-user-rootlocalhost-using-password) para concluir com sucesso a segunda parte do set up

#### Segunda parte: Configuração do banco de dados
1. Crie um arquivo `.env.properties` na raiz do projeto
2. Configure as seguintes variáveis que serão utilizadas no application.properties para configuração com o MySQL:
   1. DATABASE_URL=`jdbc:mysql://localhost:3306/sistemaacademico?useSSL=false&allowPublicKeyRetrieval=true&useTimezone=true=America/Bahia`
   2. DATABASE_USERNAME
   3. DATABASE_PASSWORD
3. Rode a migration `Database.sql` para criação do banco de dados
  1. Abra o MySQL Shell do Workbench e rode `\sql` para converter alinguagem do shell de JS para SQL
  2. Rode `\connect root@localhost` no MySQL Shell do Workbench pra conectar ao banco, então coloque a senha do root e pressione enter pra concluir
  3. Localize o caminho do `Database.sql` e rode o comando `\source <CAMINHO_DO_ARQUIVO>` dentro do terminal do MySQL
     1. Provavelmente o caminho será algo como `${caminhoParaRepositório}\backend-sistema-academico-ic-ufba\src\main\java\com\ic045\sistemaacademico\utils\migrations\Database.sql`

     Ou siga
  1. Rode o comando `mysql -u root -p` e digite a senha do seu MySQL
  2. Localize o caminho do `Database.sql` e rode o comando `source <CAMINHO_DO_ARQUIVO>` dentro do terminal do MySQL

#### Terceira parte: Rodar o projeto
1. Cliqye em "Run As" então em "Java Application" da classe "SistemaAcademicoApplication" em `${caminhoParaRepositório}\backend-sistema-academico-ic-ufba\src\main\java\com\ic045\sistemaacademico`
2. Acesse a API através de URL como a seguinte `http://localhost:8080/sistemaacademico/disciplina/1`
