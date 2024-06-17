package am.itspace.authorbookrest2.endpoint;

import am.itspace.authorbookrest2.dto.CountryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountriesEndpoint {
    private final RestTemplate restTemplate;
    private final String COUNTRIES_URL = "https://geography4.p.rapidapi.com/apis/geography/v1/country";

    @GetMapping
    public ResponseEntity<List<CountryInfo>> getCountries() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-RapidAPI-Key", "d6f1684676mshf0cea19e6e9a00bp16268djsn8af14d6ff35f");
        httpHeaders.add("X-RapidAPI-Host", "geography4.p.rapidapi.com");

        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);


        ResponseEntity<CountryInfo[]> exchange = restTemplate.exchange(COUNTRIES_URL, HttpMethod.GET, httpEntity, CountryInfo[].class);
        List<CountryInfo> result = List.of(exchange.getBody());
        return ResponseEntity.ok(result);

    }
}
