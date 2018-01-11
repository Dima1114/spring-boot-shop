var baseUrl = $('#baseUrl').attr('content');

function addToCart(itemId) {

    var sizeId;
    var sizeIdRadio = $('input[name=sizeOptions]:checked').val();
    var sizeIdSelect = $('#sizeSelector').val();

    if (sizeIdRadio === undefined && sizeIdSelect !== undefined) {
        sizeId = sizeIdSelect;
    } else if (sizeIdSelect === undefined && sizeIdRadio !== undefined) {
        sizeId = sizeIdRadio;
    } else if ($($('input[name=sizeOptions]').prop('labels')).text().trim() === 'UniSize') {
        sizeId = $('input[name=sizeOptions]').val();
    } else {
        return false;
    }

    $.ajax({
        url: baseUrl + 'cart/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            itemId: itemId,
            sizeId: sizeId
        })
    })
        .done(function () {
            $('#itemAdded').fadeToggle('slow').delay(1000).fadeToggle('slow');
        });
}

function removeFromCart(availabilityId) {

    $.ajax({
        url: baseUrl + 'cart/remove/' + availabilityId,
        type: 'DELETE',
        contentType: 'application/json'
    }).done(function () {
        location.reload();
    });
}


