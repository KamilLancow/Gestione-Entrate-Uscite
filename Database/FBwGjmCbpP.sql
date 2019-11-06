-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Lug 09, 2019 alle 17:23
-- Versione del server: 8.0.13-4
-- Versione PHP: 7.2.19-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `FBwGjmCbpP`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `dati`
--

CREATE TABLE `dati` (
  `tipo` varchar(20) NOT NULL,
  `tipo_bilancio` varchar(8) NOT NULL,
  `descrizione` varchar(300) NOT NULL,
  `data` varchar(10) NOT NULL,
  `importo` varchar(15) NOT NULL,
  `ID_utente` varchar(30) NOT NULL,
  `ID_dati` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `dati`
--

INSERT INTO `dati` (`tipo`, `tipo_bilancio`, `descrizione`, `data`, `importo`, `ID_utente`, `ID_dati`) VALUES
('ENTRATA HOTEL', 'Contanti', 'Suspicio? Bene ... tunc ibimus? Quis uh ... CONEXUS locus his diebus? Quisque semper aliquid videtur, in volutpat mauris. Nolo enim dicere. Vobis neque ab aliis. Ego feci memetipsum explicans. Gus mor', '04/06/2019', '120.0 €', 'email@email.com', 1),
('ENTRATA RISTORANTE', 'Banca', 'Ille vivere. Ut ad te quaerebam ... purgare caeli. Sunt uh ... nonnullus propter errorem qui de rebus inter nos et iacere puto suus in causa, id est in mensa. Levir meus, priusquam oppugnarent tempus quis', '03/06/2019', '59.5 €', 'email@email.com', 2),
('USCITA MERCE', 'Contanti', 'His duobus, sicariorum. Et orci aetate erat, sed nescio quo modo se gerendo levir meus aufert a me. Propter hoc \'interventu \'vivere me scito. Et tamen hoc quod multo altius est eget arcu. Fecitque quod', '03/06/2019', '73.0 €', 'email@email.com', 3),
('USCITA MERCE SOSPESI', 'Contanti', 'Uno ictu cruentus ille utrimque - statuit American Mexicanus gubernationes contra CONEXUS, et amputavit auriculam eius generis copia methamphetamine ad Africum. Quo facto suo', '02/06/2019', '145.5 €', 'email@email.com', 4),
('INCASSO POS', 'Banca', 'Sumus tam adultis. Non est enim tibi nescio fingunt. Confusio esse cupio. Scio te debeo meae. At etiam, ut caveant ab his eu. In tuo positum, idem fecissem. Constitutione, quam molesta', '04/06/2019', '50.0 €', 'email@email.com', 5),
('DEPOSITO BANCA', 'Banca', 'Respice ... Sentio sicut ego vobis exponam: sed vias breve iterum conabor. Fugere hoc maior difficultas est nobis. Perdet eam batch nostri. Et delebis eam ac omnia opus est vestigium, ut possimus', '04/06/2019', '500.0 €', 'email@email.com', 6),
('ENTRATA HOTEL', 'Banca', 'Suspicio? Bene ... tunc ibimus? Quis uh ... CONEXUS locus his diebus? Quisque semper aliquid videtur, in volutpat mauris. Nolo enim dicere. Vobis neque ab aliis. Ego feci memetipsum explicans. Gus mor', '04/06/2019', '120.0 €', 'mario.rossi@email.com', 7),
('ENTRATA RISTORANTE', 'Contanti', 'Ille vivere. Ut ad te quaerebam ... purgare caeli. Sunt uh ... nonnullus propter errorem qui de rebus inter nos et iacere puto suus in causa, id est in mensa. Levir meus, priusquam oppugnarent tempus quis', '03/06/2019', '22.7 €', 'mario.rossi@email.com', 9),
('INCASSO POS', 'Banca', 'Sumus tam adultis. Non est enim tibi nescio fingunt. Confusio esse cupio. Scio te dem molesta', '04/06/2019', '199.0 €', 'mario.rossi@email.com', 10),
('USCITA MERCE', 'Contanti', 'Lorem Ipsum', '01/07/2019', '58.0 €', 'mario.rossi@email.com', 11),
('INCASSO POS', 'Banca', 'qualcosa', '04/07/2019', '40.0 €', 'pinco.pallino@email.com', 12),
('USCITA MERCE', 'Contanti', 'asd', '03/07/2019', '2.0 €', 'mario.rossi@email.com', 13),
('INCASSO POS', 'Banca', 'Qui nunc loqueris? Ecce qui cogitatis? Vos scitis quanta ego faciam annum Id est, ut ego dixi vobis non credunt. Scis quid si ne subito placuit ire in opus? ', '23/02/2019', '55.99 €', 'mario.rossi@email.com', 14),
('USCITA MERCE', 'Banca', 'Pergo coctione, et ego, et tu oblivisci Pinkman. Obliviscendum hoc unquam factum. Intelligamus hoc in sola SINGULTO multo aliter atque fructuosa negotium structura. Malo B. Option. ', '23/02/2019', '243.7 €', 'mario.rossi@email.com', 15),
('USCITA MERCE SOSPESI', 'Contanti', 'Saule ... , ostendit quod hoc quidem ... hoc quod dixit, ... potuit adiutorium mihi, et educat me in tota vita nova facio certus ut Im \'non invenit. Ego quidem illius memini Saul. Gus sit amet interfíciat mei tota familia. Nunc opus est mihi iste. Saul ... nunc Saule. ', '23/02/2019', '170.0 €', 'mario.rossi@email.com', 16),
('DEPOSITO BANCA', 'Banca', 'Jackson Isai? Tu quoque ... A te quidem a ante. Vos scitis quod blinking res Ive \'been vocans super vos? Et conteram illud, et conteram hoc. Maledicant druggie excors. Iam hoc tu facere conatus sum ad te in omni tempore? ', '23/02/2019', '1035.0 €', 'mario.rossi@email.com', 17),
('USCITA MERCE', 'Contanti', 'Pergo coctione, et ego, et tu oblivisci Pinkman. Obliviscendum hoc unquam factum. Intelligamus hoc in sola SINGULTO multo aliter atque fructuosa negotium structura. Malo B. Option. ', '23/02/2019', '57.0 €', 'mario.rossi@email.com', 18),
('ENTRATA RISTORANTE', 'Contanti', 'Tu nunc coci ejus. Tu autem cocus Lab et probavimus liceat mihi sine causa est nunc coci interficere. Reputo it! Suus egregie. Ut antecedat. Quod si putas me posse facere, ergo ante. Pone aute in caput, et nunc interficere. Faciat! Fac. Fa', '23/02/2019', '30.0 €', 'mario.rossi@email.com', 19),
('ENTRATA RISTORANTE', 'Banca', 'piternum. Ut sciat oportet motum. Nunquam invenies eum. Hic de tabula. Ego vivere, ut debui, et nunc fiant. Istuc quod opus non est. Lorem ipsum occurrebat pragmaticam semper ut, si ', '23/02/2019', '75.0 €', 'mario.rossi@email.com', 20),
('USCITA MERCE SOSPESI', 'Contanti', 'Mauris a nunc occideritis me rectum. Videtur quod Ive facillimum, qui fecit vos. Potes me interficere, sine testibus et tunc manere', '23/02/2019', '130.0 €', 'mario.rossi@email.com', 21),
('ENTRATA RISTORANTE', 'Contanti', 'Ut sibi fuerat socius sagittis. Ego intervenerit. Vere quia a te nuper iratus occidit illos undecim annorum puer. Deinde, si hoc forte qui fuit imperavit. ', '23/02/2019', '13.0 €', 'mario.rossi@email.com', 22),
('ASSEGNO', 'Banca', 'Pergo coctione, et ego, et tu oblivisci Pinkman. Obliviscendum hoc unquam factum. Intelligamus hoc in sola SINGULTO multo aliter atque fructuosa negotium structura. Malo B. Option. ', '23/02/2019', '153.0 €', 'mario.rossi@email.com', 23);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`nome`, `cognome`, `email`, `password`) VALUES
('Pinco', 'Pallino', 'pinco.pallino@email.com', 'ciao1234'),
('Kamil', 'Lancow', 'email@email.com', 'ciao1234'),
('Mario', 'Rossi', 'mario.rossi@email.com', 'ciao1234');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `dati`
--
ALTER TABLE `dati`
  ADD PRIMARY KEY (`ID_dati`),
  ADD KEY `ID_utente` (`ID_utente`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `dati`
--
ALTER TABLE `dati`
  MODIFY `ID_dati` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `dati`
--
ALTER TABLE `dati`
  ADD CONSTRAINT `dati_ibfk_1` FOREIGN KEY (`ID_utente`) REFERENCES `utenti` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
