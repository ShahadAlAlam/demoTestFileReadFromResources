package com.saa.demoTestFileReadFromResources.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DataReturnServiceTest {
    private DataReturnService dataReturnService = Mockito.spy( DataReturnService.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("classpath:TEST.BRACBD02.PAYMENTS.20240820132312.1002.MT942.rpt")
    private org.springframework.core.io.Resource resourceMt942File;

    @Value("classpath:TEST.BRACBD02.PAYMENTS.202408151444525420.QLPAY012R.4048.CSV-BANSTA.rpt")
    private org.springframework.core.io.Resource csvResourceFile;

    @Test
    public void csvParsingTest() {
        Resource resource = resourceLoader.getResource("classpath:TEST.BRACBD02.PAYMENTS.202408151444525420.QLPAY012R.4048.CSV-BANSTA.rpt");
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            dataReturnService.csvParsing(csvParser);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void csvParsingProtectedTest() {
        Resource resource = resourceLoader.getResource("classpath:TEST.BRACBD02.PAYMENTS.202408151444525420.QLPAY012R.4048.CSV-BANSTA.rpt");
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            dataReturnService.csvParsingProtected(csvParser);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void textParsingTest()  {
            Resource resource = resourceLoader.getResource("classpath:TEST.BRACBD02.PAYMENTS.20240820132312.1002.MT942.rpt");
            try {
                // Read MT942
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
                );

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }

                dataReturnService.textParsing(content.toString());
                String resultData = "{1:F01SCBLBDDXXXX3632197687}{2:O9421801070816SCBLUS33BXXX51352049330708162301N" +
                                    "}{3:{" +
                                    "108:0708160059135}}{4:" +
                                    ":20:2307081600059135" +
                                    ":25:12312312321" +
                                    ":28C:1" +
                                    ":34F:BDT0," +
                                    ":13D:0708161801-0600" +
                                    ":61:0708160816C1615,32NTRFLCK72280788700//2007081600058637DDX12409236f5c6b" +
                                    ":86:SBK REF:LCK72280788700 BNF:11111111111111 LA BO11111111 CRUZ" +
                                    ":61:0708160816C1615,32NTRFLCK72280788700//2007081600058637" +
                                    ":86:SBK REF:LCK72280788700 BNF:11111111111111 LA BO11111111 CRUZ " +
                                    "DDX12409236f5c6b" +
                                    "-}{5:{CHK:CHECKSUM DISABLED}{MAC:MACCING DISABLED}}";
                assertEquals(true,content.toString().replace("\n","").equals(resultData.replace("\n","")));
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }

    }
}

