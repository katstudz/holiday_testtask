package com.task.holiday.service.external;

import com.task.holiday.model.HolidayApiConfig;
import com.task.holiday.model.external.api.HolidayApiQueryParams;
import com.task.holiday.model.external.api.HolidaysList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.task.holiday.model.external.api.APIParameter.API_KEY;

@Service
public class HolidayApiExternalService implements IHolidayApiExternalService {
    private RestTemplate restTemplate = new RestTemplate();
    private String REST_URI;
    private String key;

    @Autowired
    public HolidayApiExternalService(HolidayApiConfig holidayApiConfig) {
        this.REST_URI = holidayApiConfig.getRestUri();
        this.key = holidayApiConfig.getKey();
    }

    private String createPathParam(HolidayApiQueryParams params){
        String keyParam = API_KEY.toString() + "="+ key +"&";
        return REST_URI + keyParam + params.toPathVariable();
    }

    public Optional<HolidaysList> getHolidaysList(HolidayApiQueryParams params) {
        String fooResourceUrl = createPathParam(params);
        try {
            ResponseEntity<HolidaysList> response =
                    restTemplate.getForEntity(fooResourceUrl , HolidaysList.class);

            if(response.getStatusCode() == HttpStatus.OK){
                HolidaysList holidaysList = response.getBody();
                assert holidaysList != null;
                return Optional.of(holidaysList);
            }
        }
        catch (Exception ignored){}
        return Optional.empty();
    }
}

