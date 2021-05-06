<?php 

	require_once __DIR__ . "./dbconnect.php";

	class Order extends Db {

		private $stmt;
		private $con;
		private $table = 'orders';

		public $id;
		public $user_id;
		public $name;
		public $size;
		public $qty;
		public $status;
		public $unique_id;

		public function __construct()
		{
			$this->con = $this->connect();
		}

		public function store() {
			$this->stmt = $this->con->prepare('INSERT INTO '. $this->table .' (name, size, qty, user_id) VALUES (?, ?, ?, ?)');
			$this->stmt->bind_param('ssss', $this->name, $this->size, $this->qty, $this->user_id);
			return $this->stmt->execute();
		}

		public function getOrderByStatus() {
			$this->stmt = $this->con->prepare('SELECT * FROM ' . $this->table . ' 
				WHERE status = ? AND user_id = ?');
			$this->stmt->bind_param('si', $this->status, $this->user_id);			
			$this->stmt->execute();

			return $this->stmt->get_result();
		}

		public function delete () {
			$this->stmt = $this->con->prepare('DELETE FROM '. $this->table .' WHERE id = ?');
			$this->stmt->bind_param('i', $this->id);
			return $this->stmt->execute();
		}

		public function updateStatus() {
			$this->stmt = $this->con->prepare('UPDATE ' .$this->table. ' 
				SET status = ?
				WHERE id = ?');
			$this->stmt->bind_param('si', $this->status, $this->id);

			return $this->stmt->execute();
		}

		public function updateUponCheckout() {

			$this->stmt = $this->con->prepare('UPDATE ' .$this->table. ' SET status = "pending", unique_id = ? WHERE user_id = ? AND status = "incomplete"');

			$this->stmt->bind_param('ii', $this->unique_id, $this->user_id);

			return $this->stmt->execute();
		}

		public function generateUniqueId() {
			$this->stmt = $this->con->prepare('SELECT * FROM ' . $this->table . ' 
				WHERE unique_id IS NOT NULL
				ORDER BY ID DESC LIMIT 1');		
			$this->stmt->execute();
			$result = $this->stmt->get_result();
			$row = $result->fetch_array();
			$return = $row['unique_id'] + 1;

			return $return;
		}

		public function getOrderConfirm() {
			$this->stmt = $this->con->prepare('SELECT * FROM ' . $this->table . ' 
				WHERE status = ? ');
			$this->stmt->bind_param('s', $this->status);			
			$this->stmt->execute();

			return $this->stmt->get_result();
		}

	}

 ?>