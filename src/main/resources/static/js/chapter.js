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
        titulo = document.getElementById("update_chapter_title").value;
        numero = document.getElementById("update_chapter_number").value;
        encuesta = parseInt(document.getElementById("update_surveys_id").value);




        const chapterData = {
            "chapter_title": titulo,
            "chapter_number": numero,
            surveys: { id: encuesta } // surveyId como objeto con una propiedad id
        };
        console.log(chapterData);

     

        try {
            const response = await fetch(`/api/chapter/${chapterId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(chapterData)
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
                    <td>
                        <button class="btn btn-warning btn-sm me-2" onclick="openEditModal(${chapter.id})">Actualizar</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteChapter(${chapter.id})">Eliminar</button>
                    </td>
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

// Función para eliminar un capítulo
async function deleteChapter(chapterId) {
    if (confirm('¿Estás seguro de que deseas eliminar este capítulo?')) {
        try {
            const response = await fetch(`/api/chapter/${chapterId}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Capítulo eliminado exitosamente.');
                listChapters(); // Actualiza la lista después de eliminar
            } else {
                alert('Error al eliminar el capítulo.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de red');
        }
    }
}

// Función para abrir el modal de edición y prellenar el formulario
function openEditModal(chapterId) {
    fetch(`/api/chapter/${chapterId}`)
        .then(response => response.json())
        .then(chapter => {
            document.getElementById('updateId').value = chapter.id;
            document.getElementById('update_chapter_title').value = chapter.chapter_title;
            document.getElementById('update_chapter_number').value = chapter.chapter_number;
            document.getElementById('update_surveys_id').value = chapter.surveys ? chapter.surveys.id : '';
            const updateModal = new bootstrap.Modal(document.getElementById('updateModal'));
            updateModal.show();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al obtener datos del capítulo.');
        });
}

// Carga la lista de capítulos cuando se abre el modal
document.getElementById('listModal').addEventListener('shown.bs.modal', () => {
    listChapters();
});
