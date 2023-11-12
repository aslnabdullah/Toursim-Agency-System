# Customer
### Customer Table Creation
...
CREATE TABLE `customer` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`room_id` int NOT NULL,
`name_surname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`phone_number` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`customer_ID` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`adult_number` int NOT NULL,
`child_number` int NOT NULL,
`total_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
...

### Sample Data 
INSERT INTO `customer` (`id`, `hotel_id`, `room_id`, `name_surname`, `phone_number`, `customer_ID`, `adult_number`, `child_number`, `total_price`) VALUES
(2, 14, 28, 'abdullah aslan', '48763274832', '23874632', 2, 3, 198000),
(3, 14, 28, 'fatih batur', '+923492493', '235476235', 2, 2, 54000),
(4, 16, 31, 'a', '1', '1', 1, 1, 2750);


# Facility Features
### Facility Features Table Creation

CREATE TABLE `facility_features` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`hotel_features` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Sample Data 
INSERT INTO `facility_features` (`id`, `hotel_id`, `hotel_features`) VALUES
(52, 12, 'Swimming Pool'),
(53, 12, 'Fitness Center'),
(54, 12, 'SPA'),
(55, 13, 'Fre Wifi'),
(56, 13, '7/24 Room Service'),
(57, 14, 'Free Parking'),
(58, 14, 'Fre Wifi'),
(59, 14, 'Swimming Pool'),
(60, 14, 'Fitness Center'),
(61, 14, 'Hotel Concierge '),
(62, 14, 'SPA'),
(63, 14, '7/24 Room Service'),
(64, 15, 'Free Parking'),
(65, 15, 'Fre Wifi'),
(66, 15, 'Swimming Pool'),
(67, 15, 'Fitness Center'),
(68, 15, 'Hotel Concierge '),
(69, 15, 'SPA'),
(70, 15, '7/24 Room Service'),
(71, 16, 'Free Parking'),
(72, 16, 'Fre Wifi'),
(73, 16, 'Swimming Pool');


# hostel type
### hostel type Table Creation

CREATE TABLE `hostel_type` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`hostel_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Sample Data 
INSERT INTO `hostel_type` (`id`, `hotel_id`, `hostel_type`) VALUES
(58, 12, 'Full Pension'),
(59, 12, 'Half Board'),
(60, 13, 'Half Board'),
(61, 13, 'Bed Only'),
(62, 14, 'Ultra All Inclusive'),
(63, 14, 'All Inclusive'),
(64, 14, 'Full credit excluding alcohol'),
(65, 15, 'Ultra All Inclusive'),
(66, 15, 'All Inclusive'),
(67, 15, 'Room Breakfast'),
(68, 16, 'Half Board'),
(69, 16, 'Bed Only'),
(70, 16, 'Full credit excluding alcohol');

# Hotel
### Hotel Table Creation

CREATE TABLE `hotel` (
`id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`adress` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`star` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Sample Data
INSERT INTO `hotel` (`id`, `name`, `city`, `adress`, `email`, `phone`, `star`) VALUES
(12, 'Barden Hotel', 'Siirt', 'Yeni Mahalle ', 'barden@gmail.com', '0484 223 9504', '5'),
(13, 'St. George Lafayette', 'Paris', 'St. George', 'lafayette@gmail.com', '+912343243', '4'),
(14, 'Rixos', 'Antalya', 'alanya ', 'rixos@gmail.com', '05423123784', '5'),
(15, 'ipek royal', 'antwerp', 'allmoznier street', 'ipekroyal@', '+2348729432', '5'),
(16, 'a', 'a', 'a', 'a', 'a', 'a'),
(17, 'merit royal', 'lefkoşa', 'lekoşa street ', 'lefkoşa@', '+1247237482', '5');

