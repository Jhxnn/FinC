package com.FinC.services;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiAsaasService {
    private static final String API_URL = "https://api-sandbox.asaas.com/v3/pix/qrCodes/static";

    @Value("${asaas.apikey}")
    private static String apiKey;

    private static final String API_KEY = apiKey;
    private final OkHttpClient client = new OkHttpClient();

    public String gerarQrCode(String addressKey, String description, double value) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = String.format(
                "{\"addressKey\":\"%s\",\"description\":\"%s\",\"value\":%.2f}",
                addressKey, description, value
        );

        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("access_token", API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response);
            }
            return response.body().string();
        }
    }


}
