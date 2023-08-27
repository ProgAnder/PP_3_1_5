const newUserForm = document.getElementById("newUserForm")



const newUserRoleBlock = document.getElementById("newUserRoleBlock")
fetch('/api/role').then(response => response.json()).then(roless => {
        console.log(roless)
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


const tableAdmin = document.getElementById('tableAdmin')

let newUserRole = document.querySelector('#newUserRoleBlock').selectedOptions




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
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
           /* id: null,*/
            username: newUserForm.usernameNewUser.value,
            password: newUserForm.passwordNewUser.value,
            firstName: newUserForm.firstnameNewUser.value,
            lastName: lastnameNewUser.value,
            age: newUserForm.ageNewUser.value,
            roles: roles
        })
    }

    console.log(method)
    console.log(newUserRole)

    fetch("http://localhost:8080/api/admin",method).then(() => {
        newUserForm.reset()
        adminPage()
        tableAdmin.click()
    })


})

console.log(method)

console.log(newUserRole)



