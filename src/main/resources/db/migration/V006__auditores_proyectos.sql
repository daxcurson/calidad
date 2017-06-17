CREATE TABLE `auditores_proyectos` (
  `id` int(11) NOT NULL,
  `proyecto_id` int(11) NOT NULL,
  `auditor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `auditores_proyectos`  ADD PRIMARY KEY (`id`);

ALTER TABLE `auditores_proyectos`  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
