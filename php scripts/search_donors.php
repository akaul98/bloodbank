<?php

    $host = "localhost";
    $user = "id15068832_register_data";
    $pass = "|&<6-_ePuM*UN@2q";
    $db = "id15068832_bloodbank";
    $con = mysqli_connect($host,$user,$pass,$db);
    
    
 
    $city = $_POST["city"];
    $bloodgroup = $_POST["bloodgroup"];
    $sql = "Select name, phone, city from register_user WHERE bloodgroup LIKE '$bloodgroup' AND city LIKE '%$city%'";
    $result = mysqli_query($con, $sql);
    $response = array();
    while($row = mysqli_fetch_assoc($result)){
        array_push($response, $row);
    }
    echo json_encode($response);
    
?>

