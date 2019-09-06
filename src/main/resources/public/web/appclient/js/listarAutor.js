function detalle(target){
    var id = $(target).data("id");
    window.location.replace("?p=detalleAutor&id="+id);
}

function eliminar(target) {
    var id = $(target).data("id");
    httpConnect("/autor/" + id, null, "DELETE",
            function (r) {
                alert(r.message);
                cargarDatos();
            });
}

function cargarDatos() {
    httpConnect("/autor", null, "GET", function (r) {
        var html = "";
        for (var i = 0; i < r.data.length; i++) {
            var autor = r.data[i];
            html += "<tr>";
            html += "<td>" + autor.autNombre + "</td>";
            html += "<td>" + autor.autGenero + "</td>";
            html += "<td>" + autor.autFechaNacimiento + "</td>";
            html += "<td>";
            html += "<div data-id='" + autor.autId + "' class='material-icons delete' style='color:red'>delete</div>";
            html += "<div data-id='" + autor.autId + "' class='material-icons edit' style='color:green'>edit</div>";
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