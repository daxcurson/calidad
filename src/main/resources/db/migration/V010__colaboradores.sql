CREATE TABLE `colaboradores` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `usuario_sistema` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `habilitada` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `colaboradores`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `colaboradores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
CREATE TABLE `colaboradores_proyectos` (
  `id` int(11) NOT NULL,
  `proyecto_id` int(11) NOT NULL,
  `colaborador_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `colaboradores_proyectos`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `colaboradores_proyectos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;