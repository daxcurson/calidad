ALTER TABLE `metricas` ADD `unidad_medida_id` INT NOT NULL AFTER `valor_objetivo`;
CREATE TABLE `calidad`.`unidades_medida` 
(
	`id` INT NOT NULL AUTO_INCREMENT , 
	`unidad` VARCHAR(30) NOT NULL , 
	`simbolo` VARCHAR(10) NOT NULL , 
	`descripcion` VARCHAR NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;