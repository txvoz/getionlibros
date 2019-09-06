$(function () {
    $("#frmCrear").submit(function () {
        var entidad = new Object();
        entidad.autNombre = $("#nombre").val();
        entidad.autFechaNacimiento = $("#fechaNacimiento").val();
        entidad.autGenero = $("#generom").is(":checked") ? "M" : "F";

        var jentidad = JSON.stringify(entidad);
        alert(entidad.autNombre+" "+entidad.autFechaNacimiento+" "+entidad.autGenero);
        httpConnect("/autor", jentidad, "POST", function (r) {
            alert(r.message + "-" + r.data.autNombre);
            $("button[type=reset]").click();
        });

        return false;
    });
});