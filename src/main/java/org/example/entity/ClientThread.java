package org.example.entity;

public class ClientThread extends Thread {
    private final Client client;
    private final int ANAMNESIS_TIME = 1000;

    public ClientThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Перепуганный " + this.client + " вбежал в ветклинику ");
        try {
            while (!this.client.incidentsIsEmpty()) {
                Thread.sleep(ANAMNESIS_TIME);
                this.client.makeAppeal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.client + " покинул ветклинику");
    }
}
