drop function if exists comprobarUsuario;

DELIMITER $$
	Create function comprobarUsuario(in_usuario VARCHAR(30), in_pass VARCHAR(30)) returns boolean deterministic
	BEGIN
		if (SELECT count(*) FROM usuario WHERE in_usuario = usuario AND in_pass = pass)>0 then RETURN true;
		else
            RETURN false;
		end if;
	End;
	$$

drop function if exists comprobarNombreUsuarioDisponible

DELIMITER $$
	Create function comprobarNombreUsuarioDisponible(in_usuario VARCHAR(30)) returns boolean deterministic
	BEGIN
		if (SELECT count(*) FROM usuario WHERE in_usuario = usuario)>0 then RETURN false;
		else
            RETURN true;
		end if;
	End;
	$$