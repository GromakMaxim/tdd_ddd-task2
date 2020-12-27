package org.example.value_object;

/*value-object*/
public class Incident {
    private final int id;
    private final String problem;

    public Incident(int id, String problem) {
        this.id = id;
        this.problem = problem;
    }

    public int getIncidentId() {
        return id;
    }

    public String getIncidentProblem() {
        return problem;
    }

    @Override
    public String toString() {
        return "Обращение N" + this.id + " - " + this.problem;
    }
}
