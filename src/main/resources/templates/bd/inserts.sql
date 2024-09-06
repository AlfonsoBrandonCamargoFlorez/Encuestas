

-- Insertar datos en `categories_catalog`
INSERT INTO categories_catalog (name, create_at, updated_at) VALUES
    ('Categoría 1', NOW(), NOW()),
    ('Categoría 2', NOW(), NOW()),
    ('Categoría 3', NOW(), NOW()),
    ('Categoría 4', NOW(), NOW()),
    ('Categoría 5', NOW(), NOW()),
    ('Categoría 6', NOW(), NOW()),
    ('Categoría 7', NOW(), NOW()),
    ('Categoría 8', NOW(), NOW()),
    ('Categoría 9', NOW(), NOW()),
    ('Categoría 10', NOW(), NOW());

-- Insertar datos en `surveys`
INSERT INTO surveys (description, name, create_at, updated_at) VALUES
    ('Encuesta de satisfacción del cliente', 'Encuesta 2024', NOW(), NOW()),
    ('Encuesta de feedback del producto', 'Encuesta 2024 - Producto', NOW(), NOW()),
    ('Encuesta de evaluación de empleados', 'Encuesta 2024 - Empleados', NOW(), NOW()),
    ('Encuesta de calidad del servicio', 'Encuesta 2024 - Servicio', NOW(), NOW()),
    ('Encuesta de opinión del usuario', 'Encuesta 2024 - Opinión', NOW(), NOW()),
    ('Encuesta de satisfacción del proveedor', 'Encuesta 2024 - Proveedores', NOW(), NOW()),
    ('Encuesta de mercado', 'Encuesta 2024 - Mercado', NOW(), NOW()),
    ('Encuesta de eventos', 'Encuesta 2024 - Eventos', NOW(), NOW()),
    ('Encuesta de clientes potenciales', 'Encuesta 2024 - Potenciales', NOW(), NOW()),
    ('Encuesta de desempeño', 'Encuesta 2024 - Desempeño', NOW(), NOW());

-- Insertar datos en `chapter`
INSERT INTO chapter (surveys_id, chapter_title, chapter_number, create_at, updated_at) VALUES
    (1, 'Capítulo 1', '001', NOW(), NOW()),
    (1, 'Capítulo 2', '002', NOW(), NOW()),
    (1, 'Capítulo 3', '003', NOW(), NOW()),
    (2, 'Capítulo 1', '001', NOW(), NOW()),
    (2, 'Capítulo 2', '002', NOW(), NOW()),
    (2, 'Capítulo 3', '003', NOW(), NOW()),
    (3, 'Capítulo 1', '001', NOW(), NOW()),
    (3, 'Capítulo 2', '002', NOW(), NOW()),
    (3, 'Capítulo 3', '003', NOW(), NOW()),
    (4, 'Capítulo 1', '001', NOW(), NOW());

-- Insertar datos en `questions`
INSERT INTO questions (chapter_id, question_number, response_type, comment_question, question_text, create_at, updated_at) VALUES
    (1, 'Q1', 'Texto', 'Pregunta sobre el servicio', '¿Cómo calificaría el servicio?', NOW(), NOW()),
    (1, 'Q2', 'Opción', 'Pregunta sobre el producto', '¿Qué producto utilizó?', NOW(), NOW()),
    (2, 'Q3', 'Texto', 'Pregunta sobre la calidad', '¿Cómo califica la calidad del producto?', NOW(), NOW()),
    (2, 'Q4', 'Opción', 'Pregunta sobre la satisfacción', '¿Está satisfecho con el producto?', NOW(), NOW()),
    (3, 'Q5', 'Texto', 'Pregunta sobre el ambiente laboral', '¿Cómo califica el ambiente laboral?', NOW(), NOW()),
    (3, 'Q6', 'Opción', 'Pregunta sobre la comunicación', '¿Cómo califica la comunicación en el trabajo?', NOW(), NOW()),
    (4, 'Q7', 'Texto', 'Pregunta sobre el tiempo de respuesta', '¿Cómo califica el tiempo de respuesta del soporte?', NOW(), NOW()),
    (4, 'Q8', 'Opción', 'Pregunta sobre el valor por dinero', '¿Cómo califica el valor por dinero del producto?', NOW(), NOW()),
    (5, 'Q9', 'Texto', 'Pregunta sobre la facilidad de uso', '¿Cómo califica la facilidad de uso del producto?', NOW(), NOW()),
    (5, 'Q10', 'Opción', 'Pregunta sobre la recomendación', '¿Recomendaría este producto a otros?', NOW(), NOW());

