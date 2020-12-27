package org.example.Aggregate;

import org.example.entity.Client;
import org.example.entity.ClientThread;

import java.util.ArrayList;
import java.util.List;

public class ClientsPool {
    private final List<ClientThread> poolClients;
    private final int AMOUNT_CLIENTS = 3;//кол-во новых посетителей
    private final AppealsPool appealsPool;
    private final IncidentsPool incidentsPool;

    public ClientsPool(AppealsPool appealsPool, IncidentsPool incidentsPool) {
        this.poolClients = new ArrayList<>();
        this.appealsPool = appealsPool;
        this.incidentsPool = incidentsPool;
    }

    public void startClients() {
        for (int count = 0; count < AMOUNT_CLIENTS; count++) {
            ClientThread client = new ClientThread(new Client(count + 1, "Клиент", appealsPool, incidentsPool.getListIncidents()));
            this.poolClients.add(client);
            client.start();
        }
    }

    public boolean clientsIs() {
        boolean result = false;
        for (ClientThread client : this.poolClients) {
            if (result = client.isAlive()) {
                break;
            }
        }
        return result;
    }
}
