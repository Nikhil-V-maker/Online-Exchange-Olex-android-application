<?php

include 'DatabaseConfig.php';
$usn=$_POST['uid'];
$com=$_POST['cid'];
//$com1=$_POST['iid'];
// Create connection
$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
 
 //if($_SERVER['REQUEST_METHOD'] == 'POST')
// {
 $DefaultId = 0;
 
 $ImageData = $_POST['image_path'];
 
 $ImageName = $_POST['image_name'];

 $GetOldIdSQL ="SELECT iid FROM items where Userid='$usn' AND cid='$com'";
 
 $Query = mysqli_query($conn,$GetOldIdSQL);
 
 while($row = mysqli_fetch_array($Query)){
 
 $DefaultId = $row['iid'];
 }
 
 $ImagePath = "img/$DefaultId.png";
 
 $ServerURL = "http://192.168.43.28/olx/$ImagePath";
 //insert into UploadImageToServer (image_path,image_name) values ('$ServerURL','$ImageName')
 $InsertSQL = "UPDATE items set img1='$ServerURL',image_name='$ImageName' where Userid='$usn' AND cid='$com' and iid='$DefaultId'";
 
 if(mysqli_query($conn, $InsertSQL)){

 file_put_contents($ImagePath,base64_decode($ImageData));

 echo "Your Image Has Been Uploaded.";
 }else{
 echo "Not Uploaded";
 }
 
 //mysqli_close($conn);
 

?>