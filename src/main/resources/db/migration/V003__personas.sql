CREATE TABLE `calidad`.`auditores` 
(
	`id` INT NOT NULL AUTO_INCREMENT , 
	`user_id` INT NOT NULL , 
	`usuario_sistema` INT NOT NULL , 
	`nombre` VARCHAR(100) NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
