$(document).ready(function () {

    // Buscar pregunta
    $('#searchQuestionForm').on('submit', function (e) {
        e.preventDefault();
        const questionId = $('#searchId').val();
        $.get(`/api/questions/${questionId}`, function (response) {
            // Asegúrate de que los nombres de las propiedades coincidan con los nombres en la respuesta del backend
            $('#questionId').text(response.id || 'No disponible');
            $('#questionNumber').text(response.question_number || 'No disponible');
            $('#responseType').text(response.response_type || 'No disponible');
            $('#questionText').text(response.question_text || 'No disponible');
            $('#commentQuestion').text(response.comment_question || 'No disponible');
            $('#chapterId').text(response.chapter ? response.chapter.id : 'No disponible'); // Si chapter es un objeto
            $('#questionDetails').removeClass('d-none');
        }).fail(function () {
            alert('Pregunta no encontrada.');
        });
    });

    // Eliminar pregunta
    $('#deleteQuestionForm').on('submit', function (e) {
        e.preventDefault();
        const questionId = $('#deleteId').val();
        $.ajax({
            url: `/api/questions/${questionId}`,
            type: 'DELETE',
            success: function () {
                alert('Pregunta eliminada exitosamente.');
                $('#deleteModal').modal('hide');
                listQuestions(); // Actualiza la lista después de eliminar
            },
            error: function () {
                alert('Error al eliminar la pregunta.');
            }
        });
    });
});

// CREAR PREGUNTA
document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo question.js cargado');

    const createQuestionForm = document.getElementById('createQuestionForm');

    if (createQuestionForm) {
        createQuestionForm.addEventListener('submit', async (event) => {
            event.preventDefault();

            const number = document.getElementById("question_number").value;
            const type = document.getElementById("response_type").value;
            const text = document.getElementById("question_text").value;
            const comment = document.getElementById("comment_question").value;
            const chapterId = parseInt(document.getElementById("chapter_id").value);

            const questionData = {
                "question_number": number,
                "response_type": type,
                "question_text": text,
                "comment_question": comment,
                "chapter": { "id": chapterId } // chapterId como objeto con una propiedad id
            };

            console.log(questionData);
            try {
                const response = await fetch('/api/questions', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(questionData),
                });

                if (response.ok) {
                    alert('Pregunta creada exitosamente');
                    createQuestionForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                    modal.hide();
                    listQuestions(); // Actualiza la lista después de crear una nueva pregunta
                } else {
                    alert('Error al crear la pregunta');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }
});

// Actualizar pregunta
document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo update.js cargado');

    document.getElementById('updateQuestionForm').addEventListener('submit', async (event) => {
        event.preventDefault();

        const questionId = document.getElementById('updateId').value;
        if (!questionId) {
            alert('Por favor ingresa un ID de pregunta para actualizar.');
            return;
        }
        const number = document.getElementById("update_question_number").value;
        const type = document.getElementById("update_response_type").value;
        const text = document.getElementById("update_question_text").value;
        const comment = document.getElementById("update_comment_question").value;
        const chapterId = parseInt(document.getElementById("update_chapter_id").value);

        const questionData = {
            "question_number": number,
            "response_type": type,
            "question_text": text,
            "comment_question": comment,
            "chapter": { "id": chapterId } // chapterId como objeto con una propiedad id
        };
        console.log(questionData);

        try {
            const response = await fetch(`/api/questions/${questionId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(questionData)
            });

            if (response.ok) {
                alert('Pregunta actualizada exitosamente.');
                event.target.reset();
                const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
                modal.hide();
                listQuestions(); // Actualiza la lista después de actualizar una pregunta
            } else {
                alert('Error al actualizar la pregunta.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de red');
        }
    });
});

// Función para listar todas las preguntas
async function listQuestions() {
    try {
        const response = await fetch('/api/questions');
        if (response.ok) {
            const questions = await response.json();
            const questionsList = document.getElementById('questionsList');

            // Limpia la tabla antes de agregar los nuevos datos
            questionsList.innerHTML = '';

            questions.forEach(question => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${question.id}</td>
                    <td>${question.question_number}</td>
                    <td>${question.response_type}</td>
                    <td>${question.question_text}</td>
                    <td>${question.comment_question || 'No disponible'}</td>
                    <td>${question.chapter ? question.chapter.id : 'No disponible'}</td>
                    <td>
                        <button class="btn btn-warning btn-sm me-2" onclick="openEditModal(${question.id})">Actualizar</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteQuestion(${question.id})">Eliminar</button>
                    </td>
                `;
                questionsList.appendChild(row);
            });
        } else {
            alert('Error al cargar la lista de preguntas.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error de red');
    }
}

// Función para eliminar una pregunta
async function deleteQuestion(questionId) {
    if (confirm('¿Estás seguro de que deseas eliminar esta pregunta?')) {
        try {
            const response = await fetch(`/api/questions/${questionId}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Pregunta eliminada exitosamente.');
                listQuestions(); // Actualiza la lista después de eliminar
            } else {
                alert('Error al eliminar la pregunta.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de red');
        }
    }
}

// Función para abrir el modal de edición y prellenar el formulario
function openEditModal(questionId) {
    fetch(`/api/questions/${questionId}`)
        .then(response => response.json())
        .then(question => {
            document.getElementById('updateId').value = question.id;
            document.getElementById('update_question_number').value = question.question_number;
            document.getElementById('update_response_type').value = question.response_type;
            document.getElementById('update_question_text').value = question.question_text;
            document.getElementById('update_comment_question').value = question.comment_question || '';
            document.getElementById('update_chapter_id').value = question.chapter ? question.chapter.id : '';
            const updateModal = new bootstrap.Modal(document.getElementById('updateModal'));
            updateModal.show();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al obtener datos de la pregunta.');
        });
}

// Carga la lista de preguntas cuando se abre el modal
document.getElementById('listModal').addEventListener('shown.bs.modal', () => {
    listQuestions();
});
