CREATE TABLE `ctrlactividades`.`personas` (
  `I_CODIGO` BIGINT(38) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria de la tabla',
  `I_TIPO_IDENTIFICACION` BIGINT(38) NOT NULL COMMENT 'LLave foranea que se relaciona con el tipo de idenficacion',
  `C_NUM_IDENTIFICACION` VARCHAR(50) NOT NULL COMMENT 'Número de identificación de una persona',
  `C_NOMBRES` VARCHAR(150) NOT NULL COMMENT 'Nombres completos de una persona',
  `C_PRIMER_APELLIDO` VARCHAR(100) NOT NULL COMMENT 'Campo que almacena el primer apellido de una persona',
  `C_SEGUNDO_APELLIDO` VARCHAR(100) NULL COMMENT 'Campo que almacena el segundo apellido de una persona, es opcional',
  `C_NUM_CEL` VARCHAR(15) NOT NULL COMMENT 'Campo que almacena el número de celular de la persona',
  `C_EMAIL` VARCHAR(80) NOT NULL COMMENT 'Campo que almacena el email de la persona',
  `C_EMPLEADO` VARCHAR(3) NOT NULL DEFAULT 'N' COMMENT 'Campo que almacena si es empleado o no (S/N)',
  PRIMARY KEY (`I_CODIGO`))
COMMENT = 'Tabla que almacena los datos basicos de una persona';

CREATE TABLE `ctrlactividades`.`tipo_identificacion` (
  `I_CODIGO` BIGINT(38) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria de la tabla',
  `C_DESCRIPCION` VARCHAR(100) NOT NULL COMMENT 'Campo que almacena la descripción del tipo de documento',
  `C_ABREVIATURA` VARCHAR(40) NOT NULL COMMENT 'Campo que almacena la abreviatura del tipo de documento',
  PRIMARY KEY (`I_CODIGO`))
COMMENT = 'Tabla que almacena los  tipos de documento que puede tener una persona';

CREATE TABLE `ctrlactividades`.`actividades` (
  `I_CODIGO` BIGINT(38) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria de la tabla',
  `C_TITULO` VARCHAR(100) NOT NULL COMMENT 'Campo que almacenara el titulo de la actividad o tarea',
  `C_DESCRIPCION` VARCHAR(1000) NOT NULL COMMENT 'Campo que almacenara la descripción de la actividad o tarea',
  `F_FECHA_ESTIMADA_EJECUCION` DATETIME NOT NULL COMMENT 'Campo que almacena la fecha estimada de ejecución de la actividad o tarea',
  `I_DIAS_RETRASO` BIGINT(38) NOT NULL COMMENT 'Campo que almacenara los días de retraso de la actividad o tarea con respecto a la fecha actual',
  `I_PERSONA_ASIGNADA` BIGINT(38) NOT NULL COMMENT 'Campo que almacena la persona que esta asignado a la actividad o tarea',
  `I_ESTADO_ACTIVIDAD` BIGINT(38) NOT NULL COMMENT 'Campo que almacena el estado de la actividad',
  PRIMARY KEY (`I_CODIGO`))
COMMENT = 'Tabla que almacenara la información de las actividades o tareas ';

CREATE TABLE `ctrlactividades`.`estado_actividades` (
  `I_CODIGO` BIGINT(38) NOT NULL AUTO_INCREMENT COMMENT 'Llave primaria de la tabla',
  `C_DESCRIPCION` VARCHAR(80) NOT NULL COMMENT 'Campo que almacena el estado de la actividad',  
  PRIMARY KEY (`I_CODIGO`))
COMMENT = 'Tabla que almacena los tipos de estado que puede tener una actividad';


