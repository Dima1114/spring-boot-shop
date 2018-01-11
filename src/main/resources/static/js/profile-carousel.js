var itemsInRow = $('#itemsInRow').text();

//everything resolves in template by adding attribute data-ride
$(document).ready(function () {

    if ($('.carousel-item').length > itemsInRow) {

        //I don`t know why, but it does not work
        // $('.carousel').carousel({
        //     interval: 1000
        // });


        $('.carousel .carousel-item').each(function () {
            var itemToClone = $(this);
            for (var i = 0; i < itemsInRow; i++) {
                itemToClone = itemToClone.next();

                // wrap around if at end of item collection
                if (!itemToClone.length) {
                    itemToClone = $(this).siblings(':first');
                }

                // grab item, clone, add marker class, add to collection
                itemToClone.children(':first-child').clone()
                    .appendTo($(this));
            }
        });

    } else {

        //if items less than 4 then stop sliding
        $('.carousel').carousel('pause');//this row does not work too

        var itemSize = $('.carousel .carousel-item').length;
        $('.carousel .carousel-item').each(function () {

            var itemToClone = $(this);
            for (var i = 0; i < itemSize; i++) {
                itemToClone = itemToClone.next();

                // grab item, clone, add marker class, add to collection
                itemToClone.children(':first-child').clone()
                // .addClass("cloneditem-"+(i))
                    .appendTo($(this));
            }
        });
    }

});

function dubbleClick() {
        alert('dbl');

}


