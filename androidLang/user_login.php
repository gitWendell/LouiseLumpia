<?php 
	
	require_once "user.php";
	$user = new User();

	if(isset($_POST['username']) && isset($_POST['password'])) {

		$user->username = $_POST['username'];
		$user->password = $_POST['password'];

		$result = $user->checkLogin();    

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
            $response["message"] = "Unable to save faculty member.";
        }
	}

	echo json_encode($response);

 ?>