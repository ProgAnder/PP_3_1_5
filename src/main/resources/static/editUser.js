const editUserForm = document.getElementById("editUserForm")
const editModalClose = document.getElementById("editModalClose")



const editedUserRoleBlock = document.getElementById("editedUserRoleBlock")
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
    fetch("/api/admin/" + id).then(response => response.json()).then(editedUser => {
        editUserForm.id_edit.value = editedUser.id
        editUserForm.firstname_edit.value = editedUser.firstName
        editUserForm.lastname_edit.value = editedUser.lastName
        editUserForm.age_edit.value = editedUser.age
        editUserForm.email_edit.value = editedUser.username
        })
}

let editedUserRole = document.querySelector('#editedUserRoleBlock').selectedOptions


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
        headers: {"Content-Type": "application/json"},
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

    console.log(method)

    fetch("http://localhost:8080/api/admin/edit", method).then(() => {
        editUserForm.reset()
        editModalClose.click()
        adminPage()

    })

})













//
// <!--Начало модального окна изменения юзера-->
// <button type="button" class="btn btn-info" data-toggle="modal"
//         th:data-target="${'#myEdit'+ user.id}">Edit
// </button>
// <div th:id="${'myEdit' + user.id}" class="modal fade"
//      tabindex="-1" aria-labelledby="exampleModalLabel"
//      aria-hidden="true">
//     <div class="modal-dialog modal-md">
//         <div class="modal-content">
//             <div class="modal-header">
//
//                 <h5 class="modal-title">Edit user</h5>
//                 <button type="button" class="close" data-dismiss="modal"
//                         aria-label="Close">
//                     <span aria-hidden="true">&times;</span>
//                 </button>
//             </div>
//             <div class="modal-body">
//                 <form th:method="PATCH"
//                       th:action="@{/admin/edit}"
//                       th:object="${user}">
//
//                     <div class="row">
//                         <div class="col-md-2">
//                         </div>
//                         <div class="col-md-8">
//                             <div class="form-group">
//                                 <label class="row font-weight-bold justify-content-center"
//                                        for="id_edit">ID</label>
//                                 <input name="id"
//                                        th:type="text"
//                                        class="form-control"
//                                        th:value="${user.id()}"
//                                        id="id_edit" readonly/>
//                             </div>
//                             <div class="form-group">
//                                 <label class="row font-weight-bold justify-content-center"
//                                        for="firstname_edit">First
//                                     name</label>
//                                 <input name="firstName"
//                                        th:type="text"
//                                        class="form-control"
//                                        th:value="${user.firstName}"
//                                        id="firstname_edit"/>
//                             </div>
//                             <div class="form-group">
//                                 <label class="row font-weight-bold justify-content-center"
//                                        for="lastname_edit">Last
//                                     name</label>
//                                 <input name="lastName"
//                                        th:type="text"
//                                        class="form-control"
//                                        th:value="${user.lastName}"
//                                        id="lastname_edit"/>
//                             </div>
//                             <div class="form-group">
//                                 <label class="row font-weight-bold justify-content-center"
//                                        for="age_edit">Age</label>
//                                 <input name="age"
//                                        th:type="number"
//                                        class="form-control"
//                                        th:value="${user.age}"
//                                        id="age_edit"/>
//                             </div>
//                             <div class="form-group">
//                                 <label class="row font-weight-bold justify-content-center"
//                                        for="email_edit">Email</label>
//                                 <input name="username"
//                                        th:type="text"
//                                        class="form-control"
//                                        th:value="${user.username}"
//                                        id="email_edit"/>
//                             </div>
//                             <div class="form-group">
//                                 <label class="row font-weight-bold justify-content-center"
//                                        for="password_edit">Password</label>
//                                 <input name="password"
//                                        th:type="password"
//                                        class="form-control"
//                                        id="password_edit"/>
//                             </div>
//                             <label class="row font-weight-bold justify-content-center"
//                                    for="roles_edit">Role</label>
//                             <select name="rolesIds" multiple class="form-control"
//                                     size="2"
//                                     id="roles_edit">
//                                 <option
//                                     th:each="role : ${roleList}"
//                                     th:text="${role.getName()}"
//                                     th:value="${role.getId()}"
//                                     th:selected="${user.getRoles().contains(role)}"
//                                 ></option>
//                             </select>
//                         </div>
//                     </div>
//                     <div class="modal-footer">
//                         <button type="button" class="btn btn-secondary"
//                                 data-dismiss="modal">Close
//                         </button>
//                         <button type="submit" class="btn btn-primary">
//                             Edit
//                         </button>
//                     </div>
//                 </form>
//             </div>
//         </div>
//     </div>
// </div>
// <!--Конец модального окна изменения юзера-->
//
