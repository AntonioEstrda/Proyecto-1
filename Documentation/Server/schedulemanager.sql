-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2022 a las 23:33:02
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `schedulemanager`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `academicperiod`
--

CREATE TABLE `academicperiod` (
  `ACADEMICPERIDODID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `INITDATE` date NOT NULL,
  `FINALDATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `department`
--

CREATE TABLE `department` (
  `DEPARTMENTID` int(11) NOT NULL,
  `FACULTYID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enviroment`
--

CREATE TABLE `enviroment` (
  `ENVIROMENTID` int(11) NOT NULL,
  `ENVIROMENTTYPEID` int(11) NOT NULL,
  `CAPACITY` int(4) NOT NULL,
  `UBICATION` varchar(100) NOT NULL,
  `NUMBER` int(3) NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enviromenttype`
--

CREATE TABLE `enviromenttype` (
  `ENVIROMENTTYPEID` int(11) NOT NULL,
  `ENV_ENVIROMENTTYPEID` int(11) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faculty`
--

CREATE TABLE `faculty` (
  `FACULTYID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faculty_enviroment`
--

CREATE TABLE `faculty_enviroment` (
  `FAC_AND_ENV_ID` int(11) NOT NULL,
  `FACULTYID` int(11) NOT NULL,
  `ENVIROMENTID` int(11) NOT NULL,
  `REGISTERDATE` date NOT NULL,
  `FINALDATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faculty_resource`
--

CREATE TABLE `faculty_resource` (
  `FAC_AND_RES_ID` int(11) NOT NULL,
  `FACULTYID` int(11) NOT NULL,
  `RESOURCEID` int(11) NOT NULL,
  `REGISTERDATE` date NOT NULL,
  `FINALDATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `groupt`
--

CREATE TABLE `groupt` (
  `IDGROUP` int(11) NOT NULL,
  `IDSUBJECT` int(11) NOT NULL,
  `ACADEMICPERIDODID` int(11) NOT NULL,
  `CAPACITY` int(2) NOT NULL,
  `NAME` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hourlyassignment`
--

CREATE TABLE `hourlyassignment` (
  `HOURLYASSIGMENTID` int(11) NOT NULL,
  `DEPARTMENTID` int(11) NOT NULL,
  `TEACHERID` int(11) NOT NULL,
  `ACADEMICPERIDODID` int(11) NOT NULL,
  `HOURS` int(2) NOT NULL,
  `VINCULATIONTYPE` enum('Nombrado tiempo completo','Nombrado medio tiempo','Ocasional tiempo completo','Ocasional medio tiempo','Hora cátedra','Becario') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `program`
--

CREATE TABLE `program` (
  `IDPROGRAM` int(11) NOT NULL,
  `DEPARTMENTID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resourceenviroment`
--

CREATE TABLE `resourceenviroment` (
  `ENV_AND_RES_ID` int(11) NOT NULL,
  `ENVIROMENTID` int(11) NOT NULL,
  `RESOURCEID` int(11) NOT NULL,
  `REGISTERDATE` date NOT NULL,
  `FINALDATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resourcet`
--

CREATE TABLE `resourcet` (
  `RESOURCEID` int(11) NOT NULL,
  `RESSOURCETYPEID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resourcetype`
--

CREATE TABLE `resourcetype` (
  `RESSOURCETYPEID` int(11) NOT NULL,
  `RES_RESSOURCETYPEID` int(11) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `ISDISABLE` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `schedule`
--

CREATE TABLE `schedule` (
  `IDSCHEDEULE` int(11) NOT NULL,
  `IDGROUP` int(11) DEFAULT NULL,
  `ENVIROMENTID` int(11) NOT NULL,
  `ACADEMICPERIDODID` int(11) NOT NULL,
  `STARTIME` date NOT NULL,
  `ENDTIME` date NOT NULL,
  `DAY` enum('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') NOT NULL,
  `TYPE` enum('Academic','Event') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subject`
--

CREATE TABLE `subject` (
  `IDSUBJECT` int(11) NOT NULL,
  `IDPROGRAM` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `REQUISITS` varchar(200) NOT NULL,
  `SEMESTER` int(2) NOT NULL,
  `INTENSITY` int(2) NOT NULL,
  `ModalityTime` enum('Diurn','Nocturn') NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher`
--

CREATE TABLE `teacher` (
  `TEACHERID` int(11) NOT NULL,
  `FISRTSNAME` varchar(100) NOT NULL,
  `LASTNAME` varchar(100) NOT NULL,
  `NUMIDEN` varchar(50) NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher_group`
--

CREATE TABLE `teacher_group` (
  `TEAC_GRP_ID` int(11) NOT NULL,
  `TEACHERID` int(11) NOT NULL,
  `IDGROUP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher_schedule`
--

CREATE TABLE `teacher_schedule` (
  `TEAC_SCHED_ID` int(11) NOT NULL,
  `TEACHERID` int(11) NOT NULL,
  `SCHEDEULEID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `academicperiod`
--
ALTER TABLE `academicperiod`
  ADD PRIMARY KEY (`ACADEMICPERIDODID`),
  ADD UNIQUE KEY `ACADEMICPERIOD_PK` (`ACADEMICPERIDODID`);

--
-- Indices de la tabla `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`DEPARTMENTID`),
  ADD UNIQUE KEY `DEPARTMENT_PK` (`DEPARTMENTID`),
  ADD KEY `FACULTY_DEPARTMENT_FK` (`FACULTYID`);

--
-- Indices de la tabla `enviroment`
--
ALTER TABLE `enviroment`
  ADD PRIMARY KEY (`ENVIROMENTID`),
  ADD UNIQUE KEY `ENVIROMENT_PK` (`ENVIROMENTID`),
  ADD KEY `ENVIROMENT_ENVIROMENTTYPE_FK` (`ENVIROMENTTYPEID`);

--
-- Indices de la tabla `enviromenttype`
--
ALTER TABLE `enviromenttype`
  ADD PRIMARY KEY (`ENVIROMENTTYPEID`),
  ADD UNIQUE KEY `ENVIROMENTTYPE_PK` (`ENVIROMENTTYPEID`),
  ADD KEY `ISPARENT_FK` (`ENV_ENVIROMENTTYPEID`);

--
-- Indices de la tabla `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`FACULTYID`),
  ADD UNIQUE KEY `FACULTY_PK` (`FACULTYID`);

--
-- Indices de la tabla `faculty_enviroment`
--
ALTER TABLE `faculty_enviroment`
  ADD PRIMARY KEY (`FAC_AND_ENV_ID`),
  ADD UNIQUE KEY `FACULTY_ENVIROMENT_PK` (`FAC_AND_ENV_ID`),
  ADD KEY `FACULTY_ENVIROMENT2_FK` (`ENVIROMENTID`),
  ADD KEY `FACULTY_ENVIROMENT_FK` (`FACULTYID`);

--
-- Indices de la tabla `faculty_resource`
--
ALTER TABLE `faculty_resource`
  ADD PRIMARY KEY (`FAC_AND_RES_ID`),
  ADD UNIQUE KEY `FACULTY_RESOURCE_PK` (`FAC_AND_RES_ID`),
  ADD KEY `FACULTY_RESOURCE2_FK` (`RESOURCEID`),
  ADD KEY `FACULTY_RESOURCE_FK` (`FACULTYID`);

--
-- Indices de la tabla `groupt`
--
ALTER TABLE `groupt`
  ADD PRIMARY KEY (`IDGROUP`),
  ADD UNIQUE KEY `GROUP_PK` (`IDGROUP`),
  ADD KEY `SUBJECT_GROUP_FK` (`IDSUBJECT`),
  ADD KEY `ACADEMICPERIOD_GROUP_FK` (`ACADEMICPERIDODID`);

--
-- Indices de la tabla `hourlyassignment`
--
ALTER TABLE `hourlyassignment`
  ADD PRIMARY KEY (`HOURLYASSIGMENTID`),
  ADD UNIQUE KEY `HOURLYASSIGNMENT_PK` (`HOURLYASSIGMENTID`),
  ADD KEY `DEPARTAMENT_HOURLYASSIGNMENT_FK` (`DEPARTMENTID`),
  ADD KEY `TEACHER_HOURLYASSIGNMENT_FK` (`TEACHERID`),
  ADD KEY `ACADEMICPERIOD_HOURLYASSIGNMENT_FK` (`ACADEMICPERIDODID`);

--
-- Indices de la tabla `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`IDPROGRAM`),
  ADD UNIQUE KEY `PROGRAM_PK` (`IDPROGRAM`),
  ADD KEY `DEPARTMENT_PROGRAM_FK` (`DEPARTMENTID`);

--
-- Indices de la tabla `resourceenviroment`
--
ALTER TABLE `resourceenviroment`
  ADD PRIMARY KEY (`ENV_AND_RES_ID`),
  ADD UNIQUE KEY `RESOURCEENVIROMENT_PK` (`ENV_AND_RES_ID`),
  ADD KEY `RESOURCEENVIROMENT2_FK` (`RESOURCEID`),
  ADD KEY `RESOURCEENVIROMENT_FK` (`ENVIROMENTID`);

--
-- Indices de la tabla `resourcet`
--
ALTER TABLE `resourcet`
  ADD PRIMARY KEY (`RESOURCEID`),
  ADD UNIQUE KEY `RESOURCE_PK` (`RESOURCEID`),
  ADD KEY `RESOURCE_RESOURCETYPE_FK` (`RESSOURCETYPEID`);

--
-- Indices de la tabla `resourcetype`
--
ALTER TABLE `resourcetype`
  ADD PRIMARY KEY (`RESSOURCETYPEID`),
  ADD UNIQUE KEY `RESOURCETYPE_PK` (`RESSOURCETYPEID`),
  ADD KEY `IS_PARENT_FK` (`RES_RESSOURCETYPEID`);

--
-- Indices de la tabla `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`IDSCHEDEULE`),
  ADD UNIQUE KEY `SCHEDULE_PK` (`IDSCHEDEULE`),
  ADD KEY `GROUP_SCHEDEULE_FK` (`IDGROUP`),
  ADD KEY `ENVIROMENT_SCHEDEULE_FK` (`ENVIROMENTID`),
  ADD KEY `ACADEMICPERIOD_SCHEDULE_FK` (`ACADEMICPERIDODID`);

--
-- Indices de la tabla `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`IDSUBJECT`),
  ADD UNIQUE KEY `SUBJECT_PK` (`IDSUBJECT`),
  ADD KEY `PROGRAM_SUBJECT_FK` (`IDPROGRAM`);

--
-- Indices de la tabla `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`TEACHERID`),
  ADD UNIQUE KEY `TEACHER_PK` (`TEACHERID`);

--
-- Indices de la tabla `teacher_group`
--
ALTER TABLE `teacher_group`
  ADD PRIMARY KEY (`TEAC_GRP_ID`),
  ADD UNIQUE KEY `TEACHER_GROUP_PK` (`TEAC_GRP_ID`),
  ADD KEY `TEACHER_GROUP2_FK` (`IDGROUP`),
  ADD KEY `TEACHER_GROUP_FK` (`TEACHERID`);

--
-- Indices de la tabla `teacher_schedule`
--
ALTER TABLE `teacher_schedule`
  ADD PRIMARY KEY (`TEAC_SCHED_ID`),
  ADD UNIQUE KEY `TEACHER_SCHEDULE_PK` (`TEAC_SCHED_ID`),
  ADD KEY `TEACHER_SCHEDULE2_FK` (`SCHEDEULEID`),
  ADD KEY `TEACHER_SCHEDULE_FK` (`TEACHERID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `academicperiod`
--
ALTER TABLE `academicperiod`
  MODIFY `ACADEMICPERIDODID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `department`
--
ALTER TABLE `department`
  MODIFY `DEPARTMENTID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `enviroment`
--
ALTER TABLE `enviroment`
  MODIFY `ENVIROMENTID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `enviromenttype`
--
ALTER TABLE `enviromenttype`
  MODIFY `ENVIROMENTTYPEID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `faculty`
--
ALTER TABLE `faculty`
  MODIFY `FACULTYID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `faculty_enviroment`
--
ALTER TABLE `faculty_enviroment`
  MODIFY `FAC_AND_ENV_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `faculty_resource`
--
ALTER TABLE `faculty_resource`
  MODIFY `FAC_AND_RES_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `groupt`
--
ALTER TABLE `groupt`
  MODIFY `IDGROUP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `hourlyassignment`
--
ALTER TABLE `hourlyassignment`
  MODIFY `HOURLYASSIGMENTID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `program`
--
ALTER TABLE `program`
  MODIFY `IDPROGRAM` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `resourceenviroment`
--
ALTER TABLE `resourceenviroment`
  MODIFY `ENV_AND_RES_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `resourcet`
--
ALTER TABLE `resourcet`
  MODIFY `RESOURCEID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `resourcetype`
--
ALTER TABLE `resourcetype`
  MODIFY `RESSOURCETYPEID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `schedule`
--
ALTER TABLE `schedule`
  MODIFY `IDSCHEDEULE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `subject`
--
ALTER TABLE `subject`
  MODIFY `IDSUBJECT` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `teacher`
--
ALTER TABLE `teacher`
  MODIFY `TEACHERID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `teacher_group`
--
ALTER TABLE `teacher_group`
  MODIFY `TEAC_GRP_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `teacher_schedule`
--
ALTER TABLE `teacher_schedule`
  MODIFY `TEAC_SCHED_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `FK_DEPARTME_FACULTY_D_FACULTY` FOREIGN KEY (`FACULTYID`) REFERENCES `faculty` (`FACULTYID`);

--
-- Filtros para la tabla `enviroment`
--
ALTER TABLE `enviroment`
  ADD CONSTRAINT `FK_ENVIROME_ENVIROMEN_ENVIROME` FOREIGN KEY (`ENVIROMENTTYPEID`) REFERENCES `enviromenttype` (`ENVIROMENTTYPEID`);

--
-- Filtros para la tabla `enviromenttype`
--
ALTER TABLE `enviromenttype`
  ADD CONSTRAINT `FK_ENVIROME_ISPARENT_ENVIROME` FOREIGN KEY (`ENV_ENVIROMENTTYPEID`) REFERENCES `enviromenttype` (`ENVIROMENTTYPEID`);

--
-- Filtros para la tabla `faculty_enviroment`
--
ALTER TABLE `faculty_enviroment`
  ADD CONSTRAINT `FK_FACULTY__FACULTY_E_ENVIROME` FOREIGN KEY (`ENVIROMENTID`) REFERENCES `enviroment` (`ENVIROMENTID`),
  ADD CONSTRAINT `FK_FACULTY__FACULTY_E_FACULTY` FOREIGN KEY (`FACULTYID`) REFERENCES `faculty` (`FACULTYID`);

--
-- Filtros para la tabla `faculty_resource`
--
ALTER TABLE `faculty_resource`
  ADD CONSTRAINT `FK_FACULTY__FACULTY_R_FACULTY` FOREIGN KEY (`FACULTYID`) REFERENCES `faculty` (`FACULTYID`),
  ADD CONSTRAINT `FK_FACULTY__FACULTY_R_RESOURCE` FOREIGN KEY (`RESOURCEID`) REFERENCES `resourcet` (`RESOURCEID`);

--
-- Filtros para la tabla `groupt`
--
ALTER TABLE `groupt`
  ADD CONSTRAINT `FK_GROUP_ACADEMICP_ACADEMIC` FOREIGN KEY (`ACADEMICPERIDODID`) REFERENCES `academicperiod` (`ACADEMICPERIDODID`),
  ADD CONSTRAINT `FK_GROUP_SUBJECT_G_SUBJECT` FOREIGN KEY (`IDSUBJECT`) REFERENCES `subject` (`IDSUBJECT`);

--
-- Filtros para la tabla `hourlyassignment`
--
ALTER TABLE `hourlyassignment`
  ADD CONSTRAINT `FK_HOURLYAS_ACADEMICP_ACADEMIC` FOREIGN KEY (`ACADEMICPERIDODID`) REFERENCES `academicperiod` (`ACADEMICPERIDODID`),
  ADD CONSTRAINT `FK_HOURLYAS_DEPARTAME_DEPARTME` FOREIGN KEY (`DEPARTMENTID`) REFERENCES `department` (`DEPARTMENTID`),
  ADD CONSTRAINT `FK_HOURLYAS_TEACHER_H_TEACHER` FOREIGN KEY (`TEACHERID`) REFERENCES `teacher` (`TEACHERID`);

--
-- Filtros para la tabla `program`
--
ALTER TABLE `program`
  ADD CONSTRAINT `FK_PROGRAM_DEPARTMEN_DEPARTME` FOREIGN KEY (`DEPARTMENTID`) REFERENCES `department` (`DEPARTMENTID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
