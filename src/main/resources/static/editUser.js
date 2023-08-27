const editUserForm = document.getElementById('editUserForm')
const editModalClose = document.getElementById('editModalClose')
const editedUserRoleBlock = document.getElementById('editedUserRoleBlock')
let editedUserRole = document.querySelector('#editedUserRoleBlock').selectedOptions


fetch('/api/role').then(response => response.json()).then(roless => {
        let html = ''
        for (let role of roless) {
            html += `
                <option value="${role.id}">
                ${role.name.replace('ROLE_', '')}
                </option>`
            editedUserRoleBlock.innerHTML = html
        }
    }
)

function myEdit(id){
    fetch('/api/admin/' + id).then(response => response.json()).then(editedUser => {
        editUserForm.id_edit.value = editedUser.id
        editUserForm.firstname_edit.value = editedUser.firstName
        editUserForm.lastname_edit.value = editedUser.lastName
        editUserForm.age_edit.value = editedUser.age
        editUserForm.email_edit.value = editedUser.username
    })
}

editUserForm.addEventListener('submit', editedUser => {
    editedUser.preventDefault()
    let roleszz = []
    for (let i = 0; i < editedUserRole.length; i++) {
        roleszz.push({
            id: editedUserRole[i].value
        })
    }

    method = {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            id: editUserForm.id_edit.value,
            firstName: editUserForm.firstname_edit.value,
            lastName: editUserForm.lastname_edit.value,
            age: editUserForm.age_edit.value,
            username: editUserForm.email_edit.value,
            password: editUserForm.password_edit.value,
            roles: roleszz
        })
    }

    fetch('/api/admin/edit', method).then(() => {
        editUserForm.reset()
        editModalClose.click()
        adminPage()
    })
})