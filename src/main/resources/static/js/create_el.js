import * as func from './set_of_func.js';

let newUserObj;

function createUser(regForm) {
    let arr = () => {
        if (regForm.elements[6][0].selected == true) {
            if (regForm.elements[6][1].selected == true) {
                return [ 
                    { role: regForm.elements[6][0].value },
                    { role: regForm.elements[6][1].value } ]
            } else {
                return [ {role: regForm.elements[6][0].value } ]
            }
        } else {
            return [ { role: regForm.elements[6][1].value } ]
        }
    }

    let newUserObj = {
        id: regForm.elements[0].value,
        name: regForm.elements[1].value,
        surname: regForm.elements[2].value,
        age: regForm.elements[3].value,
        email: regForm.elements[4].value,
        password: regForm.elements[5].value,
        roles: arr()
    }
    return newUserObj
}

const $modalTitle = document.querySelector('.modal-title')

function createTRow(obj) {
    const $tr = document.createElement("tr")
    const $tdId = document.createElement("td")
    const $tdName = document.createElement("td")
    const $tdSurname = document.createElement("td")
    const $tdAge = document.createElement("td")
    const $tdEmail = document.createElement("td")
    const $tdRoles = document.createElement("td")
    const $tdEdit = document.createElement("td")
    const $tdDelete = document.createElement("td")

    const $btnEdit = document.createElement("button")
    $btnEdit.classList.add("t_btn", "btnEdit")
    $btnEdit.textContent = "Edit"
    $btnEdit.setAttribute("data-bs-toggle", "modal")
    $btnEdit.setAttribute("data-bs-target", "#modal_user")
    $btnEdit.addEventListener("click", function() { 
        $modalTitle.textContent = "Edit user"
        func.tRowToForm(this, 0)
    })
    
    const $btnDelete = document.createElement("button")
    $btnDelete.classList.add("t_btn", "btnDelete")
    $btnDelete.textContent = "Delete"
    $btnDelete.addEventListener("click", function() {
        $modalTitle.textContent = "Delete user"
        func.tRowToForm(this, 1)
    })
    $btnDelete.setAttribute("data-bs-toggle", "modal")
    $btnDelete.setAttribute("data-bs-target", "#modal_user")

    $tdId.textContent = obj.id
    $tdName.textContent = obj.name
    $tdSurname.textContent = obj.surname
    $tdAge.textContent = obj.age
    $tdEmail.textContent = obj.email
    $tdRoles.textContent = obj.roles.map(x => x.role).join(" ")
    $tdEdit.append($btnEdit)
    $tdDelete.append($btnDelete)

    $tr.append($tdId, $tdName, $tdSurname, $tdAge, $tdEmail, $tdRoles, $tdEdit, $tdDelete)
    return $tr
}

export {
    createUser,
    createTRow
};