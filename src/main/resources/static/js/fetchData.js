$(document).ready( function () {
    $('tbody').attr('title', 'Double-click to select.');
    $('tbody').dblclick(selectRow);
} );

let selectRow = function(ev){
    $('#selected').val(ev.target.parentNode.getElementsByTagName('td')[0].textContent);
};

let getData = function () {
    $('#data').html('');
    $('tbody').html('');
    fetch('/api/' + this.parentNode.id + '/' + $("input[name*='Code']")[0].value + "-" + $("input[name*='Name']")[0].value)
        .then(response => response.json())
        .then(partners => {
            partners.forEach(item => {
                let tr = document.createElement('tr');
                let partner = document.createElement('td');
                let name = document.createElement('td');
                let address = document.createElement('td');
                partner.textContent = item.partner;
                tr.append(partner);
                name.textContent = item.name;
                tr.append(name);
                address.textContent = item.address;
                tr.append(address);
                $('tbody').append(tr);
            });
        });
};

$("input[name*='Code']").on('input', getData);
$("input[name*='Name']").on('input', getData);