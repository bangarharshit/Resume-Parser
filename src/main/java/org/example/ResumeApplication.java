package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResumeApplication {
    public static void main(String[] args) throws IOException {
        ResumeIngestor resumeIngestor = new ResumeIngestor();
        ResumeParserIngestor resumeParserIngestor = new ResumeParserIngestor();
        for (int i = 0; i < 2; i++) {
            ResumeClassifierConsumer resumeClassifierConsumer = new ResumeClassifierConsumer(resumeIngestor, i, resumeParserIngestor);
            resumeClassifierConsumer.start();
            ResumeParserConsumer resumeParserConsumer = new ResumeParserConsumer(resumeParserIngestor);
            resumeParserConsumer.start();
        }

        for (int i = 0; i <= 1; i++) {
            resumeIngestor.enqueue(new ResumeInput(Files.readString(Paths.get("/Users/harshitbangar/IdeaProjects/ResumeParserGradle/src/main/resources/resumes/resume" + i%2+".json"))));
        }
    }
}
