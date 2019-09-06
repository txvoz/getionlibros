$(function(){
    $("#frmCrear").submit(function(){
        var categoaria = new Object();
        categoaria.catNombre = $("#nombre").val();
        //libro.cantidadPaginas = $("#cantidadPaginas").val();
        
        var jcategoria = JSON.stringify(categoaria);
        
        httpConnect("/categoria",jcategoria,"POST",function(r){
            alert(r.message+"-"+r.data.nombre);
            $("button[type=reset]").click();
        });
        
        return false;
    });
});