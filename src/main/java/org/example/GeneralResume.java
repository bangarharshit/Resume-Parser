package org.example;


import java.util.ArrayList;
import java.util.List;

public class GeneralResume {
    public ArrayList<Education> education;
    public ArrayList<Endorsement> endorsements;
    public String id;

    @Override
    public String toString() {
        return "GeneralResume{" +
                "education=" + education +
                ", endorsements=" + endorsements +
                ", id='" + id + '\'' +
                '}';
    }

    public static class Education {
        public String school;
        public String degree;
        public List<String> time;
        public String description;

        @Override
        public String toString() {
            return "Education{" +
                    "school='" + school + '\'' +
                    ", degree='" + degree + '\'' +
                    ", time=" + time +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    public static class Endorsement{
        public String work;
        public String description;
        public String metadata;

        @Override
        public String toString() {
            return "Endorsement{" +
                    "work='" + work + '\'' +
                    ", description='" + description + '\'' +
                    ", metadata='" + metadata + '\'' +
                    '}';
        }
    }
}
