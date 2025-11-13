INSERT INTO users (username, password, email, first_name, last_name, role, created_at)
VALUES ('admin',
        '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
        'admin@example.com',
        '系统',
        '管理员',
        'ADMIN',
        NOW());

INSERT INTO users (username, password, email, first_name, last_name, role, created_at)
VALUES ('user1',
        '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
        'user1@example.com',
        '张',
        '三',
        'USER',
        NOW());