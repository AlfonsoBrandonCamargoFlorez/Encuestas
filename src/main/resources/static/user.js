document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo usuario.js cargado');

    const createUserForm = document.getElementById('createUserForm');
    const searchUserForm = document.getElementById('searchUserForm');
    const updateUserForm = document.getElementById('updateUserForm');
    const deleteUserForm = document.getElementById('deleteUserForm');

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

if (updateUserForm) {
    updateUserForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const formData = new FormData(updateUserForm);
        const data = Object.fromEntries(formData.entries());
        const userId = data.id;

        // Convertir roles seleccionados en un array de IDs
        const roles = Array.from(updateUserForm.querySelector('#updateRoles').selectedOptions).map(option => option.value);

        fetch(`/api/users/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: data.username,
                password: data.password,
                enabled: data.enabled === 'true', // Convertir a Booleano
                roles: roles // Enviar roles seleccionados
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
