package com.ctl.corona.services;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class VirusDataService {
    private static final String DATA_SOURCE_URL =
"https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    @PostConstruct
    @Scheduled(cron = "******")
    public void fetchData() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(DATA_SOURCE_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(
                httpRequest, HttpResponse.BodyHandlers.ofString()
        );

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new StringReader(httpResponse.body()));
        for (CSVRecord record:records){
            String state = record.get("Country/Region");
            System.out.println(state);
        }
    }
}
