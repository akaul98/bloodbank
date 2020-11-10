<?php


    $host = "localhost";
    $user = "id15068832_register_data";
    $pass = "|&<6-_ePuM*UN@2q";
    $db = "id15068832_bloodbank";
    $con = mysqli_connect($host,$user,$pass,$db);


$target_dir = "uploads/";
$target_file_name = $target_dir . basename($_FILES["file"]["name"]);
$response = array();

if (isset($_FILES["file"]))
{
    if (move_uploaded_file($_FILES["file"]["tmp_name"], $target_file_name))
    {   $url = "https://bloodbnak.000webhostapp.com/".$target_file_name;
        $m = $_GET["message"];
        $phone = $_GET["phone"];
        $sql = "INSERT INTO `posts` (`id`, `message`, `url`, `phone`) VALUES (NULL, '$m', '$url', '$phone');";
        mysqli_query($con, $sql);
        $success = true;
        $message = "Uploaded!!!";
    }
    else
    {
        $success = false;
        $message = "NOT Uploaded!!! _ Error While Uploading";
    }
}
else
{
    $success = false;
    $message = "missing field";
}
$response["success"] = $success;
$response["message"] = $message;
echo json_encode($response);
?>
