(() => {
    let formCompany = document.getElementById('form-company');
    let formEmployee = document.getElementById('form-employee');
    let formChangeBtn = document.getElementsByClassName('btn-primary')[0];
    formEmployee.style.display = 'none';
    function switchInputForm() {
        if (formEmployee.style.display === 'none'){
            formCompany.style.display = 'none';
            formEmployee.style.display = 'block';
            formChangeBtn.textContent = 'Company contact form';
        }else {
            formEmployee.style.display = 'none';
            formCompany.style.display = 'block';
            formChangeBtn.textContent = 'Employee contact form';
        }
    }

    formChangeBtn.addEventListener('click', switchInputForm);
})();