$(document).ready(function () {
    // Crear capítulo
    $('#createChapterForm').on('submit', function (e) {
        e.preventDefault();
        const chapterData = {
            title: $('#chapter_title').val(),
            number: $('#chapter_number').val(),
            surveyId: $('#surveys_id').val()
        };
        $.post('/api/chapters', chapterData, function (response) {
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
        $.get(`/api/chapters/${chapterId}`, function (response) {
            $('#chapterId').text(response.id);
            $('#chapterTitle').text(response.title);
            $('#chapterNumber').text(response.number);
            $('#surveysId').text(response.surveyId);
            $('#searchModal').modal('hide');
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
            url: `/api/chapters/${chapterId}`,
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
            url: `/api/chapters/${chapterId}`,
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
