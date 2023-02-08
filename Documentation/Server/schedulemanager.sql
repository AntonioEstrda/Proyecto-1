-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3307
-- Tiempo de generación: 08-02-2023 a las 14:43:33
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

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

DELIMITER $$
--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `academicHoursTeacher` (`teacherId` INT) RETURNS INT(11) DETERMINISTIC BEGIN
	DECLARE vHours int; 
    SET vHours = 0; 
	WITH Consulta as ( 
        SELECT TC.TEACHERID as Tchr, TIME_TO_SEC(TIMEDIFF(SH.ENDTIME, SH.STARTIME))/3600 as Hours 
        FROM `schedule` SH  
            INNER JOIN groupt GP ON SH.IDGROUP = GP.IDGROUP 
            INNER JOIN teacher_group TG ON TG.IDGROUP = GP.IDGROUP 
            INNER JOIN teacher TC ON  TC.TEACHERID = TG.TEACHERID 
            INNER JOIN academicperiod AP ON  AP.ACADEMICPERIDODID = GP.ACADEMICPERIDODID
        WHERE TC.ISDISABLE = 0 
            AND AP.isDisable = 0
        	AND GP.ACADEMICPERIDODID = getCrrntAcdPer() 
        	AND SH.TYPE = 'ACADEMICO'
    		AND TC.TEACHERID = 2
)SELECT sum(Hours) into vHours FROM CONSULTA;
RETURN vHours; 	
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `AssigSbjtGpsHours` (`groupId` INT, `call_to_action` VARCHAR(100)) RETURNS INT(11)  BEGIN 
	DECLARE hoursT double; 
    SET hoursT = 0;  
    IF call_to_action = 'ACADEMICO' THEN  
        WITH consulta as ( 
            SELECT GP.IDGROUP, CAST(SUM(TIME_TO_SEC(TIMEDIFF(SH.ENDTIME, SH.STARTIME))/3600) as INT) as TotalHours
            FROM `schedule` SH 
            	INNER JOIN  groupt GP ON GP.IDGROUP = SH.IDGROUP
            WHERE GP.ACADEMICPERIDODID = getCrrntAcdPer()
            	AND SH.TYPE = 'ACADEMICO'
            GROUP BY  GP.IDGROUP 	
        ) SELECT TotalHours into hoursT FROM  consulta WHERE IDGROUP = groupId;  
	ELSEIF call_to_action = 'PRESTAMO_POR_MATERIA' THEN 
    	WITH consulta as ( 
             SELECT GP.IDGROUP, CAST(SUM(TIME_TO_SEC(TIMEDIFF(SH.ENDTIME, SH.STARTIME))/3600) as INT) as TotalHours 
             FROM `schedule` SH 
                INNER JOIN `event` EV ON SH.eventId = EV.id 
                INNER JOIN groupt GP ON  SH.IDGROUP = GP.IDGROUP
             WHERE EV.academicPeriodId = getCrrntAcdPer()
             AND SH.TYPE = 'EVENTO'
             AND EV.type = 'PRESTAMO_POR_MATERIA'
             GROUP BY  GP.IDGROUP 	
		) SELECT TotalHours into hoursT FROM  consulta WHERE IDGROUP = groupId;
    END IF; 
    RETURN hoursT; 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `CrossSubjectCOnUpd` (`vday` VARCHAR(50), `groupId` INT, `startime` TIME, `endtime` TIME, `schId` INT) RETURNS INT(11) DETERMINISTIC BEGIN 
DECLARE ban int DEFAULT 0; 
DECLARE progId int DEFAULT -1;  
DECLARE sem int DEFAULT -1;  
WITH consProgram as (
	SELECT distinct SB.IDPROGRAM as programId FROM groupt G 
    INNER JOIN subject SB  ON SB.IDSUBJECT = G.IDSUBJECT 
    INNER JOIN program PG ON PG.IDPROGRAM = SB.IDPROGRAM
    WHERE G.IDGROUP = groupId
)SELECT programId into progId FROM consProgram;  

WITH subjectSemester as (
        SELECT DISTINCT S.SEMESTER FROM groupt G 
        INNER JOIN subject S ON  G.IDSUBJECT = S.IDSUBJECT 
        WHERE G.IDGROUP = groupId 
)SELECT SEMESTER into sem FROM subjectSemester;  

if schId is  not null then  
    WITH academicSchedule as (
        SELECT S.* FROM `schedule` S 
            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
            INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
            INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer()
            AND S.`IDSCHEDEULE` != schId 
            AND S.DAYS = vday AND S.TYPE = 'ACADEMICO' 
            AND SB.SEMESTER = sem
            AND PG.IDPROGRAM = progId
    ), eventSchedule as (
        SELECT S.* FROM `schedule` S 
            INNER JOIN  `event` EV ON  EV.id = S.eventId
            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
            INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
            INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
        WHERE EV.type = 'PRESTAMO_ACADEMICO'
            AND EV.academicPeriodId = getCrrntAcdPer() 
            AND S.`IDSCHEDEULE` != schId 
            AND S.DAYS = vday AND S.TYPE = 'EVENTO' 
            AND SB.SEMESTER = sem
            AND PG.IDPROGRAM = progId
    ), consulta as (
      SELECT * FROM  academicSchedule
        UNION  
      SELECT * FROM  eventSchedule
    ) SELECT COUNT(*) into ban FROM consulta S WHERE  
        (( TIME(startime) >= TIME(S.STARTIME) and TIME(startime) < TIME(S.ENDTIME)) 
            OR  ( TIME(endtime) > TIME(S.STARTIME) and TIME(endtime) <= TIME(S.ENDTIME))
            OR ( TIME(startime) <= TIME(S.STARTIME) and TIME(endtime) >= TIME(S.ENDTIME)));
ELSE 
	WITH academicSchedule as (
    SELECT S.* FROM `schedule` S 
        INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
        INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
        INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
    WHERE G.ACADEMICPERIDODID = getCrrntAcdPer()
        AND S.DAYS = vday AND S.TYPE = 'ACADEMICO' 
        AND SB.SEMESTER = sem
        AND PG.IDPROGRAM = progId
), eventSchedule as (
	SELECT S.* FROM `schedule` S 
        INNER JOIN  `event` EV ON  EV.id = S.eventId
    	INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
        INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
        INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
    WHERE EV.type = 'PRESTAMO_ACADEMICO'
        AND EV.academicPeriodId = getCrrntAcdPer() 
        AND S.DAYS = vday AND S.TYPE = 'EVENTO' 
        AND SB.SEMESTER = sem
        AND PG.IDPROGRAM = progId
), consulta as (
  SELECT * FROM  academicSchedule
    UNION  
  SELECT * FROM  eventSchedule
) SELECT COUNT(*) into ban FROM consulta S WHERE  
	(( TIME(startime) >= TIME(S.STARTIME) and TIME(startime) < TIME(S.ENDTIME)) 
        OR  ( TIME(endtime) > TIME(S.STARTIME) and TIME(endtime) <= TIME(S.ENDTIME))
        OR ( TIME(startime) <= TIME(S.STARTIME) and TIME(endtime) >= TIME(S.ENDTIME)));
