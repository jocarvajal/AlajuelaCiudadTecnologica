<?php
    $con = mysqli_connect("localhost","id12007017_admin","123queso45","id12007017_alajuela_ciudad_tecnilogica_db");

    $email    = $_POST["correo"];
    $pass   = $_POST["contrasena"];
    $statement = mysqli_prepare($con,"SELECT * FROM usuario WHERE correo = ? AND contrasena = ?");
    mysqli_stmt_bind_param($statement, "ss", $email, $pass);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysql_stmt_bind>_result($statement, $name, $last1, $last2, $date, $e_mail, $password, $notifications);

    $response = array();
    $response["sucess"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["nombre"] = $name;
        $response["apellido1"] = $last1;
        $response["apellido2"] = $last2;
        $response["fecha_nacimiento"] = $date;
        $response["correo"] = $e_mail;
        $response["contrasena"] = $password;
        $response["notificacaiones"] = $notifications;

    }

    echo json_encode($response);
?>