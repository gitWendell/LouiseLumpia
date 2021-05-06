<?php 
	
	require_once "user.php";
	$user = new User();

	if(isset($_POST['name']) && isset($_POST['username']) && isset($_POST['password'])) {
		
		$user->name = $_POST['name'];
		$user->username = $_POST['username'];
		$user->password = $_POST['password'];

		if ($user->store()) 
        {
            $response["success"] = 1;
            $response["message"] = "Successfully registered!.";           
        } 
        else 
        {
            $response["success"] = 0;
            $response["message"] = "Unable to save faculty member.";
        }
	}

	echo json_encode($response);

 ?>