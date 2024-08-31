document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo question.js cargado');

    // Obtener referencias a los formularios de preguntas
    const createQuestionForm = document.getElementById('createQuestionForm');
    const searchQuestionForm = document.getElementById('searchQuestionForm');
    const updateQuestionForm = document.getElementById('updateQuestionForm');
    const deleteQuestionForm = document.getElementById('deleteQuestionForm');

    // Manejar la creación de preguntas
    if (createQuestionForm) {
        createQuestionForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = new FormData(createQuestionForm);
            const data = Object.fromEntries(formData.entries());

            fetch('/api/questions', {
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
            })
                .then(response => {
                    if (response.ok) {
                        alert('Pregunta creada con éxito');
                        createQuestionForm.reset();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                        if (modal) modal.hide();
                    } else {
                        alert('Error al crear pregunta');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }

    // Manejar la búsqueda de preguntas
    if (searchQuestionForm) {
        searchQuestionForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const questionId = document.getElementById('searchId').value;

            fetch(`/api/questions/${questionId}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Pregunta no encontrada');
                    }
                })
                .then(question => {
                    document.getElementById('questionId').textContent = question.id;
                    document.getElementById('questionNumber').textContent = question.question_number;
                    document.getElementById('responseType').textContent = question.response_type;
                    document.getElementById('commentQuestion').textContent = question.comment_question;
                    document.getElementById('questionText').textContent = question.question_text;
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }

    // Manejar la actualización de preguntas
    if (updateQuestionForm) {
        updateQuestionForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = new FormData(updateQuestionForm);
            const data = Object.fromEntries(formData.entries());
            const questionId = data.id;

            fetch(`/api/questions/${questionId}`, {
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
            })
                .then(response => {
                    if (response.ok) {
                        alert('Pregunta actualizada con éxito');
                        updateQuestionForm.reset();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
                        if (modal) modal.hide();
                    } else {
                        alert('Error al actualizar pregunta');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }

    // Manejar la eliminación de preguntas
    if (deleteQuestionForm) {
        deleteQuestionForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const questionId = document.getElementById('deleteId').value;

            fetch(`/api/questions/${questionId}`, {
                method: 'DELETE',
            })
                .then(response => {
                    if (response.ok) {
                        alert('Pregunta eliminada con éxito');
                        deleteQuestionForm.reset();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('deleteModal'));
                        if (modal) modal.hide();
                    } else {
                        alert('Error al eliminar pregunta');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }

    // Obtener referencias a los formularios de usuarios
    const createUserForm = document.getElementById('createUserForm');
    const searchUserForm = document.getElementById('searchUserForm');
    const updateUserForm = document.getElementById('updateUserForm');
    const deleteUserForm = document.getElementById('deleteUserForm');

    // Manejar la creación de usuarios
    if (createUserForm) {
        createUserForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = new FormData(createUserForm);
            const data = Object.fromEntries(formData.entries());

            fetch('/api/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: data.name,
                    password: data.password,
                    enabled: true
                }),
            })
                .then(response => {
                    if (response.ok) {
                        alert('Usuario creado con éxito');
                        createUserForm.reset();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                        if (modal) modal.hide();
                    } else {
                        alert('Error al crear usuario');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }

    // Manejar la búsqueda de usuarios
    if (searchUserForm) {
        searchUserForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const userId = document.getElementById('searchId').value;

            fetch(`/api/users/${userId}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Usuario no encontrado');
                    }
                })
                .then(user => {
                    document.getElementById('userId').textContent = user.id;
                    document.getElementById('userName').textContent = user.username;
                    document.getElementById('userEnabled').textContent = user.enabled ? 'Sí' : 'No';
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }

    // Manejar la actualización de usuarios
    if (updateUserForm) {
        updateUserForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const formData = new FormData(updateUserForm);
            const data = Object.fromEntries(formData.entries());
            const userId = data.id;

            fetch(`/api/users/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: data.username,
                    password: data.password,
                    enabled: data.enabled === 'true', // Convertir a Booleano
                }),
            })
                .then(response => {
                    if (response.ok) {
                        alert('Usuario actualizado con éxito');
                        updateUserForm.reset();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
                        if (modal) modal.hide();
                    } else {
                        alert('Error al actualizar usuario');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }

    // Manejar la eliminación de usuarios
    if (deleteUserForm) {
        deleteUserForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const userId = document.getElementById('deleteId').value;

            fetch(`/api/users/${userId}`, {
                method: 'DELETE',
            })
                .then(response => {
                    if (response.ok) {
                        alert('Usuario eliminado con éxito');
                        deleteUserForm.reset();
                        const modal = bootstrap.Modal.getInstance(document.getElementById('deleteModal'));
                        if (modal) modal.hide();
                    } else {
                        alert('Error al eliminar usuario');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error de red');
                });
        });
    }
});
