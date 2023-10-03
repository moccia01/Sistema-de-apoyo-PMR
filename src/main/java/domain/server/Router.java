package domain.server;

public class Router {

    public static void init() {
        //Acadentro se hacen todas las querys
        Server.app().get("/", ctx -> {
            //ctx.sessionAttribute("item1", "Cosa 1");
            ctx.result("kk");
        });
    }
}
