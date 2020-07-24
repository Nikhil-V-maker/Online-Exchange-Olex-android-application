<?php
session_start();

include 'DatabaseConfig.php';

// Create connection
$conn = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);

if (mysqli_connect_error($conn)) {
 
 die("Connection failed: " . mysqli_connect_error());
}
echo $_SESSION['a'];
?>
