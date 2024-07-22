-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2024 at 08:23 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `isbn` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`isbn`, `title`, `author`, `genre`, `price`, `quantity`) VALUES
('0025', 'ampore', 'kopo', 'horror', 245, 4),
('ISBN001', 'Book 1', 'Author 1', 'Fiction', 20.99, 9),
('ISBN002', 'Book 2', 'Author 2', 'Fantasy', 25.5, 7),
('ISBN003', 'Book 3', 'Author 3', 'Mystery', 18.75, 14),
('ISBN004', 'Book 4', 'Author 4', 'Thriller', 22, 11),
('ISBN005', 'Book 5', 'Author 5', 'Science Fiction', 30.25, 6),
('ISBN006', 'Book 6', 'Author 6', 'Romance', 15.99, 20),
('ISBN007', 'Book 7', 'Author 7', 'Historical Fiction', 28.75, 18),
('ISBN008', 'Book 8', 'Author 8', 'Biography', 19.5, 25),
('ISBN009', 'Book 9', 'Author 9', 'Self-Help', 17.25, 30),
('ISBN010', 'Book 10', 'Author 10', 'Business', 24.99, 22);

-- --------------------------------------------------------

--
-- Table structure for table `soldbooks`
--

CREATE TABLE `soldbooks` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sold_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `soldbooks`
--

INSERT INTO `soldbooks` (`id`, `user_id`, `isbn`, `title`, `author`, `genre`, `price`, `quantity`, `sold_date`) VALUES
(5, NULL, 'ISBN001', 'Book 1', 'Author 1', 'Fiction', 20.99, 1, '2024-05-24 05:31:02');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `usertype` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `usertype`, `user_id`) VALUES
('topo', 'topo123', 'admin', 1),
('lefika', 'lefika123', 'customer', 2),
('tumo', 'tumo4', 'customer', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `soldbooks`
--
ALTER TABLE `soldbooks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `soldbooks`
--
ALTER TABLE `soldbooks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `soldbooks`
--
ALTER TABLE `soldbooks`
  ADD CONSTRAINT `soldbooks_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
