const deleteUserForm = document.getElementById("deleteUserForm")

let deleteRoleHtml = document.getElementById("deleteUserRoleBlock")

let deleteModalClose = document.getElementById("deleteModalClose")


fetch("/api/role").then(response => response.json()).then(rolelist => {
        let html = ''
        for (let role of rolelist) {
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

    let userr = ''

    fetch("api/admin/" + id).then(response => response.json()).then(user => {
            deleteUserForm.id_delete.value = user.id
            deleteUserForm.firstname_delete.value = user.firstName
            deleteUserForm.lastname_delete.value = user.lastName
            deleteUserForm.age_delete.value = user.age
            deleteUserForm.email_delete.value = user.username
            userr = user
        }
    )
}

let deletedUserRole = document.querySelector('#deleteUserRoleBlock').selectedOptions

deleteUserForm.addEventListener("submit", deletedUser => {
    deletedUser.preventDefault()
    let rolesz = []
    for (let i = 0; i < deletedUserRole.length; i++) {
        rolesz.push({
            id: deletedUserRole[i].value
        })
    }


    method = {
        method: 'DELETE',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            // id: deleteUserForm.id_delete.value,
            // firstName: deleteUserForm.firstname_delete.value,
            // lastName: deleteUserForm.lastname_delete.value,
            // age: deleteUserForm.age_delete.value,
            // username: deleteUserForm.email_delete.value,
            //
            // roles: rolesz
        })

    }


    fetch("http://localhost:8080/api/admin/" + idForSubmit, method).then(() => {
        deleteUserForm.reset()
        deleteModalClose.click()
        adminPage()
        console.log(method)
    })



})


