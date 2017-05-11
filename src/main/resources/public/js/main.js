$(document).ready(function () {
    $(".btn-description").click(function () {
        var prodId = $(this).closest('.row').find(".productid").val();
        var route = "/product/" + prodId;
        $.getJSON(route, function (product) {
            $("#modal-title").text(product.name);
            $("#modal-cat").text(product.categoryName);
            $("#modal-sup").text(product.supplierName);
            $("#modal-desc").text(product.description);
            var price = "PRICE: " + Number((product.price).toFixed(2)) + " " + product.currency;
            $("#price").text(price)
        });

        $('#myModal').modal('show');
    });

    var $searchBox = $("#search");
    var $searchButton = $("#search-btn");


    $searchBox.on('keydown', function () {
        setTimeout(function () {   //calls click event after a certain time;
            if ($searchBox.val().length >= 3) {
                $searchButton.prop('disabled', false);
            } else {
                $searchButton.prop('disabled', true);
            }
        }, 100);
    });

});
