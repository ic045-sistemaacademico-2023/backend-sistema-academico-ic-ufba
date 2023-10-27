CREATE SCHEMA IF NOT EXISTS sistemaacademico;

USE sistemaacademico;

CREATE TABLE `aluno`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `id_curso`   INT          NOT NULL,
    `id_usuario` INT          NOT NULL UNIQUE,
    `nome`       VARCHAR(255) NOT NULL,
    `cr`         DOUBLE          NOT NULL DEFAULT  0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `coordenadordecurso`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `id_usuario` INT          NOT NULL,
    `nome`       VARCHAR(255) NOT NULL,
    `email`      VARCHAR(255) NOT NULL UNIQUE,
    `senha`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `curso`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `id_coordenador` INT          NOT NULL,
    `nome`           VARCHAR(255) NOT NULL,
    `semestre`       INT          NOT NULL,
    `turno`          VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `disciplina`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `id_curso`       INT          NOT NULL,
    `nome`           VARCHAR(255) NOT NULL,
    `codigo`         VARCHAR(255) NOT NULL UNIQUE,
    `ementa`         VARCHAR(255) NOT NULL,
    `pre_requisitos` VARCHAR(255),
    `area`           VARCHAR(255),
    `observacao`     VARCHAR(255),
    `ch_teorica`     INT          NOT NULL,
    `ch_pratica`     INT          NOT NULL,
    `ch_total`       INT          NOT NULL,
    `semestre`       INT          NOT NULL,
    `objetivos`      VARCHAR(255),
    `conteudo`       VARCHAR(255),
    `bibliografia`   VARCHAR(255),


    PRIMARY KEY (`id`)
);

CREATE TABLE `administrador`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `id_usuario` INT          NOT NULL,
    `nome`       VARCHAR(255) NOT NULL,
    `email`      VARCHAR(255) NOT NULL UNIQUE,
    `senha`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `nota`
(
    `id`       INT            NOT NULL AUTO_INCREMENT,
    `id_aluno` INT            NOT NULL,
    `id_turma` INT            NOT NULL,
    `nota`     DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `professor`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `id_usuario` INT          NOT NULL,
    `nome`       VARCHAR(255) NOT NULL,
    `email`      VARCHAR(255) NOT NULL UNIQUE,
    `senha`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `turma`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `id_disciplina` INT          NOT NULL,
    `id_professor`  INT          NOT NULL,
    `dias`          VARCHAR(255) NOT NULL,
    `horario`       VARCHAR(255) NOT NULL,
    `local`         VARCHAR(255) NOT NULL,
    `semestre` INT            NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `usuario`
(
    `id`     INT          NOT NULL AUTO_INCREMENT,
    `cpf`    VARCHAR(255) NOT NULL UNIQUE,
    `senha`  VARCHAR(255) NOT NULL,
    `email`  VARCHAR(255) NOT NULL UNIQUE,
    `role`   ENUM('ADMIN','PROFESSOR','COORDENADOR_DE_CURSO','ALUNO') NOT NULL,
    `status` ENUM('EMAIL_CHECK','WAITING_APPROVAL','APPROVED','DENIED') NOT NULL,
    `nome`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `aluno_turma` (
    `id_aluno` INT NOT NULL,
    `id_turma` INT NOT NULL,
    PRIMARY KEY (`id_aluno`, `id_turma`),
    KEY `id_aluno_fk` (`id_aluno`),
    KEY `id_turma_fk` (`id_turma`)
);

ALTER TABLE `aluno`
    ADD CONSTRAINT `aluno_fk0` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id`);

ALTER TABLE `aluno`
    ADD CONSTRAINT `aluno_fk2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

ALTER TABLE `coordenadordecurso`
    ADD CONSTRAINT `coordenadordecurso_fk0` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

ALTER TABLE `curso`
    ADD CONSTRAINT `curso_fk0` FOREIGN KEY (`id_coordenador`) REFERENCES `coordenadordecurso` (`id`);

ALTER TABLE `disciplina`
    ADD CONSTRAINT `disciplina_fk0` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id`);

ALTER TABLE `administrador`
    ADD CONSTRAINT `administrador_fk0` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

ALTER TABLE `nota`
    ADD CONSTRAINT `nota_fk0` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id`);

ALTER TABLE `nota`
    ADD CONSTRAINT `nota_fk1` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id`);

ALTER TABLE `professor`
    ADD CONSTRAINT `professor_fk0` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

ALTER TABLE `turma`
    ADD CONSTRAINT `turma_fk0` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id`);

ALTER TABLE `turma`
    ADD CONSTRAINT `turma_fk1` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id`);

ALTER TABLE `aluno_turma`
    ADD CONSTRAINT `id_aluno_fk` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id`);

ALTER TABLE `aluno_turma`
    ADD CONSTRAINT `id_turma_fk` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id`);
