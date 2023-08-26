const navbarAdmin = document.getElementById('navbarAdmin')
const tbodyAdmin = document.getElementById('tbodyAdmin')

let urlCurrentUser = '/api/user'

fetch(urlCurrentUser).then(response => response.json())
    .then(user => {
        navbarAdmin.innerHTML = `
        <strong><span className="bold">${user.username}</span></strong>
        with roles:
        <span>${user.roles.map(role => role.name.replace('ROLE_', ''))}</span>`
    })

let urlUsersList = '/api/admin'

fetch(urlUsersList).then(response => response.json())
    .then(usersList => {
            let tr = ''
            for (let user of usersList) {

                console.log(user)

                tr += `<tr>
                  <td>${user.id}</td>
                  <td>${user.firstName}</td>
                  <td>${user.lastName}</td>
                  <td>${user.age}</td>
                  <td>${user.username}</td>
                  <td>${user.roles.map(role => role.name.replace('ROLE_', ''))}</td>
                  <td>Edit</td>
                  <td>Delete</td>
                  </tr>`
            }
            tbodyAdmin.innerHTML = tr
        }
    )