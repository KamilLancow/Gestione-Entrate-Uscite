<?php
   include('config.php');
   session_start();
   
   $user_check = $_SESSION['login_user_email'];
   $ses_sql = mysqli_query($conn,"SELECT nome, cognome FROM utenti WHERE email = '". $user_check. "'");
   $row = mysqli_fetch_array($ses_sql,MYSQLI_ASSOC);
   
   $login_session = $row['nome']. " ". $row['cognome'];
   
   if(!isset($_SESSION['login_user_email'])){
      header("location: ../../login.php");
      die();
   }
?>