document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo encuesta.js cargado');

    const surveyListContainer = document.getElementById('surveyList');

   

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


});


// Función para listar todos los capítulos
async function listChapters() {
    try {
        const response = await fetch('/api/chapter');
        if (response.ok) {
            const chapters = await response.json();
            const chaptersList = document.getElementById('chaptersList');

            // Limpia la tabla antes de agregar los nuevos datos
            chaptersList.innerHTML = '';

            chapters.forEach(chapter => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${chapter.id}</td>
                    <td>${chapter.chapter_title}</td>
                    <td>${chapter.chapter_number}</td>
                    <td>${chapter.surveys ? chapter.surveys.id : 'No disponible'}</td>
                    <td>${chapter.surveys ? chapter.surveys.name : 'No disponible'}</td>
                 
                `;
                chaptersList.appendChild(row);
            });
        } else {
            alert('Error al cargar la lista de capítulos.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error de red');
    }
}


// Carga la lista de capítulos cuando se abre el modal
document.getElementById('listModal1').addEventListener('shown.bs.modal', () => {
    listChapters();
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

// Carga la lista de preguntas cuando se abre el modal
document.getElementById('listModal2').addEventListener('shown.bs.modal', () => {
    listQuestions();
});