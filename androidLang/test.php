<?php 
	$canLong = 123;
	$gunLong = 123;
	$canLat = 10;
	$gunLat = 10;

	$theta = $canLong - $gunLong;
	$miles = (sin(deg2rad($canLat))) * sin(deg2rad($gunLat)) 
			  + (cos(deg2rad($canLat)) + cos(deg2rad($gunLat)) + cos(deg2rad($theta)));
	$miles = acos($miles);
	$miles = rad2deg($miles);	
	$mile = $miles * 60 * 1.1515;
	$km = $mile * 1.609344;

	echo $miles;
 ?>
 <?php
function calculateDistance($lat1, $long1, $lat2, $long2){

  $theta = $long1 - $long2;
  $miles = (sin(deg2rad($lat1))) * sin(deg2rad($lat2)) + (cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * cos(deg2rad($theta)));
  $miles = acos($miles);
  $miles = rad2deg($miles);

  $result['miles'] = $miles * 60 * 1.1515;
  $result['feet'] = $result['miles']*5280;
  $result['yards'] = $result['feet']/3;
  $result['kilometers'] = $result['miles']*1.609344;
  $result['meters'] = $result['kilometers']*1000;
  return $result;

}

echo '<pre>';
var_dump(calculateDistance(10.295002,123.940667,10.307,123.956));