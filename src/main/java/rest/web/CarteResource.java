package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Carte;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/carte")
public class CarteResource {

    @Inject
    CarteBean carteBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carte> getAllCarte() {
        return carteBean.getAllCarte();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Carte insertCarte(Carte carte) {
        return carteBean.insertCarte(carte);
    }
}