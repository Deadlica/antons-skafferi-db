package rest.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
    public RestApplication() {
    }
}