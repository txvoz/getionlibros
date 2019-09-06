function detalle(target) {
    var id = $(target).data("id");
    window.location.replace("?p=detalleLibro&id=" + id);
}

function eliminar(target) {
    var id = $(target).data("id");
    httpConnect("/libro/" + id, null, "DELETE",
            function (r) {
                alert(r.message);
                cargarDatos();
            }, function (r) {
        alert(r.message);
    });
}

function cargarDatos() {
    httpConnect("/libro", null, "GET", function (r) {
        var html = "";
        for (var i = 0; i < r.data.length; i++) {
            var entidad = r.data[i];
            html += "<tr>";
            html += "<td>" + entidad.libNombre + "</td>";
            html += "<td>" + entidad.libNumeroPaginas + "</td>";
            html += "<td>" + entidad.libFechaPublicacion + "</td>";
            html += "<td>" + entidad.catId.catNombre + "</td>";
            html += "<td>";
            html += "<div data-id='" + entidad.libId + "' class='material-icons delete' style='color:red'>delete</div>";
            html += "<div data-id='" + entidad.libId + "' class='material-icons edit' style='color:green'>edit</div>";
            html += "</td>";
            html += "</tr>";
        }
        $("tbody").html(html);

        $(".delete").click(function () {
            if (confirm("Desea eliminar el recurso?")) {
                eliminar(this);
            }
        });
        $(".edit").click(function () {
            detalle(this);
        });

    });
}

$(function () {
    cargarDatos();
});