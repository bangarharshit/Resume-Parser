package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class ResumeClassifierConsumer {
    public ResumeIngestor resumeIngestor;
    public int consumerIdentifier;
    public ResumeParserIngestor resumeParserIngestor;

    public ResumeClassifierConsumer(ResumeIngestor resumeIngestor, int consumerIdentifier, ResumeParserIngestor resumeParserIngestor) {
        this.resumeIngestor = resumeIngestor;
        this.resumeParserIngestor = resumeParserIngestor;
        this.consumerIdentifier = consumerIdentifier;
    }

    // failure - what if this consumer crashes - implement visibility timeout
    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    ResumeInput resumeInput = resumeIngestor.consume();
                    JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V6);
                    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                    for (int i = 1; i <= 2; i++) {
                        InputStream inputStream = classloader.getResourceAsStream("schema/resume_type"+i+ ".json");
                        JsonSchema jsonSchema = factory.getSchema(inputStream);
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonNode = mapper.readTree(resumeInput.getJson());
                        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
                        if (errors.isEmpty()) {
                            resumeParserIngestor.enqueue(new ClassifiedResumeInput(resumeInput.getJson(), i));
                            break;
                        }
                    }
                    // classification
                    // parse the json
                    // create our model
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }).start();
    }
}
