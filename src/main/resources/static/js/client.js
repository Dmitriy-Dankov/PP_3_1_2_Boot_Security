
async function getUsers() {
    let response = await fetch("/api/users", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
    return await response.json()
}

async function update(obj, path) {
    let token = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');

    let response = await fetch("/" + path, {
        method: "POST",
        headers: {
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