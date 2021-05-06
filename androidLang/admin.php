<?php 

	require_once __DIR__ . "./dbconnect.php";

	class Admin extends Db {

		private $stmt;
		private $con;

		public $id;
		public $order_unique_id;
		public $contact_person;
		public $contact_number;
		public $location;
		public $note;

		public function __construct()
		{
			$this->con = $this->connect();
		}

		public function getUsersOrderInformation() {
			$this->stmt = $this->con->prepare('SELECT 
									users.name AS user_name, orders.name AS order_name, 
									orders.id AS order_id, orders.size, orders.qty, delivery.*
									FROM orders
									INNER JOIN users
									ON orders.user_id = users.id
									INNER JOIN delivery
									on orders.unique_id = delivery.order_unique_id
									WHERE orders.status = "pending"');
			$this->stmt->execute();

			return $this->stmt->get_result();
		}
	}

 ?>