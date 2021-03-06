package tg.project.ui.configuration;

import tg.project.ui.http.TestService;
import tg.project.ui.properties.ApplicationProperty;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Bean
    public TestService provideRetrofit(ApplicationProperty property) {
        return new Retrofit.Builder()
                .baseUrl(property.getServerHost())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(TestService.class);
    }
}