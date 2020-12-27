package org.example.entity;

import org.example.Aggregate.AppealsPool;
import org.example.value_object.Incident;
import org.example.value_object.IncomingAppeal;

import java.util.ArrayDeque;

public class Client {
    private final int id;
    private final String name;
    private final AppealsPool appealsPool;
    private final ArrayDeque<Incident> incidents;

    public Client(int id, String name, AppealsPool appealsPool, ArrayDeque<Incident> incidents) {
        this.id = id;
        this.name = name;
        this.appealsPool = appealsPool;
        this.incidents = incidents;
    }

    public boolean incidentsIsEmpty() {
        synchronized (this.incidents) {
            return this.incidents.isEmpty();
        }
    }

    //сделать заказ
    public void makeAppeal() throws InterruptedException {
        if (!this.incidentsIsEmpty()) {
            synchronized (this.incidents) {
                IncomingAppeal appeal = new IncomingAppeal(this, this.incidents.getFirst());
                this.appealsPool.addAppeal(appeal);
                this.incidents.wait();
            }
        }
    }

    public void putAppeal(Incident incident) {
        if (incident != null) {
            synchronized (this.incidents) {
                this.incidents.removeFirst();
                this.incidents.notify();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s N%d", this.name, this.id);
    }
}
