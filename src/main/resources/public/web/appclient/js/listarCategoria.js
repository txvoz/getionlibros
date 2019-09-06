function detalle(target){
    var id = $(target).data("id");
    window.location.replace("?p=detalleCategoria&id="+id);
}

function eliminar(target) {
    var id = $(target).data("id");
    httpConnect("/categoria/" + id, null, "DELETE",
            function (r) {
                alert(r.message);
                cargarDatos();
            },function(r){
                alert(r.message);
            });
}

function cargarDatos() {
    httpConnect("/categoria", null, "GET", function (r) {
        var html = "";
        for (var i = 0; i < r.data.length; i++) {
            var entidad = r.data[i];
            html += "<tr>";
            html += "<td>" + entidad.catNombre + "</td>";
            html += "<td>";
            html += "<div data-id='" + entidad.catId + "' class='material-icons delete' style='color:red'>delete</div>";
            html += "<div data-id='" + entidad.catId + "' class='material-icons edit' style='color:green'>edit</div>";
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