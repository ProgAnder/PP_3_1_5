const table = document.getElementById('tbody')
const navbarUser = document.getElementById('navbarUser')

fetch('/api/user')
    .then((response) => {
        return response.json();
    })
    .then((user) => {
        table.innerHTML = `<tr>
                  <td>${user.id}</td>
                  <td>${user.firstName}</td>
                  <td>${user.lastName}</td>
                  <td>${user.age}</td>
                  <td>${user.username}</td>
                  <td>${user.roles.map(role => role.name.replace('ROLE_', ''))}</td>
                  </tr>`

        navbarUser.innerHTML = `<strong><span class="bold">${user.username}</span></strong>
                    with roles: <span>${user.roles.map(role => role.name.replace('ROLE_', ''))}</span>`


        let roless = user.roles.map(role => role.name.replace('ROLE_', ''))

        if (roless.indexOf('ADMIN') === -1) {
            document.getElementById('adminMenu').style.display = 'none'
        }
    });

