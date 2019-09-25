package pilot;

import formation.Formation;

import java.util.List;
import java.util.Queue;

public class CommanderPilot {
    Queue<Formation> formationQueue;
    List<NavigationPilot> navigationPilots;

    public CommanderPilot(List<NavigationPilot> navigationPilots) {
        this.navigationPilots = navigationPilots;
    }

    public void addFormation(Formation formation) {
        formationQueue.add(formation);
    }

    public void runFormation() {
        // TODO: Extend functionality for multiple formations at the same time.
        while (formationQueue.peek() != null) {
            Formation nextFormation = formationQueue.poll();
            FormationPilot formationPilot = new FormationPilot(nextFormation);
            for (int i = 0; i < nextFormation.coordinates.size(); i++) {
                formationPilot.addNavigationPilot(navigationPilots.get(i)); // TODO: Hand out work more appropiately. What to do if not enough?
            }
            formationPilot.runFormation();
            // wait(nextFormation.durationSeconds); TODO: Find method to wait for next command.
        }
    }
}
