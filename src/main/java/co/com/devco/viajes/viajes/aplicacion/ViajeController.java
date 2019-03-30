package co.com.devco.viajes.viajes.aplicacion;

import co.com.devco.viajes.viajes.dominio.Viaje;
import co.com.devco.viajes.viajes.infraestructura.rabbit.Publicador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/viaje")
public class ViajeController {

    Publicador publicador = new Publicador();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Viaje> crearViaje(@RequestBody Viaje viaje) {
        publicador.publicarMensaje("devco.viaje.viajecomprado", viaje.getTipo(), viaje.getId()+" - "+viaje.getDestino());
        //publicador.publicarMensajeAsincrono("devco.viaje.viajecomprado", viaje.getTipo(), viaje.getId()+" - "+viaje.getDestino());
        return new ResponseEntity<Viaje>(viaje, null, HttpStatus.OK);
    }
}
