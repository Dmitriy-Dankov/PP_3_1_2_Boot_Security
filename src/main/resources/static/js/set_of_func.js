
function tRowToForm(from, num) {
    const $del = document.querySelectorAll(".delete_form_btn");
    const $edit = document.querySelectorAll(".edit_form_btn");
    let formList;
    const cellList = from.parentNode.parentNode.children;
    
    if (num == 0) {
        $del.forEach(el => {
            el.style.display = 'none';
        });
        $edit.forEach(el => {
            el.style.display = 'block';
        });
        formList = document.forms[2]; //edit
    } else {
        $edit.forEach(el => {
            el.style.display = 'none';
        });
        $del.forEach(el => {
            el.style.display = 'block';
        });
        formList = document.forms[3]; //delete
    }

    formList.elements[0].value = cellList[0].innerText;
    formList.elements[1].value = cellList[1].innerText;
    formList.elements[2].value = cellList[2].innerText;
    formList.elements[3].value = cellList[3].innerText;
    formList.elements[4].value = cellList[4].innerText;
    
    for (let i = 0; i < formList.elements[6].size; ++i ) {
        if (formList.elements[6].options[i].value == cellList[5].innerText) {
            formList.elements[6].options[i].selected = 'true';
        } else if ('ADMIN USER' == cellList[5].innerText) {
            formList.elements[6].options[0].selected = 'true';
        }
    }
}

export {
    tRowToForm
};