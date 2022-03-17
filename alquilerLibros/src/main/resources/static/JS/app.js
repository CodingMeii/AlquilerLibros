//Consultar los libros almacenados en la base de datos con el usuario Admin
//Consultar libros tabla admin
function consultarLibros(){
    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/allbooks",
        type: "GET",
        dataType:"json",
        success: function (response){
            $("#contenidoTablaLibro").empty();
            response.forEach(element => {
                var row = $("<tr>");
                row.append($("<td>").text(element.id));
                row.append($("<td>").text(element.nombre));
                row.append($("<td>").text(element.autor));
                row.append($("<td>").text(element.nejemplares));
                row.append($("<td>").text(element.ndisponible));
                row.append($("<td>").text(element.nreservada));
                row.append($("<td>").text(element.valquiler));
                row.append($("<td>").append('<button type="button" onclick="seleccionarLibro('+element.id+')">Seleccionar</button>'));
                row.append($("<td>").append('<button type="button" onclick="eliminarLibro('+element.id+')">Eliminar</button>'));
                $("#contenidoTablaLibro").append(row);
            })
        }
    });
}

//Consultar libros tabla cliente
function consultarLibrosCliente(){
    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/avaliablebook",
        type: "GET",
        dataType:"json",
        success: function (response){
            $("#contenidoTablaLibroC").empty();
            response.forEach(element => {
                var row = $("<tr>");
                row.append($("<td>").text(element.id));
                row.append($("<td>").text(element.nombre));
                row.append($("<td>").text(element.autor));
                row.append($("<td>").text(element.ndisponible));
                row.append($("<td>").text(element.valquiler));
                row.append($("<td>").append('<button type="button" onclick="mostrarModal()" id="open">Reservar</button>'));
                $("#contenidoTablaLibroC").append(row);
            })
        }
    });
}

function mostrarModal() {
    modal_container.classList.add("show");
}

function reservaLibro(){
    var idLibro = $("#idLibro").val();
    var cant = $("#cantlibro").val();

    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/reservebook?id="+idLibro+"&cant="+cant,
        type: "GET",
        dataType:"json",
        success: function (response){
            //alert(response);
            if (response == 0){
                alert("No se ha podido reservar el libro.");
            }else{
                modal_container.classList.remove("show");
                alert("El total del alquiler del libro es: "+response);
            }
            consultarLibrosCliente();
        },
        error: function(xhr, status){
            alert("Ocurrio un error en el consumo.")
        }
    });
}

function salirModal(){
    modal_container.classList.remove("show");
}

function crearLibro() {
    var id = $("#id").val();
    var name = $("#nombre").val();
    var autor = $("#autor").val();
    var nEjemplares = $("#nEjemplares").val();
    var nDisponible = $("#nDisponible").val();
    var nReservados = $("#nReservados").val();
    var vAlquiler = $("#vAlquiler").val();

    var data = {
        id:id,
        nombre:name,
        autor:autor,
        nejemplares:nEjemplares,
        ndisponible:nDisponible,
        nreservada:nReservados,
        valquiler:vAlquiler
    };

    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/savebook",
        type: "POST",
        data: JSON.stringify(data),
        headers:{
            "content-Type": "application/json"
        },
        statusCode: {
            200: function (){
                limpiar();
            }
        }
    });
}

function seleccionarLibro(idLibro){
    //alert(idLibro);
    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/listbook?id="+idLibro,
        type: "GET",
        dataType:"json",
        success: function (response){
            response.forEach(element => {
                $("#id").val(element.id);
                $("#id").attr("readOnly", true);
                $("#nombre").val(element.nombre);
                $("#autor").val(element.autor);
                $("#nEjemplares").val(element.nejemplares);
                $("#nDisponible").val(element.ndisponible);
                $("#nReservados").val(element.nreservada);
                $("#vAlquiler").val(element.valquiler);
                //alert(Object.values(element));
            })
        },
        error: function(xhr, status){
            alert("Ocurrio un error en el consumo.")
        }
    });
}

function actualizarLibro(){
    var id = $("#id").val();
    $("#id").attr("readOnly", true);
    var name = $("#nombre").val();
    var autor = $("#autor").val();
    var nEjemplares = $("#nEjemplares").val();
    var nDisponible = $("#nDisponible").val();
    var nReserva = $("#nReservados").val();
    var vAlquiler = $("#vAlquiler").val();

    var data = {
        id:id,
        nombre:name,
        autor:autor,
        nejemplares:nEjemplares,
        ndisponible:nDisponible,
        nreservada:nReserva,
        valquiler:vAlquiler
    };

    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/updatebook",
        type: "PUT",
        data: JSON.stringify(data),
        headers:{
            "content-Type": "application/json"
        },
        statusCode: {
            200: function (){
                limpiar();
            },
            505: function () {
                alert("Ocurrio un error en el consumo");
            }
        }
    });
    $("#id").attr("readOnly", false);
}

function eliminarLibro(idLibro){
    let myData={
        id:idLibro
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/deletebook?id="+idLibro,
        type:"DELETE",
        data:dataToSend,
        datatype:"json",
        contentType:"application/json",
        success:function(respuesta){
            consultarLibros();
        }
    });
}

function limpiar(){
    consultarLibros();
    $("#id").val("");
    $("#nombre").val("");
    $("#autor").val("");
    $("#nEjemplares").val("");
    $("#nDisponible").val("");
    $("#nReservados").val("");
    $("#vAlquiler").val("");
}

function cargarTabla(){
    consultarLibros();
}

//Registro de usuarios
function crearUsuario(){
    var id = $("#id").val();
    var nombre = $("#nombre").val();
    var apellido = $("#apellido").val();
    var email = $("#email").val();
    var psw = $("#psw").val();

    var data = {
        id:id,
        nombre:nombre,
        apellido:apellido,
        email:email,
        clave:psw,
        tipoUsuario:false,
        estado:false
    };

    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/saveclient",
        type: "POST",
        data: JSON.stringify(data),
        headers:{
            "content-Type": "application/json"
        },
        statusCode: {
            200: function (){
                alert("El usuario ha sido registrado.")
                limpiarCliente();
            }
        }
    });
}

function limpiarCliente(){
    $("#id").val("");
    $("#nombre").val("");
    $("#apellido").val("");
    $("#email").val("");
    $("#psw").val("");
    $("#psw2").val("");
}

//LogIn
function login(){
    var user = $("#user").val();
    var psw = $("#psw").val();

    $.ajax({
        url:"http://localhost:8080/api/mibiblioteca/login?user="+user+"&psw="+psw,
        type: "GET",
        dataType:"json",
        success: function (response){
            if(response == true){
                //alert("Es verdadero");
                var storage = window.localStorage;

                if(user == 1){
                    window.location="../admin.html";

                    if (storage != null){
                        storage.setItem(user, "Admin");
                    }
                }else{
                    window.location="../cliente.html";

                    if (storage != null){
                        storage.setItem(user, "Cliente");
                    }
                }
            }else{
                alert("El usuario o la contraseña no son correctas.");
            }
        },
        error: function(xhr, status){
            alert("Ocurrio un error en el consumo.")
        }
    });
}

function logout(){
    var storage = window.localStorage;
    storage.clear();
    window.location="../index.html";
}

//Cargar página y la tabla
$(document).ready(function(){
    //consultarLibros();
})