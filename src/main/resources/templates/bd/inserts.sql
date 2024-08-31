
-- Insertar datos en `roles`
INSERT INTO roles
    (name)
VALUES
    ('Administrador'),
    ('Usuario');

-- Insertar datos en `categories_catalog`
INSERT INTO categories_catalog
    (name, create_at, updated_at)
VALUES
    ('Categoría 1', NOW(), NOW()),
    ('Categoría 2', NOW(), NOW());

-- Insertar datos en `surveys`
INSERT INTO surveys
    (description, name, create_at, updated_at)
VALUES
    ('Encuesta de satisfacción del cliente', 'Encuesta 2024', NOW(), NOW());

-- Insertar datos en `chapter`
INSERT INTO chapter
    (surveys_id, chapter_title, chapter_number, create_at, updated_at)
VALUES
    (1, 'Capítulo 1', '001', NOW(), NOW()),
    (1, 'Capítulo 2', '002', NOW(), NOW());

-- Insertar datos en `questions`
INSERT INTO questions
    (chapter_id, question_number, response_type, comment_question, question_text, create_at, updated_at)
VALUES
    (1, 'Q1', 'Texto', 'Pregunta sobre el servicio', '¿Cómo calificaría el servicio?', NOW(), NOW()),
    (2, 'Q2', 'Opción', 'Pregunta sobre el producto', '¿Qué producto utilizó?', NOW(), NOW());

-- Insertar datos en `response_options`
INSERT INTO response_options
    (option_value, typecomponenthtml, comment_response, option_text, questions_id, categories_id, response_options_id, create_at, updated_at)
VALUES
    ('Opción 1', 'radio', 'Primera opción de respuesta', 'Opción 1', 1, 1, NULL, NOW(), NOW()),
    ('Opción 2', 'checkbox', 'Segunda opción de respuesta', 'Opción 2', 1, 1, NULL, NOW(), NOW());

-- Insertar datos en `subresponse_options`
INSERT INTO subresponse_options
    (component_html, subresponse_text, response_options_id, create_at, updated_at)
VALUES
    ('sub1', 'Subrespuesta 1', 1, NOW(), NOW()),
    ('sub2', 'Subrespuesta 2', 1, NOW(), NOW());

-- Insertar datos en `survey_json`
INSERT INTO survey_json
    (surveys_id, payload, create_at, updated_at)
VALUES
    (1, '{"questions":[{"id":1,"text":"¿Cómo calificaría el servicio?"}]}', NOW(), NOW());

-- Insertar datos en `users`
INSERT INTO users
    (username, password, enabled)
VALUES
    ('admin', 'password123', TRUE),
    ('user1', 'userpass456', TRUE);

-- Insertar datos en `users_roles`
INSERT INTO users_roles
    (users_id, roles_id)
VALUES
    (1, 1),
    (2, 2);
