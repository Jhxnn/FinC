package com.FinC.services;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Service
public class ApiAsaasService {
    private static final String API_URL = "https://api-sandbox.asaas.com/v3/pix/qrCodes/static";

    @Value("${asaas.apikey}")
    private String API_KEY;
    private final OkHttpClient client = new OkHttpClient();

    public String gerarQrCode(String addressKey, double value) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = String.format(
                "{\"addressKey\":\"%s\",\"value\":%.2f}",
                addressKey, value
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

            String responseBody = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String encodedImage = jsonNode.path("encodedImage").asText();

            return encodedImage;
        }
    }
}
