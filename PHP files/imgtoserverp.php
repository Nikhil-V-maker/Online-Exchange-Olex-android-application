<?php

include 'DatabaseConfig.php';
$usn=$_POST['uid'];
//$com=$_POST['cid'];
//$com1=$_POST['iid'];
// Create connection
$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
 
 //if($_SERVER['REQUEST_METHOD'] == 'POST')
// {
 $DefaultId = 0;
 
 $ImageData = $_POST['image_path'];
 
 $ImageName = $_POST['image_name'];

 
 $DefaultId = $usn;
 
 
 $ImagePath = "imgp/$DefaultId.png";
 
 $ServerURL = "http://192.168.43.28/olx/$ImagePath";
 //insert into UploadImageToServer (image_path,image_name) values ('$ServerURL','$ImageName')
 $InsertSQL = "UPDATE user set imgpro='$ServerURL' where Userid='$usn'";
 
 if(mysqli_query($conn, $InsertSQL)){

 file_put_contents($ImagePath,base64_decode($ImageData));

 echo "Your Image Has Been Uploaded.";
 }else{
 echo "Not Uploaded";
 }
 
 //mysqli_close($conn);
 

?>