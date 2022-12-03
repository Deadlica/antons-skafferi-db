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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteCarte(dishIdPlacerholder dishIdPlacerholder) {
        carteBean.deleteCarte(dishIdPlacerholder.getId());
        return "ok";
    }

    public static class dishIdPlacerholder {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}