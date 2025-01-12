
const URL = "http://localhost:8080/"

async function getUsers() {
    let response = await fetch(URL + "api/users", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
    return await response.json()
}

async function update(obj, path) {
    let token = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');

    let response = await fetch(URL + path, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json",
            'X-XSRF-TOKEN': token            
        },

        body: JSON.stringify(obj)
    })
    return await response.json()
}

export {
    update,
    getUsers
};