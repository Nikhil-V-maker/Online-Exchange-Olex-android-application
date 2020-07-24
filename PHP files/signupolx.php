<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';
//include 'UserLogin.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $fname = $_POST['fname'];
 $lname = $_POST['lname'];
 $phn = $_POST['phn'];
 $email = $_POST['email'];
 $address = $_POST['address'];
 $password = $_POST['password'];

$Sql_Query = "INSERT INTO user(Fname,Lname,Userid,Email,Address,Password,Phone) values ('$fname','$lname','$phn','$email','$address','$phn','$password')";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Data entered Successfully';
}
else
{
 echo 'Something went wrong';
 }
 }
 //mysqli_close($con);
?>
