<?php
include('assets/php/config.php');
session_start();
$isError = false;

if($_SERVER["REQUEST_METHOD"] == "POST") {
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    $email = $_POST['email'];
    $passw = $_POST['password'];
	$sql = "SELECT null FROM utenti WHERE email ='". $email. "' AND password = '". $passw. "'";
    $result = mysqli_query($conn, $sql);

    if (mysqli_num_rows($result) == 1) {
		$sql = "UPDATE utenti SET num_accessi =num_accessi + 1 WHERE email ='". $email. "' AND password = '". $passw. "'";
		if (mysqli_query($conn, $sql)) {
			$_SESSION['login_user_email'] = $email;
        	header("Location: management.php");
		} else {
			echo "Error updating record: " . mysqli_error($conn);
		}
    } else {
        $isError = true;
        $error = "Email o Password inserita errata.";
    }

    mysqli_close($conn);
}
?>

<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Gestione Entrate-Uscite - Login</title>
<!--===============================================================================================-->
	<link rel="shortcut icon" type="image/png" href="assets/images/icons/lit-icon.ico"/>
<!--===============================================================================================-->
	<!-- <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css"> -->
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/css/util.css">
	<link rel="stylesheet" type="text/css" href="assets/css/main.css">
<!--===============================================================================================-->
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
                <!--form-->
				<form class="login100-form validate-form" action="" method="POST">
					<span class="login100-form-title p-b-26">
						Accedi al tuo account
					</span>
					<span class="login100-form-title p-b-33">
						<a href="index.html">
							<img src="assets/images/icons/lit-icon.ico" alt="" style="width:70px; height:70px;">
						</a>
                    </span>
                    <!--error message-->
                    <div style = "font-size:12px; color:#cc0000; padding-bottom: 15px;"><?php if($isError)echo $error; ?></div>
                    <!----------------->
					<div class="wrap-input100 validate-input" data-validate = "email@example.com">
						<input class="<?php if($isError) echo "input100 has-val"; else echo "input100"?>" type="text" name="email" value="<?php if($isError) echo $email;?>">
						<span class="focus-input100" data-placeholder="Email"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Inserisci una password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="<?php if($isError) echo "input100 has-val"; else echo "input100"?>" type="password" name="password" value="<?php if($isError) echo $passw;?>">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								Login
							</button>
						</div>
					</div>

					<div class="text-center p-t-115">
						<span class="txt1">
							Non hai un account?
						</span>

						<a class="txt2" href="register.php"><!--register-->
							Registrati
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
	<script src="assets/js/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="assets/js/main.js"></script>

</body>
</html>
