
const $textTab = document.getElementById('txt_tab_user')

const $userTab = document.getElementById('user-tab')
$userTab.addEventListener("click", function() { $textTab.textContent = "All users" })

const $newUserTab = document.getElementById('new_user-tab')
$newUserTab.addEventListener("click", function() { $textTab.textContent = "Add new user" })

export {
};