$(document).ready( function () {
    $('#myTable').DataTable();
} );

$(document).ready( function () {
    $('tbody').attr('title', 'Double-click for details');
    $('tbody').dblclick(details);
} );

details = function (ev) {
    let url = document.URL.toString();
    let detail = ev.target.parentNode.getElementsByTagName('td')[0].textContent;
    window.location.href = url.slice(0, url.lastIndexOf('/') + 1) + detail;
};