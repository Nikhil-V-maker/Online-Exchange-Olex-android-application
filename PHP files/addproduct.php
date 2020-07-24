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
 $id = $_POST['id'];
 $pn=$_POST['pn'];

 if($password=="Properties")
 	$tp=100;
 else
 	if($password=="Cars")
 	$tp=101;
 else if($password=="Furniture")
 	$tp=102;
 else if($password=="Jobs")
 	$tp=103;
 else if($password=="Electronics and Appliances")
 	$tp=104;
 else if($password=="Mobiles")
 	$tp=105;
 else if($password=="Bikes")
 	$tp=106;
 else if($password=="Books, Sports and Hobbies")
 	$tp=107;
 else if($password=="Fashion")
 	$tp=108;
 
 $Sql_Query = "INSERT INTO items(name,brand,description,price,year,cid,Userid,phone) values ('$fname','$lname','$phn','$email','$address','$tp','$id','$pn')";

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
