package org.example.Aggregate;

import org.example.value_object.IncomingAppeal;

import java.util.ArrayDeque;

public class AppealsPool {
    private final ArrayDeque<IncomingAppeal> appeals;//очередь обращений

    public AppealsPool() {
        this.appeals = new ArrayDeque<>();

    }

    public IncomingAppeal getNextAppeal() throws InterruptedException {
        IncomingAppeal appeal;
        synchronized (this.appeals) {
            while (this.appeals.isEmpty()) {
                this.appeals.wait();
            }
            appeal = this.appeals.removeFirst();
        }
        return appeal;
    }

    public void addAppeal(IncomingAppeal appeal) {
        if (appeal != null) {
            synchronized (this.appeals) {
                this.appeals.add(appeal);
                System.out.println(appeal);
                this.appeals.notify();
            }
        }
    }
}
