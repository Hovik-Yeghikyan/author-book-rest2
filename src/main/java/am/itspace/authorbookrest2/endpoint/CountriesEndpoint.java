package am.itspace.authorbookrest2.endpoint;

import am.itspace.authorbookrest2.dto.CountryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final String X_RAPID_API_KEY = "X-Rapid-Api-Key";
    private final String X_RAPID_API_HOST = "X-Rapid-Api-Host";
    private final String COUNTRIES_URL = "https://geography4.p.rapidapi.com/apis/geography/v1/country";
    @Value("${rapid.api.key}")
    private String rapidApiKey;
    @Value("${rapid.api.host}")
    private String rapidApiHost;
    private final RestTemplate restTemplate;


    @GetMapping
    public ResponseEntity<List<CountryInfo>> getCountries() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(X_RAPID_API_KEY, rapidApiKey);
        httpHeaders.add(X_RAPID_API_HOST, rapidApiHost);

        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);


        ResponseEntity<CountryInfo[]> exchange = restTemplate.exchange(COUNTRIES_URL, HttpMethod.GET, httpEntity, CountryInfo[].class);
        List<CountryInfo> result = List.of(exchange.getBody());
        return ResponseEntity.ok(result);

    }
}
