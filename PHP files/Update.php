<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $id = $_POST['ID'];
 $fname = $_POST['fn'];
 $lname=$_POST['ln'];
 $phn = $_POST['phn'];
 $email = $_POST['em'];
 $address = $_POST['add'];
$Sql_Query = "UPDATE user SET Fname = '$fname',Lname='$lname', Phone = '$phn', Email='$email', Address='$address' WHERE Userid = '$id'";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Record Updated Successfully';
}
else
{
 echo 'Something went wrong';
 }
 }
 //mysqli_close($con);
?>