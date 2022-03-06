$(document).ready(function () {
    $("tr #btnDelete").click(function () {
        var item = $(this).parent().find("#item").val();
        swal({
            title: "Esta seguro de eliminar el producto del carrito?",
            text: "Una vez que lo elimine, deberá ir a la página de compras y agregarlo nuevamente!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete) => {
                if (willDelete) {
                    Eliminar(item);
                    swal("El registro ha sido eliminado!", {
                        icon: "success",
                    }).then((willDelete)=>{
                        if(willDelete){
                            parent.location.href="Controlador?accion=Carrito";
                        }
                    });
                } else {
                    swal("El registro no fué eliminado!");
                }
        });
    });
    function Eliminar(item) {
        var url = "Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "item=" + item,
            success: function (data, textStatus, jqXHR) {
            }
        });
    }
    
    $("tr #Cantidad").click(function(){
        var idpro = $(this).parent().find("#idpro").val();
        var cantidad = $(this).parent().find("#Cantidad").val();
        var url = "Controlador?accion=ActualizarCantidad";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idpro="+idpro+"&Cantidad="+cantidad,
            success: function (data, textStatus, jqXHR){
                location.href="Controlador?accion=Carrito";
            }
        });
    });
});


