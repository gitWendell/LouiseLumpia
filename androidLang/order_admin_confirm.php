<?php 
	
	require_once "order.php";
	$order = new Order();

	$order->status = "confirm";

	$result = $order->getOrderConfirm();    

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


    echo json_encode($response);

 ?>