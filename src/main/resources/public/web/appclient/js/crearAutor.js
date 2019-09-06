$(function () {
    //Asiganacion del evento submit para el formulario de crear libro
    $("#frmCrear").submit(function () {
        var entidad = new Object();
        /*****/
        entidad.autNombre = $("#autNombre").val();
        entidad.autGenero = $("input[name=autGenero]:checked").val();
        entidad.autFechaNacimiento = $("#autFechaNacimiento").val();
        /*****/
        var jentidad = JSON.stringify(entidad);
        /*****/
        httpConnect("/autor", jentidad, "POST", function (r) {
            alert(r.message + "-" + r.data.autNombre);
            $("button[type=reset]").click();
        }, function (r) {
            alert(r.message);
        });
        return false;
    });
    //
});