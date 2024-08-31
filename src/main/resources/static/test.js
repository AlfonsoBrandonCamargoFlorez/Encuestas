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
        // Aquí puedes cerrar el modal o mostrar un mensaje
      })
      .catch(error => {
        console.error('Error:', error);
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
      .then(response => response.json())
      .then(data => {
        console.log('Usuario encontrado:', data);
        // Aquí puedes mostrar los datos del usuario o manejar la respuesta
      })
      .catch(error => {
        console.error('Error:', error);
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
        // Aquí puedes cerrar el modal o mostrar un mensaje
      })
      .catch(error => {
        console.error('Error:', error);
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
        } else {
          console.error('Error al eliminar usuario');
        }
      })
      .catch(error => {
        console.error('Error:', error);
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
