CREATE USER AdminPacer WITH PASSWORD 'AdminPacer1234';

GRANT SELECT, INSERT, UPDATE, DELETE ON aluno TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON avaliacao TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON criterios TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON grupo TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON pontos_grupo TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON professor TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON sprint TO AdminPacer;
GRANT SELECT, INSERT, UPDATE, DELETE ON usuario TO AdminPacer;