END IF;  

RETURN ban; 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `existAssigmentScheudleDpto` (`schId` INT, `dptid` INT) RETURNS INT(11) DETERMINISTIC BEGIN 
	DECLARE ban int DEFAULT 0;  
    WITH academicSchedule as (
    	SELECT S.* FROM `schedule` S
        INNER JOIN  groupt G on G.IDGROUP = S.IDGROUP
    	INNER JOIN  subject SB ON  G.IDSUBJECT =SB.IDSUBJECT
    	INNER JOIN  program P ON P.IDPROGRAM = SB.IDPROGRAM 
    	INNER JOIN 	department DP ON  DP.DEPARTMENTID = P.DEPARTMENTID 
        WHERE S.TYPE = 'ACADEMICO' 
    		AND DP.DEPARTMENTID = dptId  
    		AND S.IDSCHEDEULE = schId  
        AND G.ACADEMICPERIDODID = getCrrntAcdPer() 
    ), eventSchedule as (
	    SELECT S.* FROM `schedule` S 
        INNER JOIN  `event` e on e.id = S.eventId 
        INNER JOIN  academicperiod AP ON e.academicPeriodId = AP.ACADEMICPERIDODID
        WHERE S.TYPE = 'EVENTO' 
        	AND S.IDSCHEDEULE = schId 
        	AND e.departmentId = dptId
        AND AP.ACADEMICPERIDODID = getCrrntAcdPer() 
    ), schedules as (
    	SELECT * FROM academicSchedule 
        UNION 
        SELECT * FROM eventSchedule
    )SELECT count(*) into ban FROM  schedules S; 
	return ban;  
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `existsAssigmentOnUpd` (`vday` VARCHAR(50), `groupId` INT, `schId` INT) RETURNS INT(11) DETERMINISTIC BEGIN 
DECLARE ban int DEFAULT 0;
DECLARE progId int DEFAULT -1;  
WITH consProgram as (
	SELECT SB.IDPROGRAM as programId FROM groupt G 
    INNER JOIN subject SB  ON SB.IDSUBJECT = G.IDSUBJECT 
    INNER JOIN program PG ON PG.IDPROGRAM = SB.IDPROGRAM
    WHERE G.IDGROUP = groupId
)SELECT programId into progId FROM consProgram;  

if schId is not null then  
    WITH academicSchedule as (
        SELECT S.* FROM `schedule` S 
            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
            INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
            INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer()
        	AND S.IDGROUP = groupId
            AND S.`IDSCHEDEULE` != schId 
            AND S.DAYS = vday AND S.TYPE = 'ACADEMICO' 
            AND PG.IDPROGRAM = progId
    ), eventSchedule as (
        SELECT S.* FROM `schedule` S 
            INNER JOIN  `event` EV ON  EV.id = S.eventId
            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
            INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
            INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
        WHERE EV.type = 'PRESTAMO_ACADEMICO'
            AND EV.academicPeriodId = getCrrntAcdPer() 
        	AND G.ACADEMICPERIDODID = EV.academicPeriodId
            AND S.`IDSCHEDEULE` != schId 
            AND S.DAYS = vday AND S.TYPE = 'EVENTO' 
            AND PG.IDPROGRAM = progId
        	AND S.IDGROUP = groupId
    ), consulta as (
      SELECT * FROM  academicSchedule
        UNION  
      SELECT * FROM  eventSchedule
    ) SELECT COUNT(*) into ban FROM consulta S;   
else 
    WITH academicSchedule as (
        SELECT S.* FROM `schedule` S 
            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
            INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
            INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
        WHERE G.ACADEMICPERIDODID = getCrrntAcdPer()
        	AND S.IDGROUP = groupId
            AND S.DAYS = vday AND S.TYPE = 'ACADEMICO' 
            AND PG.IDPROGRAM = progId
    ), eventSchedule as (
        SELECT S.* FROM `schedule` S 
            INNER JOIN  `event` EV ON  EV.id = S.eventId
            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP
            INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT
            INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM
        WHERE EV.type = 'PRESTAMO_ACADEMICO'
            AND EV.academicPeriodId = getCrrntAcdPer() 
        	AND G.ACADEMICPERIDODID = EV.academicPeriodId
            AND S.DAYS = vday AND S.TYPE = 'EVENTO' 
            AND PG.IDPROGRAM = progId
        	AND S.IDGROUP = groupId
    ), consulta as (
      SELECT * FROM  academicSchedule
        UNION  
      SELECT * FROM  eventSchedule
    ) SELECT COUNT(*) into ban FROM consulta S;   

