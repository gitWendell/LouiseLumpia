<?php 
	
	require_once "order.php";
	$order = new Order();

	if(isset($_POST['id'])) {
		
		$order->id = $_POST['id'];
		$order->status = "confirm";

		if ($order->updateStatus()) 
        {
            $response["success"] = 1;
            $response["message"] = "Update Successfully!.";           
        } 
        else 
        {
            $response["success"] = 0;
            $response["message"] = "Whooops.";
        }
	}

	echo json_encode($response);

 ?>