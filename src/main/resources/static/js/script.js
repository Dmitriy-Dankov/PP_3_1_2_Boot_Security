function sendInput(from, to, num) {
    let del = document.querySelectorAll(".delete_form_btn");
    let edit = document.querySelectorAll(".edit_form_btn");
    let formList;
    let cellList = from.parentNode.parentNode.children;
    
    if (num == 0) {
        del.forEach(el => {
            el.style.display = 'none';
        });
        edit.forEach(el => {
            el.style.display = 'block';
        });
        formList = document.querySelector("#id_edit");
    } else {
        edit.forEach(el => {
            el.style.display = 'none';
        });
        del.forEach(el => {
            el.style.display = 'block';
        });
        formList = document.querySelector("#id_del");
    }

    formList.elements[1].value = cellList[0].innerText;
    formList.elements[2].value = cellList[4].innerText;
    formList.elements[4].value = cellList[1].innerText;
    formList.elements[5].value = cellList[2].innerText;
    formList.elements[6].value = cellList[3].innerText;
    
    for (let i = 0; i <= formList.elements[7].size; ++i ) {
        if (formList.elements[7].options[i].value == cellList[5].innerText) {
            formList.elements[7].selectedIndex = i;
            break;
        }
        console.log("index " + i)
    }
}