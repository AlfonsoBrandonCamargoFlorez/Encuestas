$(document).ready(function () {



    // Buscar capítulo
    $('#searchChapterForm').on('submit', function (e) {
        e.preventDefault();
        const chapterId = $('#searchId').val();
        $.get(`/api/chapter/${chapterId}`, function (response) {
            // Asegúrate de que los nombres de las propiedades coincidan con los nombres en la respuesta del backend
            $('#chapterId').text(response.id || 'No disponible');
            $('#chapterTitle').text(response.chapter_title || 'No disponible');
            $('#chapterNumber').text(response.chapter_number || 'No disponible');
            $('#surveysId').text(response.surveys ? response.surveys.id : 'No disponible'); // Si surveys es un objeto
            $('#surveysName').text(response.surveys ? response.surveys.name : 'No disponible'); // Nombre de la encuesta
            $('#chapterDetails').removeClass('d-none');
        }).fail(function () {
            alert('Capítulo no encontrado.');
        });
    });

    

    // Eliminar capítulo
    $('#deleteChapterForm').on('submit', function (e) {
        e.preventDefault();
        const chapterId = $('#deleteId').val();
        $.ajax({
            url: `/api/chapter/${chapterId}`,
            type: 'DELETE',
            success: function () {
                alert('Capítulo eliminado exitosamente.');
                $('#deleteModal').modal('hide');
            },
            error: function () {
                alert('Error al eliminar el capítulo.');
            }
        });
    });
});

//CREAR CAPITULO
document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo encuesta.js cargado');

    const createSurveyForm = document.getElementById('createChapterForm');


    if (createSurveyForm) {
        createSurveyForm.addEventListener('submit', async (event) => {
            event.preventDefault();

            titulo = document.getElementById("chapter_title").value;
            numero = document.getElementById("chapter_number").value;
            encuesta = parseInt(document.getElementById("surveys_id").value);




            const chapterData = {
                "chapter_title": titulo,
                "chapter_number": numero,
                surveys: { id: encuesta } // surveyId como objeto con una propiedad id
            };

            console.log(chapterData);
            try {
                const response = await fetch('/api/chapter', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(chapterData),
                });

                if (response.ok) {
                    alert('Capitulo creado exitosamente');
                    createSurveyForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                    modal.hide();
                } else {
                    alert('Error al crear capitulo');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }
});


// Actualizar capítulo
document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo actualizar.js cargado');

    document.getElementById('updateChapterForm').addEventListener('submit', async (event) => {
        event.preventDefault();

        const chapterId = document.getElementById('updateId').value;
        if (!chapterId) {
            alert('Por favor ingresa un ID de capítulo para actualizar.');
            return;
        }
        const formData = new FormData(event.target);
        const updatedChapterData = {};

        for (let [key, value] of formData.entries()) {
            updatedChapterData[key] = value;
        }

        console.log(updatedChapterData);

        try {
            const response = await fetch(`/api/chapter/${chapterId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedChapterData)
            });

            if (response.ok) {
                alert('Capítulo actualizado exitosamente.');
                event.target.reset();
                const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
                modal.hide();
            } else {
                alert('Error al actualizar el capítulo.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de red');
        }
    });
});
