package org.example;

import java.util.concurrent.LinkedBlockingQueue;

public class ResumeParserIngestor {
    LinkedBlockingQueue<ClassifiedResumeInput> resumeQueue = new LinkedBlockingQueue<>();
    public void enqueue(ClassifiedResumeInput resumeInput) {
        resumeQueue.add(resumeInput);
    }

    public ClassifiedResumeInput consume() throws InterruptedException {
        return resumeQueue.take();
    }
}
