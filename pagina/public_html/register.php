<?php
    $con = mysqli_connect("localhost","id12007017_admin","123queso45","id12007017_alajuela_ciudad_tecnilogica_db");

    $nombre    = $_POST["nombre"];
    $apellido1    = $_POST["apellido1"];
    $apellido2   = $_POST["apellido2"];
    $fecha_nacimiento    = $_POST["fecha_nacimiento"];
    $correo    = $_POST["correo"];
    $contrasena   = $_POST["contrasena"];
    $notificaciones = $_POST["notificaciones"];
    $statement = mysqli_prepare($con,"INSERT INTO usuario (nombre,apellido1,apellido2,fecha_nacimiento,correo,contrasena,notificaciones) VALUES (?,?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssis", $nombre, $apellido1, $apellido2, $fecha_nacimiento, $correo, $contrasena, $notificaciones);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>