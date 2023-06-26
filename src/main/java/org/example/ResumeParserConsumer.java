package org.example;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.google.gson.Gson;

import java.util.List;

public class ResumeParserConsumer {

    public ResumeParserIngestor resumeParserIngestor;

    public ResumeParserConsumer(ResumeParserIngestor resumeParserIngestor) {
        this.resumeParserIngestor = resumeParserIngestor;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume(resumeParserIngestor.consume());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void consume(ClassifiedResumeInput classifiedResumeInput) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        List<Object> chainrSpecJSON = JsonUtils.jsonToList(classloader.getResourceAsStream("transformers/transformer" + classifiedResumeInput.type + ".json"));
        Chainr chainr = Chainr.fromSpec( chainrSpecJSON );

        Object inputJSON = JsonUtils.jsonToObject( classifiedResumeInput.resume);

        Object transformedOutput = chainr.transform( inputJSON );
        GeneralResume generalResume = new Gson().fromJson(JsonUtils.toJsonString( transformedOutput ), GeneralResume.class);
        System.out.println(  generalResume);
    }
}
