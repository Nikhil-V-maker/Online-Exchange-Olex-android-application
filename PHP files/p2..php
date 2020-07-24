<?php


include 'DatabaseConfig.php';

// Create connection
$conn = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);

if (mysqli_connect_error($conn)) {
 
 die("Connection failed: " . mysqli_connect_error());
}
session_start();
echo $_SESSION['a'];
?>
