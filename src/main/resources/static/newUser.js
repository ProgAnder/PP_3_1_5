const newUserForm = document.getElementById('newUserForm')
const newUserRoleBlock = document.getElementById('newUserRoleBlock')
const tableAdmin = document.getElementById('tableAdmin')
let newUserRole = document.querySelector('#newUserRoleBlock').selectedOptions


fetch('/api/role').then(response => response.json()).then(roless => {
        let html = ''
        for (let role of roless) {
            html += `
                <option value="${role.id}">
                ${role.name.replace('ROLE_', '')}
                </option>`
            newUserRoleBlock.innerHTML = html
        }
    }
)

let method = ''

newUserForm.addEventListener('submit', newUser => {
    newUser.preventDefault()
    let roles = []
    for (let i = 0; i < newUserRole.length; i++) {
        roles.push({
            id: newUserRole[i].value
        })
    }

     method = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            username: newUserForm.usernameNewUser.value,
            password: newUserForm.passwordNewUser.value,
            firstName: newUserForm.firstnameNewUser.value,
            lastName: lastnameNewUser.value,
            age: newUserForm.ageNewUser.value,
            roles: roles
        })
    }

    fetch('/api/admin', method).then(() => {
        newUserForm.reset()
        adminPage()
        tableAdmin.click()
    })
})



