package org.example.entity;

import org.example.Aggregate.AppealsPool;
import org.example.Aggregate.SolvedAppealsPool;
import org.example.value_object.IncomingAppeal;

public class NurseThread extends Thread {
    private final AppealsPool pool;
    private final SolvedAppealsPool solvedAppealsPool;
    private String name;
    private final int PREPARATION_TIME = 1000;

    public NurseThread(AppealsPool appealsPool, SolvedAppealsPool solvedAppealsPool, String name) {
        this.pool = appealsPool;
        this.name = name;
        this.solvedAppealsPool = solvedAppealsPool;
    }

    @Override
    public void run() {
        System.out.println(this.name + " приступила к работе");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                IncomingAppeal appeal = this.solvedAppealsPool.getNextSolvedAppeal();
                appeal.getClient().putAppeal(appeal.getIncident());
                Thread.sleep(PREPARATION_TIME);
                System.out.println(this.name + " возвращаю питомца " + appeal.getClient() + " (обращение №" + appeal.getIncident().getIncidentId() + ")");
            }
        } catch (InterruptedException e) {
            //empty block
        }
        System.out.println(this.name + " завершила работу.");
    }
}