
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

    formList.elements[1].value = cellList[0].innerText;
    formList.elements[2].value = cellList[1].innerText;
    formList.elements[3].value = cellList[2].innerText;
    formList.elements[4].value = cellList[3].innerText;
    formList.elements[5].value = cellList[4].innerText;

    if (cellList[5].innerText == "USER") {
        formList.elements[7].options[1].selected = 'true';
    } else {
        formList.elements[7].options[0].selected = 'true';
    }
}