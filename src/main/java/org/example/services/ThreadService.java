package org.example.services;

import org.example.Aggregate.AppealsPool;
import org.example.Aggregate.ClientsPool;
import org.example.Aggregate.IncidentsPool;
import org.example.Aggregate.SolvedAppealsPool;

import org.example.entity.DoctorThread;
import org.example.entity.NurseThread;

public class ThreadService {
    private final DoctorThread doctor;
    private final DoctorThread doctor1;
    private final NurseThread nurse;

    private final AppealsPool appealsPool;
    private final SolvedAppealsPool solvedAppealsPool;
    private final IncidentsPool incidentsPool;
    private final ClientsPool clientsPool;

    public ThreadService() {
        this.appealsPool = new AppealsPool();
        this.solvedAppealsPool = new SolvedAppealsPool();
        this.incidentsPool = new IncidentsPool();
        this.clientsPool = new ClientsPool(appealsPool, incidentsPool);

        this.doctor = new DoctorThread(appealsPool, solvedAppealsPool, "Доктор Хаус");
        this.doctor1 = new DoctorThread(appealsPool, solvedAppealsPool, "Доктор Айболит");
        this.nurse = new NurseThread(appealsPool, solvedAppealsPool, "Медсестра");
    }

    //старт потоков
    public void init() {
        System.out.println("Start program...");
        this.doctor.start();
        this.doctor1.start();
        this.nurse.start();
        clientsPool.startClients();
        while (true) {//продолжаем программу пока жив хоть один клиент=)
            if (!clientsPool.clientsIs()) {
                this.stop();
                break;
            }
        }
        System.out.println("End program...");
    }

    private void stop() {
        this.doctor.interrupt();
        this.doctor1.interrupt();
        this.nurse.interrupt();
        while (this.doctor.isAlive() || this.doctor1.isAlive() || this.nurse.isAlive()) {
            //empty cycle
        }
    }



}
