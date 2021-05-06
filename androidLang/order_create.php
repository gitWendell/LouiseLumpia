<?php 
	
	require_once "order.php";
	$order = new Order();

	if(isset($_POST['user_id']) && isset($_POST['name']) 
	   && isset($_POST['size']) && isset($_POST['qty'])) {
		
		$order->user_id = $_POST['user_id'];
		$order->name = $_POST['name'];
		$order->size = $_POST['size'];
		$order->qty = $_POST['qty'];

		if ($order->store()) 
        {
            $response["success"] = 1;
            $response["message"] = "Successfully Order!.";           
        } 
        else 
        {
            $response["success"] = 0;
            $response["message"] = "Unable to save faculty member.";
        }
	}

	echo json_encode($response);

 ?>