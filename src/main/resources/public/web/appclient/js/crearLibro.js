function cargarCategorias() {
    httpConnect("/categoria", null, "GET", function (r) {
        var html = "<select id='categoria' name='categoria' class='form-control' required>";
        html += "<option value=''>[SELECCIONAR OPCION]</option>";
        for (var i = 0; i < r.data.length; i++) {
            var o = r.data[i];
            html += "<option value='" + o.catId + "'>" + o.catNombre + "</option>";
        }
        html += "</select>";
        $("#contentCategoria").html(html);
    });
}
function cargarAutores() {
    httpConnect("/autor", null, "GET", function (r) {
        var html = "<select name='states' id='example' class='form-control'  multiple='multiple' style='display: none;'>";
        html += "<option selected value='AL'>Alabama</option>";
        for (var i = 0; i < r.data.length; i++) {
            var valor = r.data[i];
            html += "<option value='" + valor.autId + "'>" + valor.autNombre + "</option>";
        }
        html += "</select>";
        $("#contentAutores").html(html);
    });
}
$(function () {
    cargarCategorias();

    cargarAutores();
    
    $('#example').dashboardCodeBsMultiSelect();

    $("#frmCrear").submit(function () {
        var libro = new Object();
        libro.libNombre = $("#nombre").val();
        libro.libNumeroPaginas = $("#cantidadPaginas").val();
        libro.catId = {
            catId: $("#categoria").val()
        };
        libro.libFechaPublicacion = $('#fechaP').val();
        //************
        var jlibro = JSON.stringify(libro);
        //************
        httpConnect("/libro", jlibro, "POST", function (r) {
            alert(r.message + "-" + r.data.nombre);
            $("button[type=reset]").click();
        });
        //************
        return false;
    });
});

//http://develoteca.com/plugin-jquery-crear-multiselect-bootstrap/