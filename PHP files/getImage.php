<?php
include 'DatabaseConfig.php';

		$conn =mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
		if (mysqli_connect_error($conn)) {
 
		 die("Connection failed: " . mysqli_connect_error());
		} 
 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$id = $_POST['ID'];
	
		
	$sql = "SELECT img1 from items where iid = '$id'";
		
		$r = mysqli_query($conn,$sql);
		
		//$result = mysqli_fetch_array($r);
		
	if (mysqli_num_rows($r)>0) {
 
 
 while($row = mysqli_fetch_assoc($r)) {
 
 //$tem = $row;
 
 echo $row['img1'];
}
		}
		}
		
	else{
		echo "Error";
	}
?>

