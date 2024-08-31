document.addEventListener("DOMContentLoaded", () => {
  function handleCreateUserSubmit(e) {
    e.preventDefault();

    const submitButton = this.querySelector('button[type="submit"]');
    submitButton.disabled = true;

    const data = new FormData(this);
    fetch('/api/users', {
      method: 'POST',
      body: JSON.stringify({
        username: data.get('name'),
        password: data.get('password'),
        enabled: true
      }),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then(data => {
        console.log('Usuario creado:', data);
        alert('Usuario creado con éxito');
      })
      .catch(error => {
        console.error('Error:', error);
        alert('Error al crear usuario');
      })
      .finally(() => {
        submitButton.disabled = false;
      });
  }

  function handleSearchUserSubmit(e) {
    e.preventDefault();

    const submitButton = this.querySelector('button[type="submit"]');
    submitButton.disabled = true;

    const data = new FormData(this);
    fetch(`/api/users/${encodeURIComponent(data.get('id'))}`, {
      method: 'GET'
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al buscar usuario');
        }
        return response.json();
      })
      .then(userData => {
        console.log('Usuario encontrado:', userData);
        // Mostrar los datos del usuario en el HTML
        document.getElementById('userId').textContent = userData.id || 'No disponible';
        document.getElementById('userName').textContent = userData.username || 'No disponible';
        document.getElementById('userEnabled').textContent = userData.enabled ? 'Sí' : 'No';
      })
      .catch(error => {
        console.error('Error:', error);
        alert('Error al buscar usuario');
      })
      .finally(() => {
        submitButton.disabled = false;
      });
  }

  function handleUpdateUserSubmit(e) {
    e.preventDefault();

    const submitButton = this.querySelector('button[type="submit"]');
    submitButton.disabled = true;

    const data = new FormData(this);
    fetch(`/api/users/${encodeURIComponent(data.get('id'))}`, {
      method: 'PUT',
      body: JSON.stringify({
        username: data.get('username'),
        password: data.get('password'),
        enabled: data.get('enabled') === 'true' // Convertir a Booleano
      }),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then(data => {
        console.log('Usuario actualizado:', data);
        alert('Usuario actualizado con éxito');
      })
      .catch(error => {
        console.error('Error:', error);
        alert('Error al actualizar usuario');
      })
      .finally(() => {
        submitButton.disabled = false;
      });
  }

  function handleDeleteUserSubmit(e) {
    e.preventDefault();

    const submitButton = this.querySelector('button[type="submit"]');
    submitButton.disabled = true;

    const data = new FormData(this);
    fetch(`/api/users/${encodeURIComponent(data.get('id'))}`, {
      method: 'DELETE'
    })
      .then(response => {
        if (response.ok) {
          console.log('Usuario eliminado');
          alert('Usuario eliminado con éxito');
        } else {
          throw new Error('Error al eliminar usuario');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        alert('Error al eliminar usuario');
      })
      .finally(() => {
        submitButton.disabled = false;
      });
  }

  // Asigna los manejadores de eventos a los formularios
  const createForm = document.getElementById('createUserForm');
  if (createForm) {
    createForm.addEventListener('submit', handleCreateUserSubmit);
  }

  const searchForm = document.getElementById('searchUserForm');
  if (searchForm) {
    searchForm.addEventListener('submit', handleSearchUserSubmit);
  }

  const updateForm = document.getElementById('updateUserForm');
  if (updateForm) {
    updateForm.addEventListener('submit', handleUpdateUserSubmit);
  }

  const deleteForm = document.getElementById('deleteUserForm');
  if (deleteForm) {
    deleteForm.addEventListener('submit', handleDeleteUserSubmit);
  }
});
