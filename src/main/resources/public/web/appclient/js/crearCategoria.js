$(function () {
    $("#frmCrear").submit(function () {
        var categoaria = new Object();
        categoaria.catNombre = $("#nombre").val();

        var jcategoria = JSON.stringify(categoaria);

        httpConnect("/categoria", jcategoria, "POST", function (r) {
            alert(r.message + "-" + r.data.catNombre);
            $("button[type=reset]").click();
        });

        return false;
    });
});