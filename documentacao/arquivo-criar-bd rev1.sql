CREATE TABLE aluno(
    id SERIAL,
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(150),
    git VARCHAR(100),
    grupo VARCHAR(100),
    nome VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE usuario(
    id SERIAL,
    email VARCHAR(100),
    senha VARCHAR(20),
    PRIMARY KEY (email),
    FOREIGN KEY (email) REFERENCES aluno(email)
);

CREATE TABLE grupo(
    id SERIAL,
    nome_grupo VARCHAR(30),
    PRIMARY KEY (id)
);

CREATE TABLE criterios(
    id SERIAL,
    criterio VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE sprint(
    id SERIAL,
    sprint INTEGER NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    PRIMARY KEY (id)
);

CREATE TABLE pontos_grupo(
    id SERIAL,
    id_sprint INTEGER NOT NULL,
    id_grupo INTEGER NOT NULL,
    pontos DECIMAL(3,2),
    PRIMARY KEY (id),
    FOREIGN KEY (id_sprint) REFERENCES sprint(id),
    FOREIGN KEY (id_grupo) REFERENCES grupo(id)
);

CREATE TABLE avaliacao(
    id SERIAL,
    id_aluno_avaliador INTEGER NOT NULL,
    id_aluno_avaliado INTEGER NOT NULL,
    id_grupo INTEGER NOT NULL,
    id_sprint INTEGER NOT NULL,
    id_criterio INTEGER NOT NULL,
    nota DECIMAL(3,2),
    PRIMARY KEY (id),
    FOREIGN KEY (id_aluno_avaliador) REFERENCES aluno(id),
    FOREIGN KEY (id_aluno_avaliado) REFERENCES aluno(id),
    FOREIGN KEY (id_grupo) REFERENCES grupo(id),
    FOREIGN KEY (id_sprint) REFERENCES sprint(id),
    FOREIGN KEY (id_criterio) REFERENCES criterios(id)
);

-- CRIAÇÃO DE USUÁRIO PARA MANIPULAR O BANCO

CREATE USER AdminPacer WITH PASSWORD 'AdminPacer1234';

-- ACESSO DO USUÁRIO ÀS TABELAS

GRANT SELECT, INSERT, UPDATE, DELETE ON aluno TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON avaliacao TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON criterios TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON grupo TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON pontos_grupo TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON sprint TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON usuario TO AdminPacer;

-- ACESSO DO USUÁRIOS ÀS TABELAS id_seq

GRANT SELECT, INSERT, UPDATE ON aluno_id_seq TO AdminPacer;
GRANT SELECT, INSERT, UPDATE ON avaliacao_id_seq TO AdminPacer;
GRANT SELECT, INSERT, UPDATE ON criterios_id_seq TO AdminPacer;
GRANT SELECT, INSERT, UPDATE ON grupo_id_seq TO AdminPacer;
GRANT SELECT, INSERT, UPDATE ON pontos_grupo_id_seq TO AdminPacer;
GRANT SELECT, INSERT, UPDATE ON sprint_id_seq TO AdminPacer;
GRANT SELECT, INSERT, UPDATE ON usuario_id_seq TO AdminPacer;