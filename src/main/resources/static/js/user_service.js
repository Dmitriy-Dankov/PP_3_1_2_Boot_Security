import * as create from './create_el.js';
import * as client from './client.js';

async function addUser(obj) {
    let newUser = create.createUser(obj)
    let result = await client.update(newUser, "api/add_user")    
    if (result == 'OK') {
        getUsers()
        document.getElementById('user-tab').click()                  
    } else {
        console.log("STATUS: " + result)
    }
}

async function editUser(obj) {
    let newUser = create.createUser(obj)
    let result = await client.update(newUser, "api/edit_user")
    if (result == 'OK') {
        getUsers()
        document.querySelector('.btn-close').click()  
    } else {
        console.log("STATUS: " + result)
    }
}

async function deleteUser(obj) {
    let result = await client.update(obj.elements[0].value, "api/delete_user")
    if (result == 'OK') {
        getUsers()
        document.querySelector('.btn-close').click()               
    } else {
        console.log("STATUS: " + result)
    }
}

async function getUsers() {
    let result = await client.getUsers()
    let rows = []

    for (const user of result) {
        rows.push(create.createTRow(user))
    }
    renderRowAll(rows)
}

const $userTable = document.getElementById('user_table')

function renderRow(row) {
    $userTable.append(row)
}

function renderRowAll(rows) {
    $userTable.innerHTML = ''
    for (let i = 0; i < rows.length; ++i) {
        $userTable.append(rows[i])        
    }
}

export {
    getUsers,
    addUser,
    editUser,
    deleteUser
};