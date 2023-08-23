CREATE DATABASE IF NOT EXISTS SistemaAcademico;


USE SistemaAcademico;


CREATE TABLE
  `Aluno` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_curso` INT NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `senha` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

CREATE TABLE
  `CoordenadorDeCurso` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `senha` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

  CREATE TABLE
  `Curso` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_coordenador` INT NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `semestre` INT NOT NULL,
    `turno` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_coordenador) REFERENCES CoordenadorDeCurso (id)
  );

CREATE TABLE
  `Materia` (
    `id` INT NOT NULL AUTO_INCREMENT,
        `id_curso` INT NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `codigo` VARCHAR(255) UNIQUE NOT NULL,
    `ementa` VARCHAR(255) NOT NULL,
    `pre_requisitos` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_curso) REFERENCES Curso (id)
  );

CREATE TABLE
  `Administrador` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `senha` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

  CREATE TABLE
  `Nota` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_aluno` INT NOT NULL,
    `id_materia` INT NOT NULL,
    `nota` DECIMAL(10, 2) NOT NULL,
    `semestre` INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_aluno) REFERENCES Aluno (id),
    FOREIGN KEY (id_materia) REFERENCES Materia (id)
  );

CREATE TABLE
  `Professor` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `senha` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
  );

CREATE TABLE
  `Turma` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_materia` INT NOT NULL,
    `id_professor` INT NOT NULL,
    `dias` VARCHAR(255) NOT NULL,
    `horario` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_materia) REFERENCES Materia (id),
    FOREIGN KEY (id_professor) REFERENCES Professor (id)
  );


ALTER TABLE
  `Nota`
ADD
  FOREIGN KEY (`id_materia`) REFERENCES `Materia` (`id`);


ALTER TABLE
  `Aluno`
ADD
  FOREIGN KEY (`id_curso`) REFERENCES `Curso` (`id`);


ALTER TABLE
  `Materia`
ADD
  FOREIGN KEY (`id_curso`) REFERENCES `Curso` (`id`);


ALTER TABLE
  `Turma`
ADD
  FOREIGN KEY (`id_materia`) REFERENCES `Materia` (`id`);


ALTER TABLE
  `Turma`
ADD
  FOREIGN KEY (`id_professor`) REFERENCES `Professor` (`id`);