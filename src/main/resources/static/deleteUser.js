const deleteUserForm = document.getElementById('deleteUserForm')
let deleteRoleHtml = document.getElementById('deleteUserRoleBlock')
let deleteModalClose = document.getElementById('deleteModalClose')


fetch('/api/role').then(response => response.json()).then(roleList => {
        let html = ''
        for (let role of roleList) {
            html = `
        <option value="${role.id}">
                ${role.name.replace('ROLE_', '')}
                </option>`
            deleteRoleHtml.innerHTML = html
        }
    }
)

let idForSubmit = ''

function myDelete(id) {
    idForSubmit = id

    fetch('api/admin/' + id).then(response => response.json()).then(user => {
            deleteUserForm.id_delete.value = user.id
            deleteUserForm.firstname_delete.value = user.firstName
            deleteUserForm.lastname_delete.value = user.lastName
            deleteUserForm.age_delete.value = user.age
            deleteUserForm.email_delete.value = user.username
        }
    )
}

deleteUserForm.addEventListener('submit', deletedUser => {
    deletedUser.preventDefault()

    method = {
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({})

    }

    fetch('/api/admin/' + idForSubmit, method).then(() => {
        deleteUserForm.reset()
        deleteModalClose.click()
        adminPage()
    })

})