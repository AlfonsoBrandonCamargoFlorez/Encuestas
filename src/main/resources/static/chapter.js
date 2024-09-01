$(document).ready(function () {
    // Crear capítulo
    $('#createChapterForm').on('submit', function (e) {
        e.preventDefault();
        const chapterData = {
            title: $('#chapter_title').val(),
            number: $('#chapter_number').val(),
            surveyId: $('#surveys_id').val()
        };
        $.post('/api/chapter', chapterData, function (response) {
            alert('Capítulo creado exitosamente.');
            $('#createModal').modal('hide');
        }).fail(function () {
            alert('Error al crear el capítulo.');
        });
    });

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

    // Actualizar capítulo
    $('#updateChapterForm').on('submit', function (e) {
        e.preventDefault();
        const chapterId = $('#updateId').val();
        const updatedChapterData = {
            title: $('#update_chapter_title').val(),
            number: $('#update_chapter_number').val(),
            surveyId: $('#update_surveys_id').val()
        };
        $.ajax({
            url: `/api/chapter/${chapterId}`,
            type: 'PUT',
            data: updatedChapterData,
            success: function () {
                alert('Capítulo actualizado exitosamente.');
                $('#updateModal').modal('hide');
            },
            error: function () {
                alert('Error al actualizar el capítulo.');
            }
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
