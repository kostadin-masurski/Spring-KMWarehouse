(() => {
    function switchPartnerInput() {
        if ($('input[type = radio]')[0].checked){
            $('input[name = partner]').hide();
            $('#partnerH3').hide();
            $('#partnerBtn').hide();
            $('#partners').hide();
        }else {
            $('input[name = partner]').show();
            $('#partnerH3').show();
            $('#partnerBtn').show();
            $('#partners').show();
            $('tbody').html('');
        }
    }

    $('#partners').hide();
    $('#warehouses').hide();
    $('#materials').hide();
    $('.row').on('click', switchPartnerInput);
})();