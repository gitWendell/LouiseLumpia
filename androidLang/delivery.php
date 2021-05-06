<?php 

	require_once __DIR__ . "./dbconnect.php";

	class Delivery extends Db {

		private $stmt;
		private $con;
		private $table = 'delivery';

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

		public function store() {
			$this->stmt = $this->con->prepare('INSERT INTO '. $this->table .' 
					(order_unique_id, contact_person, contact_number, location, note) 
					VALUES (?, ?, ?, ?, ?)');
			$this->stmt->bind_param('issss', $this->order_unique_id, $this->contact_person, $this->contact_number, $this->location, $this->note);
			return $this->stmt->execute();
		}

	}

 ?>