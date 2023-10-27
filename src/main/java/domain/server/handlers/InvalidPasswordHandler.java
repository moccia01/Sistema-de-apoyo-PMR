package domain.server.handlers;

import domain.models.entities.validaciones.CredencialDeAcceso;
import domain.models.entities.validaciones.EsDebil;
import domain.models.entities.validaciones.UsaCredencialesPorDefecto;
import domain.models.entities.validaciones.Validacion;
import domain.models.entities.validaciones.politicasNIST.Longitud;
import domain.models.entities.validaciones.politicasNIST.TieneCaracterEspecial;
import domain.models.entities.validaciones.politicasNIST.TieneMayuscula;
import domain.models.entities.validaciones.politicasNIST.TieneNumero;
import domain.server.exceptions.DuplicateUserException;
import domain.server.exceptions.InvalidPasswordException;
import io.javalin.Javalin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidPasswordHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(InvalidPasswordException.class, (e, context) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error_return", context.sessionAttribute("error_return"));
            model.put("nombre", context.formParam("nombre"));
            model.put("apellido", context.formParam("apellido"));
            String usuario_nombre = context.formParam("usuario_nombre");
            model.put("usuario", usuario_nombre);
            String contrasenia = context.formParam("contrasenia");

            CredencialDeAcceso credencialDeAccesoFallida = new CredencialDeAcceso();
            credencialDeAccesoFallida.setFechaUltimoCambio(LocalDate.now());
            credencialDeAccesoFallida.setNombreUsuario(usuario_nombre);
            credencialDeAccesoFallida.setContrasenia(contrasenia);

            List<String> validacionesFallidas = new ArrayList<>();
            this.agregarValidacionesFallidas(validacionesFallidas, credencialDeAccesoFallida);
            model.put("validaciones", validacionesFallidas);
            context.render("errors/invalid_password.hbs", model);
        });
    }

    private void agregarValidacionesFallidas(List<String> validacionesFallidas, CredencialDeAcceso credencialDeAccesoFallida) {
        if(!new EsDebil().validar(credencialDeAccesoFallida)) {
            validacionesFallidas.add("Se encuentra en el Top 10.000 peores contraseñas.");
        }

        if(!new UsaCredencialesPorDefecto().validar(credencialDeAccesoFallida)) {
            validacionesFallidas.add("Es una credencial por defecto.");
        }

        if(!new Longitud().validar(credencialDeAccesoFallida)) {
            validacionesFallidas.add("No cumple con la longitud adecuada de entre 8 y 64 caracteres.");
        }

        if(!new TieneCaracterEspecial().validar(credencialDeAccesoFallida)) {
            validacionesFallidas.add("No contiene caracteres especiales.");
        }

        if(!new TieneMayuscula().validar(credencialDeAccesoFallida)) {
            validacionesFallidas.add("No contiene mayúsculas.");
        }

        if(!new TieneNumero().validar(credencialDeAccesoFallida)) {
            validacionesFallidas.add("No contiene números.");
        }
    }
}
