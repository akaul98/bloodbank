<?php
require "init.php";
$name=$_POST["name"];
$city=$_POST["city"];
$email=$_POST["email"];
$bloodgroup=$_POST["bloodgroup"];
$phone=$_POST["phone"];
$password=$_POST["password"];




$sql= "INSERT INTO register_user(name,city,email,bloodgroup,phone,password) VALUES('$name','$city','$email','$bloodgroup','$phone','$password')";

$result=mysqli_query($con , $sql);
	if($result){
	echo "Success";
	}
	else{
	echo "error".mysqli_error($con);
	}
	
	mysqli_close($con);

?>
