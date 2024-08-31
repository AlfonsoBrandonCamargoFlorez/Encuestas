document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo encuesta.js cargado');

    const createSurveyForm = document.getElementById('createSurveyForm');
    const searchSurveyForm = document.getElementById('searchSurveyForm');
    const updateSurveyForm = document.getElementById('updateSurveyForm');
    const deleteSurveyForm = document.getElementById('deleteSurveyForm');

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
});
