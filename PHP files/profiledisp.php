<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

$id= $_POST['ID'];

// Create connection
$conn = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);

if (mysqli_connect_error($conn)) {
 
 die("Connection failed: " . mysqli_connect_error());
}

mysqli_query($conn,"SET @uid = '$id'");

$result = mysqli_query($conn,"call fetchdata(@uid)");


if (mysqli_num_rows($result) >0) {
 
 
 while($row[] = mysqli_fetch_assoc($result)) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 }
 
} 
 else {
 echo "No Results Found.";
}
 echo $json;

//$conn->close();
}
?>