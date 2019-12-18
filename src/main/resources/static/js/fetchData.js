$(document).ready( function () {
    $('tbody').attr('title', 'Double-click to select.');
    $('tbody').dblclick(selectRow);
} );

const selectRow = function(ev){
    $('#selected').val(ev.target.parentNode.getElementsByTagName('td')[0].textContent);
};

const getData = function () {
    $('#data').html('');
    $('tbody').html('');
    fetch('/api/' + this.parentNode.id + '/' + $("input[name*='Code']")[0].value + "-" + $("input[name*='Name']")[0].value)
        .then(response => response.json())
        .then(items => {
            items.forEach(item => {
                let tr = document.createElement('tr');
                let partner = document.createElement('td');
                let name = document.createElement('td');
                let address = document.createElement('td');
                partner.textContent = Object.values(item)[0].toString();
                tr.append(partner);
                name.textContent = Object.values(item)[1].toString();
                tr.append(name);
                address.textContent = Object.values(item)[2].toString();
                tr.append(address);
                $('tbody').append(tr);
            });
        });
};

const showOnlySelected = function(ev){
    let selectedDiv = null;
    try {
        selectedDiv = $('#' + ev.target.textContent.toLowerCase());
    }catch (e) {
        return;
    }
    $('#partners').hide();
    $('#warehouses').hide();
    $('#materials').hide();
    $('tbody').html('');
    selectedDiv.show();
};

$("input[name*='Code']").on('input', getData);
$("input[name*='Name']").on('input', getData);
$('#selectButtons').on('click', showOnlySelected);