end if; 
RETURN ban; 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getCrrntAcdPer` () RETURNS INT(11) DETERMINISTIC BEGIN
 DECLARE apId int;
  Select academicperiod.ACADEMICPERIDODID into apId 
  FROM academicperiod 
  WHERE academicperiod.isDisable = 0 ORDER BY academicperiod.INITDATE DESC LIMIT 1;
  
  RETURN apId;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `globalType` (`idType` INT) RETURNS INT(11)  BEGIN
    	DECLARE var_2 int;  
        SET var_2 = -1; 
        WITH RECURSIVE globalType(id, parentId) AS  
                (  
                SELECT RT.RESSOURCETYPEID, RT.RES_RESSOURCETYPEID FROM resourcetype RT WHERE RESSOURCETYPEID = idType
                union all  
                SELECT RT2.RESSOURCETYPEID , RT2.RES_RESSOURCETYPEID from globalType GT, resourcetype RT2 WHERE GT.parentId = RT2.RESSOURCETYPEID
                )SELECT DISTINCT id into var_2 FROM globalType WHERE ParentId is null; 
        RETURN var_2; 
    END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `permitEvents` (`dptId` INT) RETURNS INT(11) DETERMINISTIC BEGIN  

	DECLARE totalSubGrop int DEFAULT 0; 
    DECLARE assgs int DEFAULT 0; 
    DECLARE facId int DEFAULT -1; 
    WITH facultyProgram as (
        SELECT F.FACULTYID FROM program P 
        INNER JOIN  department D ON P.DEPARTMENTID = D.DEPARTMENTID
        INNER JOIN  faculty F ON  F.FACULTYID = D.FACULTYID
        WHERE D.DEPARTMENTID = dptId
    )SELECT FACULTYID into facId from facultyProgram;  
    
    WITH totalSubjectGroups as 
    (
    	SELECT OIA.FACULTYID, count(*) as total FROM overviewIntensityAssignedHours OIA
        WHERE OIA.FACULTYID = facId
		GROUP BY  OIA.FACULTYID
    )SELECT total into totalSubGrop FROM totalSubjectGroups; 
    
	
	WITH assigneds as (
        SELECT OIA.FACULTYID, SUM(CASE WHEN OIA.INTENSITY = OIA.AssignedHours THEN 1 ELSE 0 END)  AS AssigAchievement 
        FROM overviewIntensityAssignedHours OIA 
        WHERE OIA.FACULTYID = facId
        GROUP BY OIA.FACULTYID
   	)SELECT AssigAchievement into assgs FROM assigneds; 
     
    IF facId = -1 OR totalSubGrop <> assgs OR (totalSubGrop = 0 and assgs = 0) THEN 
    	RETURN 0; 
    ELSE 
    	RETURN 1;  
    END IF; 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `validateAssignmentEnvDpoGro` (`dptId` INT, `groupId` INT, `envId` INT) RETURNS INT(11) DETERMINISTIC BEGIN 
	DECLARE facres int DEFAULT -1; 
    DECLARE facprogGroup int DEFAULT -1; 
    
	WITH facultyResource as ( 
        SELECT FR.FACULTYID, RT.RESOURCEID FROM  faculty_resource FR 
        INNER JOIN  resourcet RT ON  FR.RESOURCEID = RT.RESOURCEID
        WHERE FR.isDisable = 0
        AND RT.ISDISABLE = 0 
    )SELECT FACULTYID into facres FROM facultyResource 
    	WHERE RESOURCEID = envId;  
    
    IF groupId IS NOT NULL THEN 
    	SELECT DISTINCT OIA.FACULTYID into facprogGroup  FROM overviewintensityassignedhours OIA
        WHERE OIA.DEPARTMENTID = dptId AND OIA.IDGROUP = groupId;  
    ELSE 
    	SELECT DISTINCT OIA.FACULTYID into facprogGroup  FROM overviewintensityassignedhours OIA
        WHERE OIA.DEPARTMENTID = dptId;  
    END IF; 
    
    IF facprogGroup <> -1 and facres <> -1 and facprogGroup = facres THEN 
    	RETURN 1;  
    ELSE  
    	RETURN 0;  
    END IF;  
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `validateAssOverFaculty` (`envId` INT, `groupId` INT) RETURNS INT(11) DETERMINISTIC BEGIN
	DECLARE facres int DEFAULT null;  
    DECLARE facGrp int DEFAULT null;  
    DECLARE ban int DEFAULT 0;  
    
    WITH GruposProgramaFacultad as ( 
        SELECT F.FACULTYID, D.DEPARTMENTID, P.IDPROGRAM, G.IDGROUP FROM groupt G 
        INNER JOIN  subject S  ON G.IDSUBJECT = S.IDSUBJECT
        INNER JOIN  program P ON  S.IDPROGRAM = P.IDPROGRAM 
        INNER JOIN  department D ON  D.DEPARTMENTID = P.DEPARTMENTID
        INNER JOIN  faculty F on F.FACULTYID = D.FACULTYID
    )SELECT DISTINCT GPF.FACULTYID into facGrp FROM GruposProgramaFacultad GPF WHERE GPF.IDGROUP = groupId;

    WITH recursoPrograma as ( SELECT RS.RESOURCEID, FS.FACULTYID FROM resourcet RS  
        INNER JOIN  faculty_resource FS ON FS.RESOURCEID = RS.RESOURCEID 
        WHERE FS.isDisable = 0 
        AND RS.ISDISABLE = 0
    )SELECT DISTINCT RP.FACULTYID into facres FROM recursoPrograma RP WHERE RP.RESOURCEID = envId; 
	
    IF facres is not null  and facGrp is not null and 
    	(facres = facGrp) THEN  
       SET ban = 1;  
	END IF; 
    return ban;  
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `validateGroupProgram` (`schId` INT, `type` VARCHAR(50), `progIdEx` INT) RETURNS INT(11) DETERMINISTIC BEGIN                                
	DECLARE ban int DEFAULT 0;  
    DECLARE progId int DEFAULT -1;
	IF type = 'ACADEMICO' THEN 
        SELECT DISTINCT PG.IDPROGRAM into progId FROM `schedule` S 
            INNER JOIN groupt G ON S.IDGROUP = G.IDGROUP 
            INNER JOIN subject SB ON  SB.IDSUBJECT = G.IDSUBJECT 
            INNER JOIN program PG ON  PG.IDPROGRAM = SB.IDPROGRAM 
        WHERE S.IDSCHEDEULE = schId AND S.TYPE = type;  
	ELSEIF type = 'EVENTO' THEN 
        SELECT DISTINCT PG.IDPROGRAM into progId FROM `schedule` S 
        INNER JOIN `event` EV ON  S.eventId = EV.id 
        INNER JOIN program PG ON  PG.IDPROGRAM = EV.programId 
        WHERE S.IDSCHEDEULE = schId; 
    END IF; 
    
    IF progIdEx = progId THEN 
    	SET ban = 1;  
    END IF; 
    
    return ban; 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `validateHourAssignment` (`groupId` INT, `crrtime` INT, `uptTime` INT, `call_to_action` VARCHAR(50)) RETURNS INT(11) DETERMINISTIC BEGIN 
	DECLARE ban int DEFAULT 0; 
    WITH consulta as (
        SELECT * FROM  intensitysubjectgroup WHERE IDGROUP = groupId
    )
    SELECT CASE WHEN AssigSbjtGpsHours(groupId, call_to_action) + uptTime - crrTime  
    	<=  (SELECT INTENSITY FROM consulta) then 0 else 1 end into ban; 
    return ban; 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `validateScheduleAssignmentOnUpd` (`envId` INT, `startime` TIME, `endtime` TIME, `days` VARCHAR(50), `schId` INT) RETURNS INT(11) DETERMINISTIC BEGIN
DECLARE coincidences int;  
SET coincidences = 0;     
IF schId is null then  
	WITH academicSchedule as (
    	SELECT S.* FROM `schedule` S
        INNER JOIN  groupt G on G.IDGROUP = S.IDGROUP
        INNER JOIN  academicperiod AP ON g.ACADEMICPERIDODID = AP.ACADEMICPERIDODID
        WHERE S.TYPE = 'ACADEMICO' 
        AND AP.ACADEMICPERIDODID = getCrrntAcdPer() 
    ), eventSchedule as (
	    SELECT S.* FROM `schedule` S 
        INNER JOIN  `event` e on e.id = S.eventId 
        INNER JOIN  academicperiod AP ON e.academicPeriodId = AP.ACADEMICPERIDODID
        WHERE S.TYPE = 'EVENTO' 
        AND AP.ACADEMICPERIDODID = getCrrntAcdPer() 
    ), schedules as (
    	SELECT * FROM academicSchedule 
        UNION 
        SELECT * FROM eventSchedule
    )
    SELECT distinct count(S.IDSCHEDEULE) into coincidences FROM schedules S 
    WHERE S.resourceId = envId 
    AND S.DAYS = days
    AND (( TIME(startime) >= TIME(S.STARTIME) and TIME(startime) < TIME(S.ENDTIME)) 
    OR  ( TIME(endtime) > TIME(S.STARTIME) and TIME(endtime) <= TIME(S.ENDTIME))
    OR ( TIME(startime) <= TIME(S.STARTIME) and TIME(endtime) >= TIME(S.ENDTIME)));  
ELSE 
	WITH academicSchedule as (
    	SELECT S.* FROM `schedule` S
        INNER JOIN  groupt G on G.IDGROUP = S.IDGROUP
        INNER JOIN  academicperiod AP ON g.ACADEMICPERIDODID = AP.ACADEMICPERIDODID
        WHERE S.TYPE = 'ACADEMICO' 
        AND AP.ACADEMICPERIDODID = getCrrntAcdPer() 
    ), eventSchedule as (
	    SELECT S.* FROM `schedule` S 
        INNER JOIN  `event` e on e.id = S.eventId 
        INNER JOIN  academicperiod AP ON e.academicPeriodId = AP.ACADEMICPERIDODID
        WHERE S.TYPE = 'EVENTO' 
        AND AP.ACADEMICPERIDODID = getCrrntAcdPer() 
    ), schedules as (
    	SELECT * FROM academicSchedule 
        UNION 
        SELECT * FROM eventSchedule
    )
    SELECT distinct count(S.IDSCHEDEULE) into coincidences FROM schedules S 
    WHERE S.resourceId = envId 
    AND S.DAYS = days
    AND S.IDSCHEDEULE != schId
    AND (( TIME(startime) >= TIME(S.STARTIME) and TIME(startime) < TIME(S.ENDTIME)) 
    OR  ( TIME(endtime) > TIME(S.STARTIME) and TIME(endtime) <= TIME(S.ENDTIME))
    OR ( TIME(startime) <= TIME(S.STARTIME) and TIME(endtime) >= TIME(S.ENDTIME)));  
END IF; 
    return coincidences;  
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `academicperiod`
--

CREATE TABLE `academicperiod` (
  `ACADEMICPERIDODID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `INITDATE` date NOT NULL,
  `FINALDATE` date NOT NULL,
  `isDisable` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `academicperiod`
