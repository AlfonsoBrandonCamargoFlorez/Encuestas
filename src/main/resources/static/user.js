// usuario.js
document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo usuario.js cargado');

    const createUserForm = document.getElementById('createUserForm');
    const searchUserForm = document.getElementById('searchUserForm');
    const updateUserForm = document.getElementById('updateUserForm');
    const deleteUserForm = document.getElementById('deleteUserForm');

    if (createUserForm) {
        createUserForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(createUserForm);
            const data = Object.fromEntries(formData.entries());

            try {
                const response = await fetch('/api/users', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: data.name,
                        password: data.password,
                        enabled: true
                    }),
                });

                if (response.ok) {
                    alert('Usuario creado con éxito');
                    createUserForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('createModal'));
                    if (modal) modal.hide();
                } else {
                    alert('Error al crear usuario');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    if (searchUserForm) {
        searchUserForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const userId = document.getElementById('searchId').value;

            try {
                const response = await fetch(`/api/users/${userId}`);
                if (response.ok) {
                    const user = await response.json();
                    document.getElementById('userId').textContent = user.id;
                    document.getElementById('userName').textContent = user.username;
                    document.getElementById('userEnabled').textContent = user.enabled ? 'Sí' : 'No';
                } else {
                    alert('Usuario no encontrado');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    if (updateUserForm) {
        updateUserForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(updateUserForm);
            const data = Object.fromEntries(formData.entries());
            const userId = data.id;

            try {
                const response = await fetch(`/api/users/${userId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: data.username,
                        password: data.password,
                        enabled: data.enabled === 'true', // Convertir a Booleano
                    }),
                });

                if (response.ok) {
                    alert('Usuario actualizado con éxito');
                    updateUserForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
                    if (modal) modal.hide();
                } else {
                    alert('Error al actualizar usuario');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    if (deleteUserForm) {
        deleteUserForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const userId = document.getElementById('deleteId').value;

            try {
                const response = await fetch(`/api/users/${userId}`, {
                    method: 'DELETE',
                });

                if (response.ok) {
                    alert('Usuario eliminado con éxito');
                    deleteUserForm.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById('deleteModal'));
                    if (modal) modal.hide();
                } else {
                    alert('Error al eliminar usuario');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }
});
