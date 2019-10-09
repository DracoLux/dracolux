import network.server.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.start();
        while (server.getClients().size() < 3){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Waiting for connection...");
        }
        server.sendToAll("HELLO DRONE");
        /*
        List<NavigationPilot> navigationPilots = new ArrayList<NavigationPilot>();
        navigationPilots.add(new NavigationPilot(new DumbDrone(), new Coordinate(5, 6, 7)));
        navigationPilots.add(new NavigationPilot(new DumbDrone(), new Coordinate(3, 1, 4)));
        navigationPilots.add(new NavigationPilot(new DumbDrone(), new Coordinate(25, 36, 47)));


        // TODO: This is where shapes and light patterns are decided and generated.
        CommanderPilot commanderPilot = new CommanderPilot(navigationPilots);

        // Generate formations here arbritarily.
        for (int i = 0; i < 3; i++) {
            Formation formation = FormationGenerator.generateFormation(10 - i, Shape.SQUARE, Color.RED, LightPattern.BLINK, i * 2);
            commanderPilot.addFormation(formation);
        }

        commanderPilot.runFormation();
        */
    }

}