-- Insertar datos en `response_options`
INSERT INTO response_options (option_value, typecomponenthtml, comment_response, option_text, questions_id, categories_id, response_options_id, create_at, updated_at) VALUES
    ('Opción 1', 'radio', 'Primera opción de respuesta', 'Opción 1', 1, 1, NULL, NOW(), NOW()),
    ('Opción 2', 'checkbox', 'Segunda opción de respuesta', 'Opción 2', 1, 1, NULL, NOW(), NOW()),
    ('Opción 3', 'radio', 'Tercera opción de respuesta', 'Opción 3', 2, 2, NULL, NOW(), NOW()),
    ('Opción 4', 'checkbox', 'Cuarta opción de respuesta', 'Opción 4', 2, 2, NULL, NOW(), NOW()),
    ('Opción 5', 'radio', 'Quinta opción de respuesta', 'Opción 5', 3, 3, NULL, NOW(), NOW()),
    ('Opción 6', 'checkbox', 'Sexta opción de respuesta', 'Opción 6', 3, 3, NULL, NOW(), NOW()),
    ('Opción 7', 'radio', 'Séptima opción de respuesta', 'Opción 7', 4, 4, NULL, NOW(), NOW()),
    ('Opción 8', 'checkbox', 'Octava opción de respuesta', 'Opción 8', 4, 4, NULL, NOW(), NOW()),
    ('Opción 9', 'radio', 'Novena opción de respuesta', 'Opción 9', 5, 5, NULL, NOW(), NOW()),
    ('Opción 10', 'checkbox', 'Décima opción de respuesta', 'Opción 10', 5, 5, NULL, NOW(), NOW());

-- Insertar datos en `subresponse_options`
INSERT INTO subresponse_options (component_html, subresponse_text, response_options_id, create_at, updated_at) VALUES
    ('sub1', 'Subrespuesta 1', 1, NOW(), NOW()),
    ('sub2', 'Subrespuesta 2', 1, NOW(), NOW()),
    ('sub3', 'Subrespuesta 3', 2, NOW(), NOW()),
    ('sub4', 'Subrespuesta 4', 2, NOW(), NOW()),
    ('sub5', 'Subrespuesta 5', 3, NOW(), NOW()),
    ('sub6', 'Subrespuesta 6', 3, NOW(), NOW()),
    ('sub7', 'Subrespuesta 7', 4, NOW(), NOW()),
    ('sub8', 'Subrespuesta 8', 4, NOW(), NOW()),
    ('sub9', 'Subrespuesta 9', 5, NOW(), NOW()),
    ('sub10', 'Subrespuesta 10', 5, NOW(), NOW());

-- Insertar datos en `survey_json`
-- INSERT INTO survey_json (surveys_id, payload, create_at, updated_at) VALUES
--     (1, '{"questions":[{"id":1,"text":"¿Cómo calificaría el servicio?"},{"id":2,"text":"¿Qué producto utilizó?"}]}', NOW(), NOW()),
--     (2, '{"questions":[{"id":3,"text":"¿Cómo califica la calidad del producto?"},{"id":4,"text":"¿Está satisfecho con el producto?"}]}', NOW(), NOW()),
--     (3, '{"questions":[{"id":5,"text":"¿Cómo califica el ambiente laboral?"},{"id":6,"text":"¿Cómo califica la comunicación en el trabajo?"}]}', NOW(), NOW()),
--     (4, '{"questions":[{"id":7,"text":"¿Cómo califica el tiempo de respuesta del soporte?"},{"id":8,"text":"¿Cómo califica el valor por dinero del producto?"}]}', NOW(), NOW()),
--     (5, '{"questions":[{"id":9,"text":"¿Cómo califica la facilidad de uso del producto?"},{"id":10,"text":"¿Recomendaría este producto a otros?"}]}', NOW(), NOW());
