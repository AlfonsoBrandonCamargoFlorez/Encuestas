// question.js
document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo question.js cargado');

    // Obtener referencias a los formularios
    const createQuestionForm = document.getElementById('createQuestionForm');
    const searchQuestionForm = document.getElementById('searchQuestionForm');
    const updateQuestionForm = document.getElementById('updateQuestionForm');
    const deleteQuestionForm = document.getElementById('deleteQuestionForm');

    // Manejar la creación de preguntas
    if (createQuestionForm) {
        createQuestionForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(createQuestionForm);
            const data = Object.fromEntries(formData.entries());

            try {
                const response = await fetch('/api/questions', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        question_number: data.question_number,
                        response_type: data.response_type,
                        comment_question: data.comment_question,
                        question_text: data.question_text
                    }),
                });

                if (response.ok) {
                    alert('Pregunta creada con éxito');
                    createQuestionForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                    if (modal) modal.hide();
                } else {
                    alert('Error al crear pregunta');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    // Manejar la búsqueda de preguntas
    if (searchQuestionForm) {
        searchQuestionForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const questionId = document.getElementById('searchId').value;

            try {
                const response = await fetch(`/api/questions/${questionId}`);
                if (response.ok) {
                    const question = await response.json();
                    document.getElementById('questionId').textContent = question.id;
                    document.getElementById('questionNumber').textContent = question.question_number;
                    document.getElementById('responseType').textContent = question.response_type;
                    document.getElementById('commentQuestion').textContent = question.comment_question;
                    document.getElementById('questionText').textContent = question.question_text;
                } else {
                    alert('Pregunta no encontrada');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    // Manejar la actualización de preguntas
    if (updateQuestionForm) {
        updateQuestionForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(updateQuestionForm);
            const data = Object.fromEntries(formData.entries());
            const questionId = data.id;

            try {
                const response = await fetch(`/api/questions/${questionId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        question_number: data.question_number,
                        response_type: data.response_type,
                        comment_question: data.comment_question,
                        question_text: data.question_text
                    }),
                });

                if (response.ok) {
                    alert('Pregunta actualizada con éxito');
                    updateQuestionForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
                    if (modal) modal.hide();
                } else {
                    alert('Error al actualizar pregunta');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    // Manejar la eliminación de preguntas
    if (deleteQuestionForm) {
        deleteQuestionForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const questionId = document.getElementById('deleteId').value;

            try {
                const response = await fetch(`/api/questions/${questionId}`, {
                    method: 'DELETE',
                });

                if (response.ok) {
                    alert('Pregunta eliminada con éxito');
                    deleteQuestionForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('deleteModal'));
                    if (modal) modal.hide();
                } else {
                    alert('Error al eliminar pregunta');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }
});
