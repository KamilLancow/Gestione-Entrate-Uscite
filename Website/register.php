<?php
include('assets/php/config.php');
$isError = false;
$isRegisterSuccesful = false;
$color = "#cc0000";// default color red

if($_SERVER["REQUEST_METHOD"] == "POST") {
    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
	}
	$nome = $_POST['nome'];
	$cognome = $_POST['cognome'];
    $email = $_POST['email'];
    $passw = $_POST['password'];
    $sql = "SELECT null FROM utenti WHERE email ='". $email. "'";
    $result = mysqli_query($conn, $sql);

    if (mysqli_num_rows($result) > 0) {
        //session_register("email"); //deprecated function
		//header("Location: management.php");
		$isError = true;
		$error = "L'email inserita è già registrata.";
		$color = "#cc0000";
    } else {
		$isError = false;
		$sql = "INSERT INTO utenti(nome, cognome, email, password) VALUES('". $nome. "', '". $cognome. "', '". $email. "', '". $passw. "')";
		$result = mysqli_query($conn, $sql);
		if($result){
			$isRegisterSuccesful = true;
			$registered = "Registrazione effettuata con successo.";
			$color = "#00cc00";
		} else {// potrei togliere questa opzione
			echo "Error: " . $sql . "<br>" . mysqli_error($conn);
		}
    }

    mysqli_close($conn);
}
?>

<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Entrate-Uscite - Register</title>
<!--===============================================================================================-->
	<link rel="shortcut icon" type="image/png" href="assets/images/icons/lit-icon.ico"/>
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
						Registrati
					</span>
					<span class="login100-form-title p-b-48">
						<a href="index.html">
							<img src="assets/images/icons/lit-icon.ico" alt="" style="width:70px; height:70px;">
						</a>
                    </span>
                    <!--error message-->
					<div style = "font-size:12px; color:<?php echo $color ?>; padding-bottom: 15px;"><?php if($isError) echo $error; else if($isRegisterSuccesful) echo $registered; ?></div>
					
					<!--input nome-->
                    <div class="wrap-input100 validate-input" data-validate = "Inserisci il Nome">
						<input class="<?php if($isError) echo "input100 has-val"; else echo "input100"?>" type="text" name="nome" value="<?php if($isError) echo $nome;?>">
						<span class="focus-input100" data-placeholder="Nome"></span>
                    </div>
					
					<!--input cognome-->
                    <div class="wrap-input100 validate-input" data-validate = "Inserisci il Cognome">
						<input class="<?php if($isError) echo "input100 has-val"; else echo "input100"?>" type="text" name="cognome" value="<?php if($isError) echo $cognome;?>">
						<span class="focus-input100" data-placeholder="Cognome"></span>
                    </div>

					<!--input email-->
					<div class="wrap-input100 validate-input" data-validate = "email@example.com">
						<input class="<?php if($isError) echo "input100 has-val"; else echo "input100"?>" type="text" name="email" value="<?php if($isError) echo $email;?>">
						<span class="focus-input100" data-placeholder="Email"></span>
					</div>

					<!--input password-->
					<div class="wrap-input100 validate-input" data-validate="Inserisci una password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="<?php if($isError) echo "input100 has-val"; else echo "input100"?>" type="password" name="password" value="<?php if($isError) echo $passw;?>">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>

					<!--button-->
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								Registrati
							</button>
						</div>
					</div>

					<!--link to login-->
					<div class="text-center p-t-30">
						<span class="txt1">
							Hai già un account?
						</span>

						<a class="txt2" href="login.php">
							Accedi
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
<!--===============================================================================================-->

</body>
</html>