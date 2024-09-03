document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo encuesta.js cargado');

    const createSurveyForm = document.getElementById('createSurveyForm');
    const searchSurveyForm = document.getElementById('searchSurveyForm');
    const updateSurveyForm = document.getElementById('updateSurveyForm');
    const deleteSurveyForm = document.getElementById('deleteSurveyForm');
    const surveyListContainer = document.getElementById('surveyList');

    if (createSurveyForm) {
        createSurveyForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(createSurveyForm);
            const data = Object.fromEntries(formData.entries());

            try {
                const response = await fetch('/api/surveys', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                });

                if (response.ok) {
                    alert('Encuesta creada exitosamente');
                    createSurveyForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                    modal.hide();
                } else {
                    alert('Error al crear encuesta');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    if (searchSurveyForm) {
        searchSurveyForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const surveyId = document.getElementById('searchId').value;

            try {
                const response = await fetch(`/api/surveys/${surveyId}`);
                if (response.ok) {
                    const survey = await response.json();
                    document.getElementById('surveyId').textContent = survey.id;
                    document.getElementById('surveyName').textContent = survey.name;
                    document.getElementById('surveyDescription').textContent = survey.description;
                } else {
                    alert('Encuesta no encontrada');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    if (updateSurveyForm) {
        updateSurveyForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(updateSurveyForm);
            const data = Object.fromEntries(formData.entries());
            const surveyId = data.id;

            try {
                const response = await fetch(`/api/surveys/${surveyId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        name: data.name,
                        description: data.description,
                    }),
                });

                if (response.ok) {
                    alert('Encuesta actualizada exitosamente');
                    updateSurveyForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
                    modal.hide();
                } else {
                    alert('Error al actualizar encuesta');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    if (deleteSurveyForm) {
        deleteSurveyForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const surveyId = document.getElementById('deleteId').value;

            try {
                const response = await fetch(`/api/surveys/${surveyId}`, {
                    method: 'DELETE',
                });

                if (response.ok) {
                    alert('Encuesta eliminada exitosamente');
                    deleteSurveyForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('deleteModal'));
                    modal.hide();
                } else {
                    alert('Error al eliminar encuesta');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    // Funcionalidad para listar encuestas
    document.querySelector('[data-bs-target="#listModal"]').addEventListener('click', async () => {
        try {
            const response = await fetch('/api/surveys');
            if (response.ok) {
                const surveys = await response.json();
                if (surveys.length === 0) {
                    surveyListContainer.innerHTML = '<p>No hay encuestas disponibles.</p>';
                } else {
                    const listHtml = surveys.map(survey => `
                        <div class="survey-item mb-3">
                            <p><strong>ID:</strong> ${survey.id}</p>
                            <p><strong>Nombre:</strong> ${survey.name}</p>
                            <p><strong>Descripción:</strong> ${survey.description}</p>
                            <button class="btn btn-primary btn-sm me-2" data-action="update" data-id="${survey.id}">Actualizar</button>
                            <button class="btn btn-danger btn-sm" data-action="delete" data-id="${survey.id}">Eliminar</button>
                            <hr>
                        </div>
                    `).join('');
                    surveyListContainer.innerHTML = listHtml;
                }
            } else {
                surveyListContainer.innerHTML = '<p>Error al cargar encuestas.</p>';
            }
        } catch (error) {
            console.error('Error:', error);
            surveyListContainer.innerHTML = '<p>Error de red.</p>';
        }
    });

    // Manejo de clics en los botones de actualizar y eliminar en el modal de listar
    surveyListContainer.addEventListener('click', async (event) => {
        const button = event.target;
        if (button.tagName === 'BUTTON') {
            const action = button.getAttribute('data-action');
            const surveyId = button.getAttribute('data-id');

            if (action === 'update') {
                // Rellenar el formulario de actualización con los datos de la encuesta
                const response = await fetch(`/api/surveys/${surveyId}`);
                if (response.ok) {
                    const survey = await response.json();
                    document.getElementById('updateId').value = survey.id;
                    document.getElementById('updateName').value = survey.name;
                    document.getElementById('updateDescription').value = survey.description;
                    const updateModal = new bootstrap.Modal(document.getElementById('updateModal'));
                    updateModal.show();
                } else {
                    alert('Error al cargar datos de la encuesta');
                }
            } else if (action === 'delete') {
                document.getElementById('deleteId').value = surveyId;
                const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                deleteModal.show();
            }
        }
    });
});
