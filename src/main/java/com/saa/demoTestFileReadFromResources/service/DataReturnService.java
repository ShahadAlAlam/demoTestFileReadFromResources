package com.saa.demoTestFileReadFromResources.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import org.apache.commons.csv.CSVParser;

@Service
public class DataReturnService {
    DataReturnService() {

    }
    protected void csvParsingProtected(CSVParser csvParser) {
        for (CSVRecord record : csvParser) {
            record.forEach(System.out::println);
//            String hData = record.get("H");
//            String scblhkhoxxData = record.get("SCBLHKH0XXX");
//            String bracdbData = record.get("scblhkhoxx");
//            System.out.println("BANSTA hData: " + hData + ", scblhkhoxxData: " + scblhkhoxxData + ", bracdbData: " + bracdbData);
        }

    }

    public void csvParsing(CSVParser csvParser) {
        for (CSVRecord record : csvParser) {
            record.forEach(System.out::println);
//            String hData = record.get("H");
//            String scblhkhoxxData = record.get("SCBLHKH0XXX");
//            String bracdbData = record.get("scblhkhoxx");
//            System.out.println("BANSTA hData: " + hData + ", scblhkhoxxData: " + scblhkhoxxData + ", bracdbData: " + bracdbData);
        }

    }

    public void textParsing(String data) {
        System.out.println("MT942 "+data);
    }
}

