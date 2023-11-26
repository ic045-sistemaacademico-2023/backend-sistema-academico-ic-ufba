-- Tabela usuario
INSERT INTO usuario (cpf, senha, email, role, status, nome) VALUES 
('07332976099', '123456', 'admin@gmail.com', 'ADMIN', 'APPROVED', 'Admin'),
('16672579001', 'abcd', 'coordenador@gmail.com', 'COORDENADOR_DE_CURSO', 'APPROVED', 'Coordenador'),
('47779839001', '123456', 'professor@gmail.com', 'PROFESSOR', 'APPROVED', 'Professor'),
('76741828001', 'abcd', 'aluno@gmail.com', 'ALUNO', 'APPROVED', 'Aluno'),
('12345678901', '1234', 'ic01@gmail.com', 'ALUNO', 'APPROVED', 'Usuario 1'),
('23456789012', 'abcd', 'ic02@gmail.com', 'PROFESSOR', 'APPROVED', 'Usuario 2'),
('34567890123', '123456', 'ic03@gmail.com', 'ADMIN', 'APPROVED', 'Usuario 3'),
('45678901234', 'abcd', 'ic04@gmail.com', 'COORDENADOR_DE_CURSO', 'APPROVED', 'Usuario 4'),
('45678945789', 'abcd', 'ic05@gmail.com', 'COORDENADOR_DE_CURSO', 'APPROVED', 'Usuario 5'),
('56789012345', '123456', 'ic06@gmail.com', 'PROFESSOR', 'APPROVED', 'Usuario 6'),
('12345678201', '1234', 'ic07@gmail.com', 'ALUNO', 'APPROVED', 'Usuario 7');

-- Tabela coordenadordecurso
INSERT INTO coordenadordecurso (id_usuario, nome, email) VALUES 
(2, 'Coordenador', 'coordenador1@gmail.com'),
(8, 'Coordenador 2', 'ic04@gmail.com'),
(9, 'Coordenador 3', 'ic05@gmail.com');

-- Populando a tabela professor
INSERT INTO professor (id_usuario, nome, email) VALUES 
(3, 'Professor', 'professor1@gmail.com'),
(6, 'Professor 2', 'professor2@gmail.com'),
(10, 'Professor 3', 'professor3@gmail.com');

-- Populando a tabela curso
INSERT INTO curso (id_coordenador, nome, semestre, turno, periodo_curriculo) VALUES 
(1, 'Engenharia de Software', 1, 'MATUTINO', '2012.1'),
(1, 'Ciência da Computação', 1, 'VESPERTINO', '2023.1'),
(2, 'Ciências Econômicas', 2, 'NOTURNO', '2023.2'),
(2, 'Estatística', 3, 'VESPERTINO', '2023.1'),
(3, 'Medicina', 4, 'MATUTINO', '2023.2');

-- Populando a tabela disciplina
INSERT INTO disciplina (id_curso, nome, codigo, ementa, pre_requisitos, area, observacao, ch_total, ch_teorica, ch_pratica, bibliografia) VALUES 
(1, 'Programação Orientada a Objetos', 'POOA', 'Ementa da disciplina POO', 'Nenhum', 'Técnica', '', 40, 30, 10, 'Bibliografia POO'),
(2, 'Banco de Dados', 'BD101', 'Ementa de Banco de Dados', 'Nenhum', 'Técnica', '', 60, 40, 20, 'Bibliografia BD'),
(3, 'Macroeconomia III', 'MACRO', 'Ementa de Macroeconomia', 'Nenhum', 'Teórica', '', 50, 35, 15, 'Bibliografia Economia'),
(4, 'Séries Temporais', 'SERIES', 'Ementa de Estatística', 'Nenhum', 'Prática', '', 70, 30, 40, 'Bibliografia Estatística'),
(5, 'História da Medicina', 'HISTMED', 'Ementa de Medicina', 'Nenhum', 'Prática', '', 80, 40, 40, 'Bibliografia Medicina');

-- Populando a tabela turma
INSERT INTO turma (id_disciplina, id_professor, dias, horario, sala, code, semestre) VALUES 
(1, 3, 'SEG/QUA', '10:00-12:00/10:00-12:00', 'PAFI_103', 'T1', 1),
(2, 3, 'TER/QUI', '14:00-16:00/14:00-16:00', 'PAFI_104', 'T2', 1),
(3, 2, 'SEG/SEX', '18:00-20:00/18:00-20:00', 'PAFI_105', 'T3', 2),
(4, 1, 'QUA/QUI', '08:00-10:00/09:00-11:00', 'PAFI_106', 'T4', 3),
(5, 1, 'TER/QUI', '16:00-18:00/16:00-18:00', 'PAFI_107', 'T5', 4);

-- Populando a tabela aluno
INSERT INTO aluno (id_curso, id_usuario, nome, cr, periodo_ingresso) VALUES 
(1, 4, 'Aluno', 8.9, '2019.2'),
(2, 5, 'Aluno 2', 7.2, '2020.1'),
(5, 11, 'Aluno 3', 4.7, '2023.1');

-- Populando a tabela aluno_turma
INSERT INTO aluno_turma (id_aluno, id_turma) VALUES 
(1, 1),
(2, 2),
(3, 5);

-- Populando a tabela oportunidade_matricula
INSERT INTO oportunidade_matricula (id, nome, descricao, data_inicial, data_final, aberta, id_coordenador) VALUES 
(1, 'Oportunidade A', 'Descricao da Oportunidade A', '2023-11-21 00:00:00', '2023-12-23 00:00:00', 1, 2),
(2, 'Oportunidade B', 'Descricao da Oportunidade B', '2022-12-01 00:00:00', '2023-12-30 00:00:00', 0, 2),
(3, 'Oportunidade C', 'Descricao da Oportunidade C', '2020-01-10 00:00:00', '2023-12-24 00:00:00', 0, 1),
(4, 'Oportunidade D', 'Descricao da Oportunidade D', '2017-02-20 00:00:00', '2021-02-25 00:00:00', 0, 1),
(5, 'Oportunidade E', 'Descricao da Oportunidade E', '2014-03-30 00:00:00', '2020-04-05 00:00:00', 0, 3);
