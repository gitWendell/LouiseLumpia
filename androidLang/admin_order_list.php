<?php 
	
	require_once "admin.php";
	$order = new Admin();


	$result = $order->getUsersOrderInformation();    

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