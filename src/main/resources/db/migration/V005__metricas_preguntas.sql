ALTER TABLE `metricas` ADD `pregunta_id` INT NOT NULL AFTER `descripcion`;
ALTER TABLE `mediciones` ADD `fecha` DATE NOT NULL AFTER `valor_medido`;
