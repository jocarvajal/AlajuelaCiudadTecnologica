<?php
    $con = mysqli_connect("localhost","id12007017_admin","123queso45","id12007017_alajuela_ciudad_tecnilogica_db");

    $name    = $_POST["nombre"];
    $last1    = $_POST["apellido1"];
    $last2   = $_POST["apellido2"];
    $date    = $_POST["fecha_nacimiento"];
    $email    = $_POST["correo"];
    $pass   = $_POST["contrasena"];
    $checkbox = $_POST["notificaciones"];
    $insertdate = date("Y-m-d", strtotime($date));
    
    $statement = mysqli_prepare($con,"UPDATE usuario SET nombre = ?,apellido1 = ?,apellido2 = ?,fecha_nacimiento = ?,contrasena = ?,notificaciones = ? WHERE correo = ? ");
    mysqli_stmt_bind_param($statement, "sssssis", $name, $last1, $last2, $insertdate, $pass, $checkbox, $email);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>