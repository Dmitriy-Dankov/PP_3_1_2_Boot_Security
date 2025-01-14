import * as service from './user_service.js';
import * as listen from './listeners.js';

service.getUsers()

const $regForm = document.forms[1]
const $editForm = document.forms[2]
const $deleteForm = document.forms[3]

function submitListeners () {
    $regForm.addEventListener("submit", function (event) {
        event.preventDefault()
        service.addUser($regForm)
        $regForm.querySelector('#clear').click() 
    })
    $editForm.addEventListener("submit", function (event) {
        event.preventDefault()
        service.editUser($editForm)
    })
    $deleteForm.addEventListener("submit", function (event) {
        event.preventDefault()
        service.deleteUser($deleteForm)
    })
}

submitListeners();