--

INSERT INTO `academicperiod` (`ACADEMICPERIDODID`, `NAME`, `INITDATE`, `FINALDATE`, `isDisable`) VALUES
(2, 'Periodo 2022-2', '2022-05-19', '2022-12-31', 0),
(3, 'Periodo 2023-1', '2023-03-01', '2023-06-30', 1),
(4, 'Periodo 2021-1', '2021-03-01', '2021-06-30', 1),
(5, 'Periodo 2021-2', '2021-09-01', '2021-12-19', 1),
(6, 'Periodo 2020-1', '2020-03-01', '2020-06-30', 1),
(7, 'Periodo 2022-1', '2022-01-01', '2022-05-18', 1),
(8, 'Periodo 2020-2', '2020-09-01', '2020-12-19', 1),
(9, 'Periodo 2019-1', '2019-03-01', '2019-06-30', 1),
(10, 'Periodo 2019-2', '2019-09-01', '2019-12-19', 1),
(11, 'Periodo 2018-1', '2018-03-01', '2018-06-30', 1),
(12, 'Periodo 2018-2', '2019-09-01', '2019-12-19', 1);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `assertassignments`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `assertassignments` (
`envId` int(11)
,`resId` int(11)
,`typeID` int(11)
,`FACULTYID` int(11)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `assignmentresource`
--

CREATE TABLE `assignmentresource` (
  `id` int(11) NOT NULL,
  `registerdate` date NOT NULL,
  `finaldate` date DEFAULT NULL,
  `isDisable` tinyint(1) NOT NULL DEFAULT 0,
  `envId` int(11) NOT NULL,
  `resId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `assignmentresource`
--

INSERT INTO `assignmentresource` (`id`, `registerdate`, `finaldate`, `isDisable`, `envId`, `resId`) VALUES
(3, '2022-12-01', '2022-12-08', 0, 6, 5),
(5, '2022-12-03', '2022-12-08', 1, 6, 3),
(7, '2022-12-03', '2022-11-02', 0, 8, 3),
(8, '2022-12-04', '2022-11-02', 1, 6, 7),
(10, '2022-12-03', NULL, 0, 8, 7);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `assingmentschedulehours`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `assingmentschedulehours` (
`DEPARTMENTID` int(11)
,`IDPROGRAM` int(11)
,`IDSUBJECT` int(11)
,`IDGROUP` int(11)
,`IDSCHEDEULE` int(11)
,`TimeAsg` decimal(20,4)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `department`
--

CREATE TABLE `department` (
  `DEPARTMENTID` int(11) NOT NULL,
  `FACULTYID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `code` varchar(25) NOT NULL,
  `location` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `department`
--

INSERT INTO `department` (`DEPARTMENTID`, `FACULTYID`, `NAME`, `code`, `location`) VALUES
(1, 16, 'Sistemas', 'DEPT1', 2),
(41, 51, 'Español y literatura', 'DEPT2', 66),
(42, 52, 'Física', 'DEPT3', 61),
(43, 53, 'Medicina', 'DEPT4', 63),
(44, 54, 'Administración', 'DEPT5', 61),
(45, 55, 'Derecho', 'DEPT6', 66),
(46, 52, 'Matemática', 'DEPT7', 61),
(47, 16, 'Electrónica', 'DEPT8', 2),
(48, 16, 'Telemática', 'DEPT9', 2),
(49, 16, 'Automática Industrial', 'DEPT10', 2),
(50, 7, 'Agroindustrial', 'DEPT11', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(150) NOT NULL,
  `Code` varchar(10) NOT NULL,
  `type` enum('GENERAL','PRESTAMO_POR_MATERIA','ACADEMICO') NOT NULL,
  `teacherId` int(11) NOT NULL,
  `academicPeriodId` int(11) NOT NULL,
  `departmentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faculty`
--

CREATE TABLE `faculty` (
  `FACULTYID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `locId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `faculty`
--

INSERT INTO `faculty` (`FACULTYID`, `NAME`, `locId`) VALUES
(7, 'Agrarias', 2),
(16, 'FIET', 2),
(26, 'Artes', 2),
(27, 'Nueva sede', 2),
(51, 'Ciencias Humanas (antiguo ed)', 66),
(52, 'FACNED', 61),
(53, 'Ciencias de la salud', 63),
(54, 'Ciencias Contables', 61),
(55, 'Ciencias Políticas', 66),
(56, 'Ingeniería civil', 61);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `faculty_resource`
--

CREATE TABLE `faculty_resource` (
  `FAC_AND_RES_ID` int(11) NOT NULL,
  `FACULTYID` int(11) NOT NULL,
  `RESOURCEID` int(11) NOT NULL,
  `REGISTERDATE` date NOT NULL,
  `FINALDATE` date DEFAULT NULL,
  `isDisable` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `faculty_resource`
--

INSERT INTO `faculty_resource` (`FAC_AND_RES_ID`, `FACULTYID`, `RESOURCEID`, `REGISTERDATE`, `FINALDATE`, `isDisable`) VALUES
(0, 16, 6, '2022-11-30', '2022-12-06', 1),
(2, 16, 3, '2022-11-01', NULL, 0),
(3, 16, 4, '2022-11-01', NULL, 0),
(7, 16, 8, '2022-12-02', '2022-12-03', 1),
(8, 16, 7, '2022-12-02', '2022-12-03', 1),
(9, 16, 8, '2022-12-03', NULL, 0),
(10, 16, 7, '2022-12-03', NULL, 0),
(11, 16, 9, '2022-12-08', NULL, 0);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `groupt`
--

INSERT INTO `groupt` (`IDGROUP`, `IDSUBJECT`, `ACADEMICPERIDODID`, `CAPACITY`, `NAME`) VALUES
(4, 6, 2, 20, 'A'),
(5, 6, 2, 20, 'B'),
(6, 7, 2, 30, 'A'),
(11, 21, 3, 20, 'A'),
(12, 22, 3, 25, 'A'),
(13, 21, 3, 20, 'B'),
(14, 22, 3, 25, 'B'),
(15, 23, 3, 30, 'A'),
(16, 23, 3, 30, 'B'),
(17, 26, 3, 20, 'A'),
(18, 26, 3, 20, 'B'),
(19, 27, 3, 35, 'A'),
(20, 29, 3, 30, 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hourlyassignment`
--

CREATE TABLE `hourlyassignment` (
  `vinculationId` int(11) NOT NULL,
  `DEPARTMENTID` int(11) NOT NULL,
  `TEACHERID` int(11) NOT NULL,
  `HOURS` int(2) NOT NULL,
  `VINCULATIONTYPE` enum('NOMBRADOTIEMPOCOMPLETO','NOMBRADOMEDIOTIEMPO','OCASIONALTIEMPOCOMPLETO','OCASIONALMEDIOTIEMPO','HORACATEDRA','BECARIO') NOT NULL,
  `isDisable` tinyint(1) NOT NULL DEFAULT 0,
  `initialdate` date NOT NULL,
  `finaldate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hourlyassignment`
--

INSERT INTO `hourlyassignment` (`vinculationId`, `DEPARTMENTID`, `TEACHERID`, `HOURS`, `VINCULATIONTYPE`, `isDisable`, `initialdate`, `finaldate`) VALUES
(3, 1, 2, 30, 'HORACATEDRA', 0, '2022-12-01', NULL),
(4, 1, 3, 40, 'NOMBRADOTIEMPOCOMPLETO', 0, '2022-12-01', NULL);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `intensitysubjectgroup`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `intensitysubjectgroup` (
`FACULTYID` int(11)
,`DEPARTMENTID` int(11)
,`IDPROGRAM` int(11)
,`IDSUBJECT` int(11)
,`IDGROUP` int(11)
,`INTENSITY` int(2)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `Name` text NOT NULL,
  `city` varchar(50) NOT NULL,
  `parentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `location`
--

INSERT INTO `location` (`id`, `Name`, `city`, `parentId`) VALUES
(2, 'Sede principal', '', NULL),
(3, 'Edificio 2', '', 2),
(4, 'El Carmen', '', NULL),
(61, 'Campus Tulcán', '', NULL),
(62, 'Claustro de Santo Domingo', '', NULL),
(63, 'Sector La Estancia', '', NULL),
(64, 'Las Guacas', '', NULL),
(65, 'Casa Rosada', '', NULL),
(66, 'Sector Histórico', '', NULL);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `overviewintensityassignedhours`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `overviewintensityassignedhours` (
`FACULTYID` int(11)
,`DEPARTMENTID` int(11)
,`IDPROGRAM` int(11)
,`IDSUBJECT` int(11)
,`IDGROUP` int(11)
,`INTENSITY` int(2)
,`AssignedHours` int(11)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `program`
--

CREATE TABLE `program` (
  `IDPROGRAM` int(11) NOT NULL,
  `DEPARTMENTID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `code` varchar(25) NOT NULL,
  `location` int(100) NOT NULL,
  `color` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `program`
--

INSERT INTO `program` (`IDPROGRAM`, `DEPARTMENTID`, `NAME`, `code`, `location`, `color`) VALUES
(7, 1, 'Sistemas', 'PRG1', 3, ''),
(31, 41, 'Licenciatura en Español y Literatura', 'PRG2', 62, ''),
(32, 41, 'Licenciatura en básica con énfasis en Español', 'PRG3', 62, ''),
(33, 42, 'Ingeniería física', 'PRG4', 61, ''),
(34, 43, 'Medicina', 'PRG5', 63, ''),
(35, 44, 'Contaduría', 'PRG6', 61, ''),
(36, 46, 'Licenciatura en Matemáticas', 'PRG7', 61, ''),
(37, 50, 'Ingeniería forestal', 'PRG8', 64, ''),
(38, 49, 'Ingeniería automática', 'PRG9', 61, ''),
(39, 47, 'Ingeniería electrónica', 'PRG10', 61, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resourcet`
--

CREATE TABLE `resourcet` (
  `RESOURCEID` int(11) NOT NULL,
  `RESOURCETYPEID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL DEFAULT 0,
  `code` varchar(25) NOT NULL,
  `location` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `resourcet`
--

INSERT INTO `resourcet` (`RESOURCEID`, `RESOURCETYPEID`, `NAME`, `DESCRIPTION`, `ISDISABLE`, `code`, `location`, `capacity`, `number`) VALUES
(3, 8, 'Computador', 'Computador', 0, '4567894', NULL, NULL, NULL),
(4, 7, 'Salón de telemática', 'Salón de telemática', 0, 'TEL', NULL, 30, 101),
(5, 8, 'Computador2', 'Computador2', 0, 'SISCOM', NULL, NULL, 101),
(6, 5, 'Salon', 'Salon', 1, 'SISCOM', 3, 45, 102),
(7, 10, 'Tablero', 'Tablero', 0, 'AGR', NULL, NULL, 456),
(8, 7, 'SALON', 'SALON', 0, 'SIS', NULL, 40, 303),
(9, 5, 'Salon', 'Salon', 1, 'SISCON', 3, 45, 105);

--
-- Disparadores `resourcet`
--
DELIMITER $$
CREATE TRIGGER `upd_IsDisable` BEFORE UPDATE ON `resourcet` FOR EACH ROW BEGIN
	DECLARE gtype int;  
   	IF NEW.isDisable = 1 THEN 
    	SELECT globalType(NEW.RESOURCETYPEID) into gtype;
        IF  gtype = 4 THEN   
        	UPDATE assignmentresource SET isDisable = 1, finaldate = CURRENT_DATE() 
            WHERE envId = NEW.RESOURCEID and isDisable = 0; 
        
        ELSE 
        	UPDATE assignmentresource SET isDisable = 1, finaldate = CURRENT_DATE() 
            WHERE resId = NEW.RESOURCEID and isDisable = 0;  
        END IF;  
    END IF; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resourcetype`
--

CREATE TABLE `resourcetype` (
  `RESSOURCETYPEID` int(11) NOT NULL,
  `RES_RESSOURCETYPEID` int(11) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `ISDISABLE` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `resourcetype`
--

INSERT INTO `resourcetype` (`RESSOURCETYPEID`, `RES_RESSOURCETYPEID`, `NAME`, `ISDISABLE`) VALUES
(4, NULL, 'Ambiente', 0),
(5, 4, 'SALA', 0),
(6, 4, 'AUDITORIO', 0),
(7, 4, 'SALON', 0),
(8, NULL, 'Computacional', 0),
(9, 7, 'SubTipo1', 0),
(10, 8, 'SubTipoComputacional', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'SCHEDULEMANAGER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `schedule`
--

CREATE TABLE `schedule` (
  `IDSCHEDEULE` int(11) NOT NULL,
  `IDGROUP` int(11) DEFAULT NULL,
  `eventId` int(11) DEFAULT NULL,
  `resourceId` int(11) NOT NULL,
  `TYPE` enum('ACADEMICO','EVENTO') NOT NULL,
  `DAYS` enum('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO') NOT NULL,
  `STARTIME` time NOT NULL,
  `ENDTIME` time NOT NULL,
  `initialdate` date NOT NULL,
  `finaldate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `schedule`
--

INSERT INTO `schedule` (`IDSCHEDEULE`, `IDGROUP`, `eventId`, `resourceId`, `TYPE`, `DAYS`, `STARTIME`, `ENDTIME`, `initialdate`, `finaldate`) VALUES
(13, 5, NULL, 4, 'ACADEMICO', 'LUNES', '07:00:00', '09:00:00', '2022-05-19', '2022-12-31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subject`
--

CREATE TABLE `subject` (
  `IDSUBJECT` int(11) NOT NULL,
  `IDPROGRAM` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `REQUISITS` varchar(200) NOT NULL,
  `SEMESTER` int(2) NOT NULL,
  `INTENSITY` int(2) NOT NULL,
  `Modality` enum('Semestral','Anual') NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL,
  `type` enum('TEORICA','PRACTICA','HIBRIDA','FISH') NOT NULL,
  `isExtern` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `subject`
--

INSERT INTO `subject` (`IDSUBJECT`, `IDPROGRAM`, `NAME`, `code`, `REQUISITS`, `SEMESTER`, `INTENSITY`, `Modality`, `ISDISABLE`, `type`, `isExtern`) VALUES
(6, 7, 'Redes', 'SIS101', '{\"7\":null}', 8, 2, 'Semestral', 0, 'TEORICA', 0),
(7, 7, 'IA', 'SIS104', '{\"7\":null}', 8, 8, 'Semestral', 0, 'TEORICA', 0),
(21, 33, 'Mecánica', 'FIS101', '{\"7\":null}', 2, 4, 'Semestral', 0, 'TEORICA', 0),
(22, 33, 'Electromagnetismo', 'FIS102', '{\"7\":null}', 3, 4, 'Semestral', 0, 'TEORICA', 0),
(23, 36, 'Cálculo I', 'MAT101', '{\"7\":null}', 1, 4, 'Semestral', 0, 'TEORICA', 0),
(24, 36, 'Cálculo II', 'MAT102', '{\"7\":null}', 2, 4, 'Semestral', 0, 'TEORICA', 0),
(25, 31, 'Lectura y escritura', 'ESP101', '{\"7\":null}', 1, 2, 'Semestral', 0, 'TEORICA', 0),
(26, 36, 'Álgebra', 'MAT103', '{\"7\":null}', 2, 4, 'Semestral', 0, 'TEORICA', 0),
(27, 7, 'Introducción a la ingeniería de sistemas', 'SIS-01', '{\"7\":null}', 1, 4, 'Semestral', 0, 'TEORICA', 0),
(28, 7, 'Informática I', 'SIS102', '{\"7\":null}', 1, 4, 'Semestral', 0, 'TEORICA', 0),
(29, 7, 'Programación OO', 'SIS103', '{\"7\":null}', 2, 4, 'Semestral', 0, 'TEORICA', 0),
(30, 7, 'Laboratorio POO', 'SIS104', '{\"7\":null}', 2, 2, 'Semestral', 0, 'TEORICA', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher`
--

CREATE TABLE `teacher` (
  `TEACHERID` int(11) NOT NULL,
  `FISRTSNAME` varchar(100) NOT NULL,
  `LASTNAME` varchar(100) NOT NULL,
  `NUMIDEN` varchar(50) NOT NULL,
  `ISDISABLE` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `teacher`
--

INSERT INTO `teacher` (`TEACHERID`, `FISRTSNAME`, `LASTNAME`, `NUMIDEN`, `ISDISABLE`) VALUES
(2, 'Hector', 'Dorado', '12345', 0),
(3, 'Carlos', 'Papas', '78945', 0);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `teacherdepartmentfacul`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `teacherdepartmentfacul` (
`FACULTYID` int(11)
,`DEPARTMENTID` int(11)
,`TEACHERID` int(11)
,`username` varchar(250)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher_group`
--

CREATE TABLE `teacher_group` (
  `TEAC_GRP_ID` int(11) NOT NULL,
  `TEACHERID` int(11) NOT NULL,
  `IDGROUP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `teacher_group`
--

INSERT INTO `teacher_group` (`TEAC_GRP_ID`, `TEACHERID`, `IDGROUP`) VALUES
(1, 3, 4),
(2, 2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `TeacherId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `TeacherId`) VALUES
(2, 'usuariotest@unicauca.edu.co', '$2a$10$iF.Q7qduG5KTOgqvNt0Bu.6L1wxBJicMjsdYKplEOYeP46.q9w9DS', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `userrol`
--

CREATE TABLE `userrol` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `rolId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `userrol`
--

INSERT INTO `userrol` (`id`, `userId`, `rolId`) VALUES
(1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vinculations`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vinculations` (
`TEACHERID` int(11)
,`HOURS` int(2)
,`VINCULATIONTYPE` enum('NOMBRADOTIEMPOCOMPLETO','NOMBRADOMEDIOTIEMPO','OCASIONALTIEMPOCOMPLETO','OCASIONALMEDIOTIEMPO','HORACATEDRA','BECARIO')
,`DEPARTMENTID` int(11)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `assertassignments`
--
DROP TABLE IF EXISTS `assertassignments`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `assertassignments`  AS SELECT DISTINCT `ar`.`envId` AS `envId`, `ar`.`resId` AS `resId`, (select `ra`.`RESOURCETYPEID` from `resourcet` `ra` where `ra`.`RESOURCEID` = `ar`.`resId`) AS `typeID`, `fr`.`FACULTYID` AS `FACULTYID` FROM ((`resourcet` `r` join `assignmentresource` `ar` on(`r`.`RESOURCEID` = `ar`.`envId`)) join `faculty_resource` `fr` on(`fr`.`RESOURCEID` = `r`.`RESOURCEID`)) WHERE `r`.`ISDISABLE` = 0 AND `ar`.`isDisable` = 0 AND `fr`.`isDisable` = 00  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `assingmentschedulehours`
--
DROP TABLE IF EXISTS `assingmentschedulehours`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `assingmentschedulehours`  AS SELECT `dp`.`DEPARTMENTID` AS `DEPARTMENTID`, `p`.`IDPROGRAM` AS `IDPROGRAM`, `s`.`IDSUBJECT` AS `IDSUBJECT`, `g`.`IDGROUP` AS `IDGROUP`, `sc`.`IDSCHEDEULE` AS `IDSCHEDEULE`, time_to_sec(timediff(`sc`.`ENDTIME`,`sc`.`STARTIME`)) / 3600 AS `TimeAsg` FROM ((((`schedule` `sc` join `groupt` `g` on(`sc`.`IDGROUP` = `g`.`IDGROUP`)) join `subject` `s` on(`g`.`IDSUBJECT` = `s`.`IDSUBJECT`)) join `program` `p` on(`p`.`IDPROGRAM` = `s`.`IDPROGRAM`)) join `department` `dp` on(`dp`.`DEPARTMENTID` = `p`.`DEPARTMENTID`)) WHERE `g`.`ACADEMICPERIDODID` = `getCrrntAcdPer`()  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `intensitysubjectgroup`
--
DROP TABLE IF EXISTS `intensitysubjectgroup`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `intensitysubjectgroup`  AS SELECT `f`.`FACULTYID` AS `FACULTYID`, `dp`.`DEPARTMENTID` AS `DEPARTMENTID`, `pg`.`IDPROGRAM` AS `IDPROGRAM`, `sb`.`IDSUBJECT` AS `IDSUBJECT`, `gp`.`IDGROUP` AS `IDGROUP`, `sb`.`INTENSITY` AS `INTENSITY` FROM ((((`faculty` `f` join `department` `dp` on(`dp`.`FACULTYID` = `f`.`FACULTYID`)) join `program` `pg` on(`pg`.`DEPARTMENTID` = `dp`.`DEPARTMENTID`)) join `subject` `sb` on(`sb`.`IDPROGRAM` = `pg`.`IDPROGRAM`)) join `groupt` `gp` on(`gp`.`IDSUBJECT` = `sb`.`IDSUBJECT`)) WHERE `gp`.`ACADEMICPERIDODID` = `getCrrntAcdPer`()  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `overviewintensityassignedhours`
--
DROP TABLE IF EXISTS `overviewintensityassignedhours`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `overviewintensityassignedhours`  AS SELECT `isg`.`FACULTYID` AS `FACULTYID`, `isg`.`DEPARTMENTID` AS `DEPARTMENTID`, `isg`.`IDPROGRAM` AS `IDPROGRAM`, `isg`.`IDSUBJECT` AS `IDSUBJECT`, `isg`.`IDGROUP` AS `IDGROUP`, `isg`.`INTENSITY` AS `INTENSITY`, CASE WHEN `sb`.`isExtern` = 1 THEN `AssigSbjtGpsHours`(`isg`.`IDGROUP`,'PRESTAMO_POR_MATERIA') ELSE `AssigSbjtGpsHours`(`isg`.`IDGROUP`,'ACADEMICO') END AS `AssignedHours` FROM (`intensitysubjectgroup` `isg` join `subject` `sb` on(`sb`.`IDSUBJECT` = `isg`.`IDSUBJECT`)) WHERE `sb`.`ISDISABLE` = 00  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `teacherdepartmentfacul`
--
DROP TABLE IF EXISTS `teacherdepartmentfacul`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `teacherdepartmentfacul`  AS SELECT DISTINCT `dp`.`FACULTYID` AS `FACULTYID`, `vc`.`DEPARTMENTID` AS `DEPARTMENTID`, `vc`.`TEACHERID` AS `TEACHERID`, `us`.`username` AS `username` FROM ((((`user` `us` join `teacher` `ti` on(`us`.`TeacherId` = `ti`.`TEACHERID`)) join `vinculations` `vc` on(`vc`.`TEACHERID` = `ti`.`TEACHERID`)) join `department` `dp` on(`vc`.`DEPARTMENTID` = `dp`.`DEPARTMENTID`)) join `faculty` `fa` on(`dp`.`FACULTYID` = `fa`.`FACULTYID`))  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vinculations`
--
DROP TABLE IF EXISTS `vinculations`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vinculations`  AS SELECT `tc`.`TEACHERID` AS `TEACHERID`, `ha`.`HOURS` AS `HOURS`, `ha`.`VINCULATIONTYPE` AS `VINCULATIONTYPE`, `dp`.`DEPARTMENTID` AS `DEPARTMENTID` FROM ((`teacher` `tc` join `hourlyassignment` `ha` on(`tc`.`TEACHERID` = `ha`.`TEACHERID`)) join `department` `dp` on(`ha`.`DEPARTMENTID` = `dp`.`DEPARTMENTID`)) WHERE `tc`.`ISDISABLE` = 0 AND `ha`.`isDisable` = 00  ;

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
-- Indices de la tabla `assignmentresource`
--
ALTER TABLE `assignmentresource`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_env_res` (`envId`),
  ADD KEY `fk_res_env` (`resId`);

--
-- Indices de la tabla `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`DEPARTMENTID`),
  ADD UNIQUE KEY `DEPARTMENT_PK` (`DEPARTMENTID`),
  ADD KEY `FACULTY_DEPARTMENT_FK` (`FACULTYID`),
  ADD KEY `fk_location_department` (`location`);

--
-- Indices de la tabla `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_pk_id` (`id`),
  ADD KEY `fk_teacher_event` (`teacherId`),
  ADD KEY `fk_academicperiod_event` (`academicPeriodId`),
  ADD KEY `fk_department_event` (`departmentId`);

--
-- Indices de la tabla `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`FACULTYID`),
  ADD UNIQUE KEY `FACULTY_PK` (`FACULTYID`),
  ADD KEY `fk_faculty_location` (`locId`);

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
  ADD PRIMARY KEY (`vinculationId`),
  ADD UNIQUE KEY `HOURLYASSIGNMENT_PK` (`vinculationId`),
  ADD KEY `DEPARTAMENT_HOURLYASSIGNMENT_FK` (`DEPARTMENTID`),
  ADD KEY `TEACHER_HOURLYASSIGNMENT_FK` (`TEACHERID`);

--
-- Indices de la tabla `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `fk_location_id` (`parentId`);

--
-- Indices de la tabla `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`IDPROGRAM`),
  ADD UNIQUE KEY `PROGRAM_PK` (`IDPROGRAM`),
  ADD KEY `DEPARTMENT_PROGRAM_FK` (`DEPARTMENTID`),
  ADD KEY `fk_location_program` (`location`);

--
-- Indices de la tabla `resourcet`
--
ALTER TABLE `resourcet`
  ADD PRIMARY KEY (`RESOURCEID`),
  ADD UNIQUE KEY `RESOURCE_PK` (`RESOURCEID`),
  ADD KEY `fk_resourcet_resourcetype` (`RESOURCETYPEID`),
  ADD KEY `fk_resource_location` (`location`);

--
-- Indices de la tabla `resourcetype`
--
ALTER TABLE `resourcetype`
  ADD PRIMARY KEY (`RESSOURCETYPEID`),
  ADD UNIQUE KEY `RESOURCETYPE_PK` (`RESSOURCETYPEID`),
  ADD KEY `IS_PARENT_FK` (`RES_RESSOURCETYPEID`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`IDSCHEDEULE`),
  ADD UNIQUE KEY `SCHEDULE_PK` (`IDSCHEDEULE`),
  ADD KEY `GROUP_SCHEDEULE_FK` (`IDGROUP`),
  ADD KEY `fk_schedule_res` (`resourceId`),
  ADD KEY `fk_schedule_event` (`eventId`);

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
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_pk_id` (`id`),
  ADD UNIQUE KEY `unique_pk_username` (`username`),
  ADD KEY `fk_teacher_user` (`TeacherId`);

--
-- Indices de la tabla `userrol`
--
ALTER TABLE `userrol`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_userrol` (`userId`),
  ADD KEY `fk_rol_userrol` (`rolId`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `academicperiod`
--
ALTER TABLE `academicperiod`
  MODIFY `ACADEMICPERIDODID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `assignmentresource`
--
ALTER TABLE `assignmentresource`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `department`
--
ALTER TABLE `department`
  MODIFY `DEPARTMENTID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `faculty`
--
ALTER TABLE `faculty`
  MODIFY `FACULTYID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `faculty_resource`
--
ALTER TABLE `faculty_resource`
  MODIFY `FAC_AND_RES_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `groupt`
--
ALTER TABLE `groupt`
  MODIFY `IDGROUP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `hourlyassignment`
--
ALTER TABLE `hourlyassignment`
  MODIFY `vinculationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT de la tabla `program`
--
ALTER TABLE `program`
  MODIFY `IDPROGRAM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `resourcet`
--
ALTER TABLE `resourcet`
  MODIFY `RESOURCEID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `resourcetype`
--
ALTER TABLE `resourcetype`
  MODIFY `RESSOURCETYPEID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `schedule`
--
ALTER TABLE `schedule`
  MODIFY `IDSCHEDEULE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `subject`
--
ALTER TABLE `subject`
  MODIFY `IDSUBJECT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `teacher`
--
ALTER TABLE `teacher`
  MODIFY `TEACHERID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `teacher_group`
--
ALTER TABLE `teacher_group`
  MODIFY `TEAC_GRP_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `userrol`
--
ALTER TABLE `userrol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `assignmentresource`
--
ALTER TABLE `assignmentresource`
  ADD CONSTRAINT `fk_env_res` FOREIGN KEY (`envId`) REFERENCES `resourcet` (`RESOURCEID`),
  ADD CONSTRAINT `fk_res_env` FOREIGN KEY (`resId`) REFERENCES `resourcet` (`RESOURCEID`);

--
-- Filtros para la tabla `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `FK_DEPARTME_FACULTY_D_FACULTY` FOREIGN KEY (`FACULTYID`) REFERENCES `faculty` (`FACULTYID`),
  ADD CONSTRAINT `fk_location_department` FOREIGN KEY (`location`) REFERENCES `location` (`id`);

--
-- Filtros para la tabla `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `fk_academicperiod_event` FOREIGN KEY (`academicPeriodId`) REFERENCES `academicperiod` (`ACADEMICPERIDODID`),
  ADD CONSTRAINT `fk_department_event` FOREIGN KEY (`departmentId`) REFERENCES `department` (`DEPARTMENTID`),
  ADD CONSTRAINT `fk_teacher_event` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`TEACHERID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `faculty`
--
ALTER TABLE `faculty`
  ADD CONSTRAINT `fk_faculty_location` FOREIGN KEY (`locId`) REFERENCES `location` (`id`);

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
  ADD CONSTRAINT `FK_GROUP_SUBJECT_G_SUBJECT` FOREIGN KEY (`IDSUBJECT`) REFERENCES `subject` (`IDSUBJECT`) ON DELETE CASCADE;

--
-- Filtros para la tabla `hourlyassignment`
--
ALTER TABLE `hourlyassignment`
  ADD CONSTRAINT `FK_HOURLYAS_DEPARTAME_DEPARTME` FOREIGN KEY (`DEPARTMENTID`) REFERENCES `department` (`DEPARTMENTID`),
  ADD CONSTRAINT `FK_HOURLYAS_TEACHER_H_TEACHER` FOREIGN KEY (`TEACHERID`) REFERENCES `teacher` (`TEACHERID`);

--
-- Filtros para la tabla `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `fk_location_id` FOREIGN KEY (`parentId`) REFERENCES `location` (`id`);

--
-- Filtros para la tabla `program`
--
ALTER TABLE `program`
  ADD CONSTRAINT `FK_PROGRAM_DEPARTMEN_DEPARTME` FOREIGN KEY (`DEPARTMENTID`) REFERENCES `department` (`DEPARTMENTID`),
  ADD CONSTRAINT `fk_location_program` FOREIGN KEY (`location`) REFERENCES `location` (`id`);

--
-- Filtros para la tabla `resourcet`
--
ALTER TABLE `resourcet`
  ADD CONSTRAINT `fk_resource_location` FOREIGN KEY (`location`) REFERENCES `location` (`id`),
  ADD CONSTRAINT `fk_resourcet_resourcetype` FOREIGN KEY (`RESOURCETYPEID`) REFERENCES `resourcetype` (`RESSOURCETYPEID`);

--
-- Filtros para la tabla `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `fk_schedule_event` FOREIGN KEY (`eventId`) REFERENCES `event` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_schedule_group` FOREIGN KEY (`IDGROUP`) REFERENCES `groupt` (`IDGROUP`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_schedule_res` FOREIGN KEY (`resourceId`) REFERENCES `resourcet` (`RESOURCEID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `fk_program_subject` FOREIGN KEY (`IDPROGRAM`) REFERENCES `program` (`IDPROGRAM`);

--
-- Filtros para la tabla `teacher_group`
--
ALTER TABLE `teacher_group`
  ADD CONSTRAINT `fk_teacher_group_group` FOREIGN KEY (`IDGROUP`) REFERENCES `groupt` (`IDGROUP`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_teacher_group_teacher` FOREIGN KEY (`TEACHERID`) REFERENCES `teacher` (`TEACHERID`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_teacher_user` FOREIGN KEY (`TeacherId`) REFERENCES `teacher` (`TEACHERID`);

--
-- Filtros para la tabla `userrol`
--
ALTER TABLE `userrol`
  ADD CONSTRAINT `fk_rol_userrol` FOREIGN KEY (`rolId`) REFERENCES `rol` (`id`),
  ADD CONSTRAINT `fk_user_userrol` FOREIGN KEY (`userId`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
