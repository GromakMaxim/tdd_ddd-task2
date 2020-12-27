package org.example.value_object;

import org.example.entity.Client;

public class IncomingAppeal {
    private final Client client;
    private final Incident incident;

    public Client getClient() {
        return client;
    }

    public Incident getIncident() {
        return incident;
    }

    public IncomingAppeal(Client client, Incident incident) {
        this.client = client;
        this.incident = incident;
    }

    @Override
    public String toString() {
        return String.format(this.client + " обращение - " + this.incident);
    }
}
