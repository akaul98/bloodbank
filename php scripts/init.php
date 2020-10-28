<?php
	$host="localhost";
	$user="id15068832_register_data";
	$pass="|&<6-_ePuM*UN@2q";
	$db="id15068832_bloodbank";
	
	$con=mysqli_connect($host,$user,$pass,$db);
	
	if($con){
		echo"database is connected";
		}
		
	else{
		echo"database is not connected".mymysqli_connect_error();
	}
	
?>
