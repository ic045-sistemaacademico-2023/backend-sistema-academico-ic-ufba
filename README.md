# Sistema Acadêmico para o IC UFBA

## Componente Curricular

### IC045: Tópicos em Sistemas de Informação e Web I

## [Drive](https://drive.google.com/drive/folders/1QJ4PaNYhIkvsSdNPGOQN3nP7j8SYYtgN?usp=sharing)

## [Requisitos](https://docs.google.com/document/d/1Dzjv17Old3uu1rwtQg_xaMUMJ1OL9CvtbSKM_gnplww/edit?usp=drive_link)

## [Modelagem do banco de dados](https://dbdesigner.page.link/28BjhNgupwdhX9Tp8)

## [Protótipo](https://www.figma.com/file/43HvdK6cT0hJ4XjSFZDL04/SIGA---IC045?type=design&mode=design&t=ogiUXiYnDVzzZ5J4-1)

## [Documento de Arquitetura de Software](https://docs.google.com/document/d/1b8DwGg7oZ-APcK7_UBkhEQnsvwaKZnzY/edit?usp=sharing&ouid=114509522047919530579&rtpof=true&sd=true)

## [Plano de Teste](https://docs.google.com/document/d/14-j3w0bYvJGLhpsmsXG_i86Z4zQMQpVhaE_vOw9HVgU/edit?usp=sharing)

## [Documento de Implantação/Instalação](https://docs.google.com/document/d/1AfUttj2DfImhIPl24RdDgZtM6SBJ3-jEPB4A_ky386s/edit?usp=sharing)


## Aplicação

| Aplicação | Link                                                                           |
|-----------|--------------------------------------------------------------------------------|
| Frontend  | https://ic045-siag.netlify.app                                                 |
| Backend   | https://learning-ufba-sistemaacademico.ue.r.appspot.com/api/v1                 |
| Swagger   | https://learning-ufba-sistemaacademico.ue.r.appspot.com/api/v1/swagger-ui.html |

[![Deploy][deploy-badge]][deploy-action]

[deploy-action]: https://github.com/ic045-sistemaacademico-2023/backend-sistema-academico-ic-ufba/actions/workflows/deploy.yaml

[deploy-badge]: https://github.com/ic045-sistemaacademico-2023/backend-sistema-academico-ic-ufba/actions/workflows/deploy.yaml/badge.svg?branch=main

## Tecnologias

1. Java
2. SpringBoot
3. JPA

## Time de Desenvolvimento

| Nome           | Cargo                                   |
|----------------|-----------------------------------------|
| Karen Botelho  | Gerente do Projeto e desenvolvedor      |
| Gustavo Mendel | Vice Gerente do Projeto e desenvolvedor |
| Elis Marcela   | Desenvolvedor                           |
| Glauber        | Desenvolvedor                           |
| Lávio          | Desenvolvedor                           |
| Vitor de Jesus | Desenvolvedor                           |
| Lucas Natanael | Desenvolvedor                           |
| Adiel Cristo   | Desenvolvedor                           |
| Cleiton Rocha  | Desenvolvedor                           |
| Matheus        | Desenvolvedor                           |

## Desenvolvimento

### Instalação do projeto (em Windows)

1. Instale o [Git](https://gitforwindows.org/)
2. Instale o [Eclipse](https://www.eclipse.org/downloads/)
3. Clone o repositório da nuvem para a sua máquina local
4. Importe o projeto Maven no Eclipse pela opção `Existing Maven Project`
5. Aguarde o Eclipse terminar de realizar os downloads necessários para a importação
6. Instale [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17) ou superior
7. Instale [Lombok](https://repo1.maven.org/maven2/org/projectlombok/lombok/1.18.20/lombok-1.18.20.jar)
	7.1. Siga os [passos](https://dicasdeprogramacao.com.br/como-configurar-o-lombok-no-eclipse/#:~:text=Como%20configurar%20o%20lombok%20no%20Eclipse%201%201.,...%207%207.%20Rebuild%20do%20seu%20projeto.%20)

### Configuração do banco de dados

1. Copie o arquivo `.env.properties.dist` para `.env.properties`.
2. Descomente a seção correspondente ao banco de dados que será usado (H2 ou MySQL).

#### H2

1. Altere a propriedade `spring.sql.init.mode` para `always` para executar os scripts `src/main/resources/schema.sql`
   e `src/main/resources/data.sql` sempre que iniciar a aplicação.
2. Para habilitar o console e acessar o banco de dados, altere a propriedade `spring.h2.console.enabled` para `true`
   no arquivo `src/main/resources/application.properties`.
3. Acesse o console no endereço http://localhost:8080/api/v1/h2-console.

#### MySQL

1. Instale o [MySQL Workbench](https://dev.mysql.com/downloads/windows/installer/8.0.html).
2. Adicione uma conexão no MySQL Workbench e configure as credenciais de acesso.
3. Reinicie seu computador ou siga os passos dessa
   [thread](https://stackoverflow.com/questions/41818827/mysql-error-1045-access-denied-for-user-rootlocalhost-using-password)
   para concluir a configuração.
4. Execute os scripts `src/main/resources/schema.sql` e `src/main/resources/data.sql` para a criação do banco de dados
   usando o Workbench ou o MySQL Client:
   1. Workbench:
      1. Abra o MySQL Shell do Workbench e execute `\sql` para converter a linguagem do shell de JS para SQL.
      2. Execute `\connect root@localhost` no MySQL Shell do Workbench pra conectar ao banco, então coloque a senha do
         root e pressione <enter> pra concluir.
      3. Localize os scripts `src/main/resources/schema.sql` e `src/main/resources/data.sql` e execute o comando
         `\source <caminho-do-arquivo>` no terminal do MySQL. Provavelmente os caminhos serão algo como
         `<caminho-do-repositorio>\src\main\resources\schema.sql` e
         `<caminho-do-repositorio>\src\main\resources\data.sql`
   2. MySQL Client:
      1. Execute o comando `mysql -u root -p` e digite a senha do seu MySQL.
      2. Localize os scripts `src/main/resources/schema.sql` e `src/main/resources/data.sql` e execute o comando
         `source <caminho-do-arquivo>` no terminal do MySQL. Provavelmente os caminhos serão algo como
         `<caminho-do-repositorio>\src\main\resources\schema.sql` e
         `<caminho-do-repositorio>\src\main\resources\data.sql`

### Execução da aplicação

1. Clique em `Run As`, e então em `Java Application` da classe `SistemaAcademicoApplication`
   em `<caminho-do-repositorio>\src\main\java\com\ic045\sistemaacademico`
2. Consulte os endpoints da API através da URL `http://localhost:8080/api/v1/swagger-ui/index.html`
