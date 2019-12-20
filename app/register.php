<?php
    $con = mysqli_connect("localhost","id12007017_admin","123queso45","id12007017_alajuela_ciudad_tecnilogica_db");

    $name    = $_POST["nombre"];
    $last1    = $_POST["apellido1"];
    $last2   = $_POST["apellido2"];
    $date    = $_POST["fecha__nacimiento"];
    $email    = $_POST["correo"];
    $pass   = $_POST["contrasena"];
    $checkbox = $_POST["notificaciones"];
    $statement = mysqli_prepare($con,"INSERT INTO usuario (nombre,apellido1,apellido2,fecha_nacimiento,correo,contrasena,notificaciones) VALUES (?,?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssis", $name, $last1, $last2, $date, $email, $pass, $checkbox);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>