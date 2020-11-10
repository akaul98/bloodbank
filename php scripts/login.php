<?php

    $host = "localhost";
    $user = "id15068832_register_data";
    $pass = "|&<6-_ePuM*UN@2q";
    $db = "id15068832_bloodbank";
    $con = mysqli_connect($host,$user,$pass,$db);
    
    
$phone=$_POST["phone"];
$password=$_POST["password"];

$sql= "SELECT * FROM register_user WHERE phone='$phone' and password='$password'";


$result=mysqli_query($con , $sql);
	if(mysqli_num_rows($result)>0){
	$rows=mysqli_fetch_assoc($result);
	echo $rows['city'];
	}
	else{
	echo "Invalid Credentials";
	}
	
	mysqli_close($con);

?>

