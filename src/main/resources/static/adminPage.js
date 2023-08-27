const navbarAdmin = document.getElementById('navbarAdmin')
const tbodyAdmin = document.getElementById('tbodyAdmin')


fetch('/api/user').then(response => response.json())
    .then(user => {
        navbarAdmin.innerHTML = `
        <strong><span className="bold">${user.username}</span></strong>
        with roles:
        <span>${user.roles.map(role => role.name.replace('ROLE_', ''))}</span>`
    })

function adminPage() {
    fetch('/api/admin').then(response => response.json())
        .then(usersList => {
                let tr = ''
                for (let user of usersList) {
                    tr += `<tr>
                  <td>${user.id}</td>
                  <td>${user.firstName}</td>
                  <td>${user.lastName}</td>
                  <td>${user.age}</td>
                  <td>${user.username}</td>
                  <td>${user.roles.map(role => role.name.replace('ROLE_', ''))}</td>
                  <td>
                  <button type="button" class="btn btn-info" data-toggle="modal"
                  data-target="#myEdit" onclick="myEdit(${user.id})">Edit</button>
                  </td>
                  <td>
                  <button type="button" class="btn btn-danger" data-toggle="modal"
                  data-target="#myDelete" onclick="myDelete(${user.id})">Delete</button>
                  </td>
                  </tr>`
                }
                tbodyAdmin.innerHTML = tr
            }
        )
}

adminPage()