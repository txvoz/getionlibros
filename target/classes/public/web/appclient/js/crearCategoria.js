$(function(){
    $("#frmCrear").submit(function(){
        var entidad = new Object();
        entidad.catNombre = $("#catNombre").val();
        
        var jentidad = JSON.stringify(entidad);
        
        httpConnect("/categoria",jentidad,"POST",function(r){
            alert(r.message+"-"+r.data.catNombre);
            $("button[type=reset]").click();
        },function(r){
            alert(r.message);
        });
        
        return false;
    });
});