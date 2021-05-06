<?php 
	
	require_once "order.php";
	$order = new Order();

	if(isset($_POST['user_id'])) {
		$order->user_id = $_POST['user_id'];
		$order->status = "confirm";

		$result = $order->getOrderByStatus();    

	    while($row = $result->fetch_assoc()) {
	        $output[] = $row;
	    }

		if ($result->num_rows) 
        {
            $response["success"] = 1;
            $response["message"] = "Successfully registered!.";     
            $response["items"] = $output;       
        } 
        else 
        {
            $response["success"] = 0;
            $response["message"] = "Empty";
        }

	}

    echo json_encode($response);

 ?>