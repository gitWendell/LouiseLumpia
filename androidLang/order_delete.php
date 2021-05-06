<?php 
	
	require_once "order.php";
	$order = new Order();

	if(isset($_POST['id'])) {
		
		$order->id = $_POST['id'];

		if ($order->delete()) 
        {
            $response["success"] = 1;
            $response["message"] = "Successfully Deleted!.";           
        } 
        else 
        {
            $response["success"] = 0;
            $response["message"] = "Unable to save faculty member.";
        }
	}

	echo json_encode($response);

 ?>