CREATE TABLE professor(
    email VARCHAR(100),
    senha VARCHAR(150), -- QUAL E O MELHOR TIPO DE DADO P/ ARMAZENAR SENHAS?
    PRIMARY KEY (email)
);

CREATE TABLE aluno(
    email VARCHAR(100),
    senha VARCHAR(150),
    git VARCHAR(100),
    grupo VARCHAR(100),
    nome VARCHAR(100),
    PRIMARY KEY (email)
);

CREATE TABLE usuario( -- REVISAR RELACIONAMENTOS!!
    email VARCHAR(100),
    senha VARCHAR(20),
    PRIMARY KEY (email),
    FOREIGN KEY (email) REFERENCES professor(email),
    FOREIGN KEY (email) REFERENCES aluno(email)
);

CREATE TABLE grupo(
    id_grupo INTEGER NOT NULL,
    nome_grupo VARCHAR(30),
    PRIMARY KEY (id_grupo)
);

CREATE TABLE criterios(
    id_criterio INTEGER NOT NULL,
    descricao VARCHAR(100),
    PRIMARY KEY (id_criterio)
);

CREATE TABLE sprint(
    id_sprint INTEGER NOT NULL,
    data_sprint DATE,
    PRIMARY KEY (id_sprint)
);

CREATE TABLE pontos_grupo(
    id_pontos_grupo INTEGER NOT NULL,
    id_sprint INTEGER NOT NULL,
    id_grupo INTEGER NOT NULL,
    pontos DECIMAL(3,2),
    PRIMARY KEY (id_pontos_grupo),
    FOREIGN KEY (id_sprint) REFERENCES sprint(id_sprint),
    FOREIGN KEY (id_grupo) REFERENCES grupo(id_grupo)
);

CREATE TABLE avaliacao(
    id_avaliacao INTEGER NOT NULL,
    aluno_avaliado VARCHAR(100),
    aluno_avaliador VARCHAR(100),
    id_sprint INTEGER NOT NULL,
    nota DECIMAL(3,2),
    PRIMARY KEY (id_avaliacao),
    FOREIGN KEY (id_sprint) REFERENCES sprint(id_sprint),
    FOREIGN KEY (aluno_avaliado) REFERENCES aluno(email),
    FOREIGN KEY (aluno_avaliador) REFERENCES aluno(email)
)

