<?php 
	
	require_once "delivery.php";
	require_once "order.php";

	$delivery = new Delivery();
	$order = new Order();

	if(isset($_POST['user_id'])
		&& isset($_POST['contact_person'])
		&& isset($_POST['contact_number']) 
		&& isset($_POST['location']) 
		&& isset($_POST['note'])) {

		$generatedId = $order->generateUniqueId();

		$order->unique_id = $generatedId;
		$order->user_id = $_POST['user_id'];
		$order->status = 'incomplete';

		if ($order->updateUponCheckout()) {
			$delivery->order_unique_id = $generatedId;
			$delivery->contact_person = $_POST['contact_person'];
			$delivery->contact_number = $_POST['contact_number'];
			$delivery->location = $_POST['location'];
			$delivery->note = $_POST['note'];

			if ($delivery->store()) 
	        {
	            $response["success"] = 1;
	            $response["message"] = "Successfully Place Order!.";
	        } 
	        else 
	        {
	            $response["success"] = 0;
	            $response["message"] = "Whoops.";
	        }
		} else {
			$response["success"] = 0;
            $response["message"] = "Whoops 2.";
		}
		
	}

	echo json_encode($response);

 ?>