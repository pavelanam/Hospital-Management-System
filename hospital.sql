-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2017 at 08:55 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Name` varchar(30) NOT NULL,
  `Age` int(2) NOT NULL,
  `Contact` int(11) NOT NULL,
  `City` varchar(15) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `ID` int(10) NOT NULL,
  `Qualification` varchar(100) NOT NULL,
  `Specialist` varchar(100) NOT NULL,
  `Salary` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Name`, `Age`, `Contact`, `City`, `Gender`, `ID`, `Qualification`, `Specialist`, `Salary`) VALUES
('Pavel', 25, 1756558060, 'Dhaka', 'Male', 101, 'MBBS', 'Heart', 300000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `UserName` varchar(30) NOT NULL,
  `UserID` varchar(15) NOT NULL,
  `Password` varchar(15) NOT NULL,
  `UserType` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`UserName`, `UserID`, `Password`, `UserType`) VALUES
('Niloy', '1990', 'niloy', 'Admin'),
('Maria', '1994', 'maria', 'Doctor'),
('Koushik', '1997', 'koushik', 'Receptionist');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `Name` varchar(30) NOT NULL,
  `Age` int(2) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Phone No.` int(11) NOT NULL,
  `City` varchar(15) NOT NULL,
  `Token  No.` int(100) NOT NULL,
  `Problem` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `receptionist`
--

CREATE TABLE `receptionist` (
  `Name` varchar(30) NOT NULL,
  `Age` int(2) NOT NULL,
  `Contact` int(11) NOT NULL,
  `City` varchar(15) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `ID` int(10) NOT NULL,
  `Qualification` varchar(100) NOT NULL,
  `Salary` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receptionist`
--

INSERT INTO `receptionist` (`Name`, `Age`, `Contact`, `City`, `Gender`, `ID`, `Qualification`, `Salary`) VALUES
('Muyeen', 23, 1859577414, 'Dhaka', 'Male', 102, 'CSE', 30000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`Token  No.`);

--
-- Indexes for table `receptionist`
--
ALTER TABLE `receptionist`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
