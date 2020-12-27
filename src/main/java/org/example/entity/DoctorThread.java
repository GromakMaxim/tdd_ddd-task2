package org.example.entity;

import org.example.Aggregate.AppealsPool;
import org.example.Aggregate.SolvedAppealsPool;
import org.example.value_object.IncomingAppeal;

public class DoctorThread extends Thread {
    private final AppealsPool appealsPool;
    private final SolvedAppealsPool solvedAppealsPool;
    private final String name;
    private final int HEALING_TIME = 3000;

    public DoctorThread(AppealsPool appealsPool, SolvedAppealsPool solvedAppealsPool, String name) {
        this.appealsPool = appealsPool;
        this.solvedAppealsPool = solvedAppealsPool;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " приступил к работе");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                IncomingAppeal appeal = this.appealsPool.getNextAppeal();
                Thread.sleep(HEALING_TIME);
                System.out.println(name + ": завершил обращение №" + appeal.getIncident().getIncidentId() + ". Постановщик: " + appeal.getClient());
                this.solvedAppealsPool.addSolvedAppeal(appeal);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
