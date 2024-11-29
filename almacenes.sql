-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 29-11-2024 a las 18:10:01
-- Versión del servidor: 10.6.20-MariaDB-ubu2004
-- Versión de PHP: 8.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `almacenes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacen`
--

CREATE TABLE `almacen` (
  `id` bigint(20) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `almacen`
--

INSERT INTO `almacen` (`id`, `direccion`, `latitud`, `longitud`, `nombre`) VALUES
(1, 'Av. Principal 123, Ciudad', 19.432608, -99.133209, 'Almacén Central'),
(2, 'Av. Principal 123, Ciudad', 19.432608, -99.133209, 'Almacén Mante'),
(3, 'Av. Principal 123, Ciudad', 19.432608, -99.133209, 'Almacén Principal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `almacenes`
--

CREATE TABLE `almacenes` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `latitud` decimal(10,8) DEFAULT NULL,
  `longitud` decimal(11,8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `upc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `descripcion`, `upc`) VALUES
(1, 'Producto de prueba', '123456789012'),
(2, 'Producto de prueba', '7507040090080'),
(3, 'Helado', '87878754'),
(4, 'Cigarros', '11212266');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL,
  `upc` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `empleado` varchar(255) DEFAULT NULL,
  `fecha_hora` datetime(6) DEFAULT NULL,
  `origen_destino` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `almacen_id` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `almacen` varchar(255) DEFAULT NULL,
  `destino_origen` varchar(255) DEFAULT NULL,
  `tipo_transaccion` varchar(255) DEFAULT NULL,
  `upc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transaccion`
--

INSERT INTO `transaccion` (`id`, `cantidad`, `empleado`, `fecha_hora`, `origen_destino`, `tipo`, `almacen_id`, `producto_id`, `almacen`, `destino_origen`, `tipo_transaccion`, `upc`) VALUES
(1, 100, 'María López', '2024-11-25 01:24:41.000000', 'Cliente ABC', 'Salida', 1, 1, NULL, NULL, NULL, NULL),
(2, 100, 'María López', '2024-11-25 10:42:26.000000', 'Cliente ABC', 'Entrada', 1, 1, NULL, NULL, NULL, NULL),
(3, 100, 'María López', '2024-11-25 10:43:03.000000', 'Cliente ABC', 'Entrada', 1, 1, NULL, NULL, NULL, NULL),
(4, 70, 'María López', '2024-11-25 10:43:28.000000', 'Cliente ABC', 'Salida', 1, 1, NULL, NULL, NULL, NULL),
(5, 40, 'María López', '2024-11-25 10:53:30.000000', 'Cliente ABC', 'Entrada', 1, 1, NULL, NULL, NULL, NULL),
(6, 40, 'María López', '2024-11-25 10:53:54.000000', 'Cliente ABC', 'Salida', 1, 1, NULL, NULL, NULL, NULL),
(18, 10, 'Juan Pérez', '2024-11-28 18:20:56.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(19, 10, 'Juan Pérez', '2024-11-28 18:21:20.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(20, 10, 'Juan Pérez', '2024-11-28 18:23:38.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(21, 10, 'Juan Pérez', '2024-11-28 18:23:56.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(22, 10, 'Juan Pérez', '2024-11-28 18:26:59.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(23, 10, 'Juan Pérez', '2024-11-28 18:27:07.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(24, 10, 'Juan Pérez', '2024-11-28 18:30:15.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(25, 10, 'Juan Pérez', '2024-11-28 18:32:39.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(26, 10, 'Juan Pérez', '2024-11-28 20:37:48.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL),
(27, 40, 'María López', '2024-11-29 11:03:25.000000', 'Cliente ABC', 'Entrada', 1, 4, NULL, NULL, NULL, NULL),
(28, 10, 'María López', '2024-11-29 11:03:47.000000', 'Cliente ABC', 'Entrada', 3, 4, NULL, NULL, NULL, NULL),
(29, 10, 'María López', '2024-11-29 11:04:16.000000', 'Cliente ABC', 'Salida', 1, 4, NULL, NULL, NULL, NULL),
(30, 10, 'Juan', '2024-11-29 12:00:10.000000', 'Almacén Mante', 'Entrada Traspaso', 3, 2, NULL, NULL, NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `almacen`
--
ALTER TABLE `almacen`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `almacenes`
--
ALTER TABLE `almacenes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `upc` (`upc`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2ba1r6kig5wv7lympaplf01c0` (`almacen_id`),
  ADD KEY `FKg1num9gu2krun4hwjrfejx5jn` (`producto_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `almacen`
--
ALTER TABLE `almacen`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `almacenes`
--
ALTER TABLE `almacenes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `FK2ba1r6kig5wv7lympaplf01c0` FOREIGN KEY (`almacen_id`) REFERENCES `almacen` (`id`),
  ADD CONSTRAINT `FKg1num9gu2krun4hwjrfejx5jn` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