# Room
### Room Table Creation
CREATE TABLE `room` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`room_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`season_id` int NOT NULL,
`pension_id` int NOT NULL,
`stock` int NOT NULL,
`adult_price` int NOT NULL,
`child_price` int NOT NULL,
`bed` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Sample Data

INSERT INTO `room` (`id`, `hotel_id`, `room_type`, `season_id`, `pension_id`, `stock`, `adult_price`, `child_price`, `bed`) VALUES
(24, 12, 'Single Person', 26, 59, 100, 2000, 1500, 1),
(25, 12, 'Double Room', 26, 58, 50, 2500, 2000, 2),
(26, 13, 'Family Room', 27, 60, 75, 5000, 3500, 4),
(27, 13, 'Double Room', 28, 61, 45, 4000, 3000, 2),
(28, 14, 'Royal Suite', 29, 62, 149, 7500, 6000, 6),
(29, 14, 'Family Room', 30, 63, 120, 6000, 4500, 4),
(30, 15, 'Family Room', 31, 65, 100, 5000, 4000, 4),
(31, 16, 'Single Person', 32, 69, 14, 1500, 1250, 1);

# Room Features
### Room Features Table Creation
CREATE TABLE `room_features` (
`id` int NOT NULL,
`room_id` int NOT NULL,
`room_features` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Sample Data

INSERT INTO `room_features` (`id`, `room_id`, `room_features`) VALUES
(20, 28, 'Television'),
(21, 28, 'Mini Bar'),
(22, 28, 'Game Console'),
(23, 28, 'Safe Box'),
(24, 28, 'Projection'),
(25, 29, 'Television'),
(26, 29, 'Mini Bar'),
(27, 29, 'Game Console'),
(28, 29, 'Safe Box'),
(29, 29, 'Projection'),
(30, 25, 'Television'),
(31, 25, 'Mini Bar'),
(32, 25, 'Game Console'),
(33, 25, 'Safe Box'),
(34, 25, 'Projection'),
(35, 30, 'Television'),
(36, 30, 'Mini Bar'),
(37, 30, 'Game Console'),
(38, 30, 'Safe Box'),
(39, 30, 'Projection');

# Season
### Season Table Creation

CREATE TABLE `season` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`season_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`start_date` date NOT NULL,
`finish_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Sample Data

INSERT INTO `season` (`id`, `hotel_id`, `season_name`, `start_date`, `finish_date`) VALUES
(26, 12, 'Summer Season', '2023-05-01', '2023-10-01'),
(27, 13, 'Winter Season', '2024-01-01', '2024-03-01'),
(28, 13, 'Summer Season', '2024-05-01', '2024-09-01'),
(29, 14, 'Summer Season', '2025-05-01', '2025-11-01'),
(30, 14, 'Winter Season', '2025-11-01', '2025-12-31'),
(31, 15, 'Summer Season', '2023-06-01', '2023-11-01'),
(32, 16, 'Winter Season', '2023-01-01', '2023-06-01');


# User
### User Table Creation

CREATE TABLE `user` (
`id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`type` enum('admin','user','') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

### Sample Data

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'Abddullah Aslan', 'a', '1', 'admin'),
(2, 'fatih batur', 'fatih', '1', 'user'),
(3, 'apo', 'a1', '1', 'user'),
(4, 'eren ', 'e', '1', 'user'),
(5, 'eren ', 'cansev', '1', 'user'),
(6, 'ibrahim boz', 'ibo', '1', 'admin'),
(7, 'ali şimşek', 'ali', '1', 'user');


## ------------------------------
Tablo için indeksler `customer`
--
ALTER TABLE `customer`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `facility_features`
--
ALTER TABLE `facility_features`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hostel_type`
--
ALTER TABLE `hostel_type`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room_features`
--
ALTER TABLE `room_features`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `facility_features`
--
ALTER TABLE `facility_features`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- Tablo için AUTO_INCREMENT değeri `hostel_type`
--
ALTER TABLE `hostel_type`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Tablo için AUTO_INCREMENT değeri `room_features`
--
ALTER TABLE `room_features`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;






