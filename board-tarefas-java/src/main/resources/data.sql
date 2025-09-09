INSERT INTO users (id, name, email) VALUES
(1, 'Alice', 'alice@example.com'),
(2, 'Bob', 'bob@example.com');

INSERT INTO tasks (title, description, status, created_at, updated_at, assigned_to) VALUES
('Planejar release', 'Definir escopo e datas', 'TODO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
('Criar DAO', 'Implementar camada de acesso a dados', 'DOING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
('Refatorar serviços', 'Melhorar design e testes', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null);
