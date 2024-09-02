document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo usuario.js cargado');

    // Función para llenar el selector de roles
    async function loadRoles() {
        try {
            const response = await fetch('/api/roles');
            const roles = await response.json();
            const roleSelect = document.getElementById('updateRoles');
            roleSelect.innerHTML = ''; // Limpiar opciones actuales
            roles.forEach(role => {
                const option = document.createElement('option');
                option.value = role.id;
                option.textContent = role.name;
                roleSelect.appendChild(option);
            });
        } catch (error) {
            console.error('Error al cargar roles:', error);
            alert('Error al cargar roles');
        }
    }

    loadRoles(); // Cargar roles al iniciar

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
                    // Seleccionar roles en el selector
                    const roleSelect = document.getElementById('updateRoles');
                    roleSelect.value = user.roles.map(role => role.id); // Ajustar esto según cómo estén estructurados los roles
                } else {
                    alert('Usuario no encontrado');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de red');
            }
        });
    }

    // if (updateUserForm) {
    //     updateUserForm.addEventListener('submit', async (event) => {
    //         event.preventDefault();
    //         const formData = new FormData(updateUserForm);
    //         const data = Object.fromEntries(formData.entries());
    //         const userId = data.id;

    //         // Convertir roles seleccionados en un array de IDs
    //         const roles = Array.from(updateUserForm.querySelector('#updateRoles').selectedOptions).map(option => option.value);
    //         console.log('Roles seleccionados:', roles); // Depuración

    //         try {
    //             const response = await fetch(`/api/users/${userId}`, {
    //                 method: 'PUT',
    //                 headers: {
    //                     'Content-Type': 'application/json',
    //                 },
    //                 body: JSON.stringify({
    //                     username: data.username,
    //                     password: data.password,
    //                     enabled: data.enabled === 'true', // Convertir a Booleano
    //                     roles: roles // Enviar roles seleccionados
    //                 }),
    //             });

    //             if (response.ok) {
    //                 alert('Usuario actualizado con éxito');
    //                 updateUserForm.reset();
    //                 const modal = bootstrap.Modal.getInstance(document.getElementById('updateModal'));
    //                 if (modal) modal.hide();
    //             } else {
    //                 alert('Error al actualizar usuario');
    //             }
    //         } catch (error) {
    //             console.error('Error:', error);
    //             alert('Error de red');
    //         }
    //     });
    // }

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

// Actualizar capítulo
document.addEventListener('DOMContentLoaded', () => {
    console.log('Archivo actualizar.js cargado');

    document.getElementById('updateUserForm').addEventListener('submit', async (event) => {
        event.preventDefault();

        const userId = document.getElementById('updateId').value;
        if (!userId) {
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
            const response = await fetch(`/api/user/${chapterId}`, {
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
