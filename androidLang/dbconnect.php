<?php 

	class Db {

	public function connect() {
		return $con = new mysqli('localhost', 'root', '', 'android');
	}
}

 ?>