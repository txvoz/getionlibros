function cargarCategorias() {
    var html = "<option value=''>[SELECCIONAR]</option>";
    httpConnect("/categoria", {}, "GET", function (r) {
        for (var i = 0; i < r.data.length; i++) {
            var categoria = r.data[i];
            html += "<option value='" + categoria.catId + "'>" + categoria.catNombre + "</option>";
        }
        $("#catId").html(html);
    });
}

$(function () {
    //Cargar dependencias de la vista
    cargarCategorias();
    //Asiganacion del evento submit para el formulario de crear libro
    $("#frmCrear").submit(function () {

        var entidad = new Object();

        $(".form-control").each(function () {
            var attr = $(this).attr("id");
            entidad[attr] = $(this).val();
        });

        entidad.catId = {
            catId: $("#catId").val()
        };

        var jentidad = JSON.stringify(entidad);

        httpConnect("/libro", jentidad, "POST", function (r) {
            alert(r.message + "-" + r.data.libNombre);
            $("button[type=reset]").click();
        }, function (r) {
            alert(r.message);
        });

        return false;
    });
});