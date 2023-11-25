-- Create a user named `admin` with password `123456` and role `ADMIN`
INSERT INTO `usuario` (cpf, senha, email, role, status, nome) VALUES ('07332976099', '123456', 'admin@gmail.com', 'ADMIN', 'APPROVED', 'Admin');

-- Create a coordinator named `coordenador` with password `123456` and role `COORDENADOR_DE_CURSO`
INSERT INTO `usuario` (cpf, senha, email, role, status, nome) VALUES ('16672579001', '123456', 'coordenador@gmail.com', 'COORDENADOR_DE_CURSO', 'APPROVED', 'Coordenador');

-- Create a professor named `professor` with password `123456` and email `professor@gmail.com`
INSERT INTO `usuario` (cpf, senha, email, role, status, nome) VALUES ('47779839001', '123456', 'professor@gmail.com', 'PROFESSOR', 'APPROVED', 'Professor');

-- Create a student named `aluno` with password `123456` and email `aluno@gmail.com`
INSERT INTO `usuario` (cpf, senha, email, role, status, nome) VALUES ('76741828001', '123456', 'aluno@gmail.com', 'ALUNO', 'APPROVED', 'Aluno');

-- Inserts a row into the coordenadordecurso table
INSERT INTO `coordenadordecurso` (id_usuario, nome, email)
VALUES (2, 'Coordenador', 'coordenador1@gmail.com');

-- Inserts a row into the professor table
INSERT INTO `professor` (id_usuario, nome, email)
VALUES (3, 'Professor', 'professor1@gmail.com');

-- Create a course named `Engenharia de Software` with semester 1 and turno `MATUTINO`
INSERT INTO `curso` (id_coordenador, nome, semestre, turno, periodo_curriculo) VALUES (1, 'Engenharia de Software', 1, 'MATUTINO', '2012.1');

-- Create a discipline named `Programação Orientada a Objetos` with code `POOA` and belongs to the `Engenharia de Software` course
INSERT INTO `disciplina` (id_curso, nome, codigo, ementa, pre_requisitos, area, observacao, ch_total, ch_teorica, ch_pratica, bibliografia)
VALUES (1, 'Programação Orientada a Objetos', 'POOA', 'Ementa da disciplina de Programação Orientada a Objetos', 'Nenhum', 'Técnica', '', 40, 30,10, '1. MANKIW, N.Gregory; TAYLOR, Mark P.. Programação Orientada a Objetos. 6.ed., São Paulo: Cengage Learning, 2014.');

-- Create a turma named `Turma 1` with discipline `Programação Orientada a Objetos` and professor `professor`
INSERT INTO `turma` (id_disciplina, id_professor, dias, horario, sala, code, semestre) VALUES (1, 1, 'SEG, QUA', '10:00 - 12:00', 'PAFI_103', 'Técnica1',  1);

-- Create a record in the `aluno` table to associate the student `aluno` with the `Turma 1`
INSERT INTO `aluno` (id_curso, id_usuario, nome, cr, periodo_ingresso) VALUES (1, 4, 'Aluno', 0, '2019.2');

INSERT INTO aluno_turma (id_aluno, id_turma) VALUES (1, 1);

-- Creates a oportunidade de matricula with coordenador Coordenador
INSERT INTO oportunidade_matricula (id, nome, descricao, data_inicial, data_final, aberta, id_coordenador) VALUES (1, 'Oportunidade A', 'Descricao da Oportunidade A', '2023-11-21 00:00:00', '2023-11-23 00:00:00', 1, 1);

-- Inserts a row into opmatricula_disciplina_turma with oportunidade_matricula 1, disciplina 1, turma 1
INSERT INTO opmatricula_disciplina_turma (id, id_oportunidade_matricula, id_disciplina, id_turma) VALUES (1, 1, 1,1);

-- Create a matricula request for the student `aluno` for the opportunity `Oportunidade A`
INSERT INTO solicitacao_matricula (id_aluno, id_oportunidade_matricula, status) VALUES (1, 1, 'WAITING_APPROVAL');

-- Create a turma request for the student `aluno` for the turma `Turma 1`
INSERT INTO solicitacao_turma (id_turma, id_aluno, id_solicitacao_matricula, status) VALUES (1, 1, 1, 'WAITING_APPROVAL');
