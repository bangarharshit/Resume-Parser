package org.example;

import java.util.concurrent.LinkedBlockingQueue;

public class ResumeIngestor {

    LinkedBlockingQueue<ResumeInput> resumeQueue = new LinkedBlockingQueue<>();
    public void enqueue(ResumeInput resumeInput) {
        resumeQueue.add(resumeInput);
    }

    public ResumeInput consume() throws InterruptedException {
        return resumeQueue.take();
    }
}
