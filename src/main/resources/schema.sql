CREATE SCHEMA IF NOT EXISTS sistemaacademico;

USE sistemaacademico;

CREATE TABLE `aluno`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `id_curso`   int          NOT NULL,
    `id_usuario` INT          NOT NULL UNIQUE,
    `nome`       varchar(255) NOT NULL,
    `cr` DOUBLE NOT NULL DEFAULT 0,
    `periodo_ingresso`       varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `coordenadordecurso`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `id_usuario` INT          NOT NULL,
    `nome`       varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL UNIQUE,
    `senha`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `curso`
(
    `id`             int          NOT NULL AUTO_INCREMENT,
    `id_coordenador` int          NOT NULL,
    `nome`           varchar(255) NOT NULL,
    `semestre`       int          NOT NULL,
    `periodo_curriculo`       varchar(255) NOT NULL,
    `turno`          varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `disciplina`
(
    `id`             int          NOT NULL AUTO_INCREMENT,
    `id_curso`       int          NOT NULL,
    `nome`           varchar(255) NOT NULL,
    `codigo`         varchar(255) NOT NULL UNIQUE,
    `ementa`         varchar(255) NOT NULL,
    `pre_requisitos` varchar(255) NOT NULL,
    `area`           varchar(255),
    `observacao`     VARCHAR(255),
    `ch_total`       INT          NOT NULL,
    `ch_teorica`     INT          NOT NULL,
    `ch_pratica`     INT          NOT NULL,
    `bibliografia`   varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `administrador`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `id_usuario` INT          NOT NULL,
    `nome`       varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL UNIQUE,
    `senha`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `nota`
(
    `id`       int            NOT NULL AUTO_INCREMENT,
    `id_aluno` int            NOT NULL,
    `id_turma` int            NOT NULL,
    `nota`     DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `professor`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `id_usuario` INT          NOT NULL,
    `nome`       varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL UNIQUE,
    `senha`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `turma`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `id_disciplina` int          NOT NULL,
    `id_professor`  int          NOT NULL,
    `dias`          varchar(255) NOT NULL,
    `horario`       varchar(255) NOT NULL,
    `sala`          ENUM('PAFI_101', 'PAFI_102', 'PAFI_103', 'PAFI_104', 'PAFI_105', 'PAFI_106',
		'PAFI_107',
		'PAFI_108',
		'PAFI_109',
		'PAFI_110',
		'PAFI_111',
		'PAFI_112',
		'PAFI_113',
		'PAFI_114',
		'PAFI_115',
		'PAFI_116',
		'PAFI_117',
		'PAFI_118',
		'PAFI_119',
		'PAFI_120',
		'PAFI_201',
		'PAFI_202',
		'PAFI_203',
		'PAFI_204',
		'PAFI_205',
		'PAFI_206',
		'PAFI_207',
		'PAFI_208',
		'PAFI_209',
		'PAFI_210',
		'PAFI_211',
		'PAFI_212',
		'PAFI_213',
		'PAFI_214',
		'PAFI_215',
		'PAFI_216',
		'PAFI_217',
		'PAFI_218',
		'PAFI_219',
		'PAFI_220',
		'PAFI_301',
		'PAFI_302',
		'PAFI_303',
		'PAFI_304',
		'PAFI_305',
		'PAFI_306',
		'PAFI_307',
		'PAFI_308',
		'PAFI_309',
		'PAFI_310',
		'PAFI_311',
		'PAFI_312',
		'PAFI_313',
		'PAFI_314',
		'PAFI_315',
		'PAFI_316',
		'PAFI_317',
		'PAFI_318',
		'PAFI_319',
		'PAFI_320')NOT NULL,
    `code`          varchar(255) NOT NULL,
    `semestre`      int          NOT NULL,
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

CREATE TABLE `aluno_turma`
(
    `id_aluno` INT NOT NULL,
    `id_turma` INT NOT NULL,
    PRIMARY KEY (`id_aluno`, `id_turma`)
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
