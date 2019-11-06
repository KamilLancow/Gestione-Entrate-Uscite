<?php
   include('assets/php/session.php');
?>
<html>
   <head>
      <title>Gestione Entrate-Uscite</title>
      <!--===============================================================================================-->
	      <link rel="shortcut icon" type="image/png" href="assets/images/icons/lit-icon.ico"/>
      <!--===============================================================================================-->
         <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
         <link rel="stylesheet" type="text/css" href="assets/vendor/perfect-scrollbar/perfect-scrollbar.css">
         <link rel="stylesheet" type="text/css" href="assets/css/table.css">
         <link rel="stylesheet" type="text/css" href="assets/css/table-restyle.css">
      <!--===============================================================================================-->
         <link rel="stylesheet" type="text/css" href="assets/css/util.css">
         <link rel="stylesheet" type="text/css" href="assets/css/management.css">
      <!--===============================================================================================-->
      <script type="text/javascript">
            function loadTableDati() {
               $.get("manage-panoramicadati.php");
               
               return false;
            }
         </script>
   </head>
   <body>
      <!----------------------navbar------------------------->
      <nav class="mainmenu-area">
         <div class="navbar-header">
               <a class="navbar-brand" href="#"><img src="assets/images/icons/lit-icon.ico" alt="" style="width:50px; height:50px;"></a>
               <p>Benvenuto <?php echo $login_session; ?></p>
         </div>
         <div class="collapse navbar-collapse">
               <ul class="nav navbar-nav mainmenu">
                  <li class="right-button"><a class="panoramica-dati" href="#" onclick="loadTableDati();">Panoramica dati</a></li>
                  <li class="right-button"><a class="attivita" href="#">Attività</a></li>
               </ul>
               <div class="right-button">
                  <a class="logout" href="index.html">Log out</a>
               </div>
         </div>
      </nav>

      <div class="content-container">
         <!-- <?php //include('manage-panoramicadati.php'); ?> -->
         <!----------------------table------------------------->
         <div class="wrap-table100">
            <div class="table100 ver1 m-t-50">
               <div class="table100-head">
                  <table>
                     <thead>
                        <tr class="row100 head">
                              <th class="cell100 column0"></th>
                              <th class="cell100 column1">Tipologia</th>
                              <th class="cell100 column2">Descrizione</th>
                              <th class="cell100 column3">Data</th>
                              <th class="cell100 column4">Importo</th>
                              <th class="cell100 column5"></th>
                        </tr>
                     </thead>
                  </table>
               </div>

               <div class="table100-body js-pscroll "><!-- ps ps--active-y -->
                     <table>
                        <tbody>
                           <!-- <tr class="row100 body">
                                 <td class="cell100 column1">Like a butterfly</td>
                                 <td class="cell100 column2">Boxing</td>
                                 <td class="cell100 column3">9:00 AM - 11:00 AM</td>
                                 <td class="cell100 column4">Aaron Chapman</td>
                                 <td class="cell100 column5">10</td>
                           </tr> -->
                           <?php
                              include('assets/php/config.php');
                              // Check connection
                              if (!$conn) {
                                 die("Connection failed: " . mysqli_connect_error());
                              }

                              $sql = "SELECT * FROM dati WHERE ID_utente ='". $user_check. "'";
                              $result = mysqli_query($conn, $sql);
                              if (mysqli_num_rows($result) > 0) {
                                 while($row = mysqli_fetch_assoc($result)) {
                                    $desc = substr($row['descrizione'],0,300);
                                    if($row["tipo_bilancio"] == "Banca") $type_img = '<img src="assets/images/icons/Type_Banca.png" "></img>';
                                       else $type_img = '<img src="assets/images/icons/Type_Contanti.png" "></img>';
                                    if($row["tipo"] == "USCITA MERCE" || $row["tipo"] == "USCITA MERCE SOSPESI") $arrow = '<img src="assets/images/icons/Arrow_red.png" "></img>';
                                       else $arrow = '<img src="assets/images/icons/Arrow_green.png" "></img>';

                                    echo '<tr class="row100 body">';
                                    echo '<td class="cell100 column0">'. $type_img. '</td>';
                                    echo '<td class="cell100 column1">'. $row["tipo"]. '<h6>'.$row["tipo_bilancio"].'</h6>'. '</td>';
                                    echo '<td class="cell100 column2">'. $desc. '</td>';
                                    echo '<td class="cell100 column3">'. $row["data"]. '</td>';
                                    echo '<td class="cell100 column4">'. $row["importo"]. '</td>';
                                    echo '<td class="cell100 column5">'. $arrow. '</td>';
                                    echo '</tr>';
                                 }
                              } else {
                                 echo '<tr class="row100 body">';
                                 echo '<td class="cell100 column0"></td>';
                                 echo '<td class="cell100 column1"></td>';
                                 echo '<td class="cell100 column2" style="text-align: center;">Nessun contenuto nella tabella</td>';
                                 echo '<td class="cell100 column3"></td>';
                                 echo '<td class="cell100 column4"></td>';
                                 echo '<td class="cell100 column5"></td>';
                                 echo '</tr>';
                              }
                              mysqli_close($conn);
                           ?>
                        </tbody>
                     </table>

                     <!-- <div class="ps__rail-x" style="left: 0px; bottom: 0px;">
                        <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
                     </div>
                     <div class="ps__rail-y" style="top: 0px; height: 585px; right: 5px;">
                        <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 293px;"></div>
                     </div> -->
               </div>
            </div>
         </div>
         <!---------------------------------------------------------------->
      </div>

      <!--===============================================================================================-->	
         <script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
         <script src="assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
         <script>
            $('.js-pscroll').each(function(){
               var ps = new PerfectScrollbar(this);

               $(window).on('resize', function(){
                  ps.update();
               })
            });
               
            
         </script>
         <script src="assets/js/main.js"></script>
         
      <!--===============================================================================================-->
   </body>
</html>