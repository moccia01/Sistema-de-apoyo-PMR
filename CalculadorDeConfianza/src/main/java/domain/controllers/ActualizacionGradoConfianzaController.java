package domain.controllers;

import domain.calculos.CalculadorGradoConfianza;
import domain.comunidades.*;
import domain.json.PayloadDTO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class ActualizacionGradoConfianzaController implements Handler{

    public ActualizacionGradoConfianzaController() {
        super();
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        PayloadDTO payloadDTO = context.bodyAsClass(PayloadDTO.class);

        new CalculadorGradoConfianza().actualizarPuntosDeConfianza(
                payloadDTO.getUsuarios(),
                payloadDTO.getComunidades(),
                payloadDTO.getIncidentes().stream()
                        .filter(Incidente::estaDentroDeLaSemana).toList());

        // TODO ver como devolver el json con las listas actualizadas
        context.json(payloadDTO);
    }
}
