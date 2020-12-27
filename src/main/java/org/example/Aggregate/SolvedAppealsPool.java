package org.example.Aggregate;

import org.example.value_object.IncomingAppeal;

import java.util.ArrayDeque;

public class SolvedAppealsPool {
    private final ArrayDeque<IncomingAppeal> solvedAppeals;//очередь готовых заказов

    public SolvedAppealsPool() {
        this.solvedAppeals = new ArrayDeque<>();
    }

    public IncomingAppeal getNextSolvedAppeal() throws InterruptedException {
        IncomingAppeal appeal;
        synchronized (this.solvedAppeals) {
            while (this.solvedAppeals.isEmpty()) {
                this.solvedAppeals.wait();
            }
            appeal = this.solvedAppeals.removeFirst();
        }
        return appeal;
    }

    public void addSolvedAppeal(IncomingAppeal appeal) {
        if (appeal != null) {
            synchronized (this.solvedAppeals) {
                this.solvedAppeals.add(appeal);
                this.solvedAppeals.notify();
            }
        }
    }
}
