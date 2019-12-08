(() => {
    let alert = document.getElementsByClassName('alert-danger')[0];
    if (alert) {
        setTimeout(() => {
            alert.style.display = 'none';
        }, 5000)
    }
})();