package tg.project.ui.bot.services;

import tg.project.ui.http.TestService;
import tg.project.ui.http.model.SearchByNameResponse;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.concurrent.ExecutionException;

@Service
public class SearchSerialService {

    private final TestService testService;

    public SearchSerialService(TestService testService) {
        this.testService = testService;
    }

    public String getSerialInfo(String name) {
        try {
            Response<SearchByNameResponse> searchByNameResponse = testService.getSerialByName(name).get();
            if (searchByNameResponse.isSuccessful()) {
                SearchByNameResponse serial = searchByNameResponse.body();
                return String.format("Название RU: %s\nНазвание EN: %s\n%s",
                        serial.getNameRu(),
                        serial.getNameEn(),
                        serial.getPosterUrl()
                );
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Search serial error");
    }


}
