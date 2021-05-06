<?php 

	require_once __DIR__ . "./dbconnect.php";

	class User extends Db {

		private $stmt;
		private $con;
		private $table = 'users';

		public $name;
		public $username;
		public $password;

		public function __construct()
		{
			$this->con = $this->connect();
		}

		public function store() {
			$this->stmt = $this->con->prepare('INSERT INTO '. $this->table .' (name, username, password) VALUES (?, ?, ?)');
			$this->stmt->bind_param('sss', $this->name, $this->username, $this->password);

			return $this->stmt->execute();
		}

		public function select() {
			$this->stmt = $this->con->prepare('SELECT * FROM ' . $this->table);
			$this->stmt->execute();

			return $this->stmt->get_result();
		}

		public function checkLogin() {

			$this->stmt = $this->con->prepare('SELECT * FROM ' . $this->table . ' WHERE username = ? AND password = ?');
			$this->stmt->bind_param('ss', $this->username, $this->password);
			$this->stmt->execute();

			return $this->stmt->get_result();
		}
	}

 ?>