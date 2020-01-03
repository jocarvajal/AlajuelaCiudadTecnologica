<?php
    $con = mysqli_connect("localhost","id12007017_admin","123queso45","id12007017_alajuela_ciudad_tecnilogica_db");

    $correo    = $_POST["correo"];
    $contrasena   = $_POST["contrasena"];
    $statement = mysqli_prepare($con,"SELECT correo,contrasena FROM usuario WHERE correo = ? AND contrasena = ?");
    mysqli_stmt_bind_param($statement, "ss", $correo, $contrasena);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$correo, $contrasena);

    $response = array();
    $response["sucess"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
       

    }

    echo json_encode($response);
?>