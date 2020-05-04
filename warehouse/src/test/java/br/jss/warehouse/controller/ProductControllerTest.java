package br.jss.warehouse.controller;

import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductControllerTest {
    private static final int HTTP_OK = 200;
    private static final int HTTP_CREATED = 201;
    private static final String REST_URI = "http://localhost:8080/warehouse-0.0.1/api/products";
    private final Client client = ClientBuilder.newClient();
    private WebTarget webTarget;

    @Test
    public void getAll() {
        webTarget = client.target(REST_URI).path("");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        assertEquals(HTTP_OK, response.getStatus());
    }

    @Test
    public void save() {
        webTarget = client.target(REST_URI).path("");
        JsonObject product = Json.createObjectBuilder()
                .add("name", "Doitos")
                .add("amount", (short) 121)
                .add("price", BigDecimal.valueOf(5.49))
                .add("validateDate", LocalDate.of(2022, 5, 12).toString()).build();

        Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(product));
        JsonObject payload = response.readEntity(JsonObject.class);
        assertNotNull(payload);
        assertEquals(HTTP_CREATED, response.getStatus());
    }

    @Test
    public void getById() {
        webTarget = client.target(REST_URI).path("");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        JsonObject payload = response.readEntity(JsonObject.class);
        assertNotNull(payload);
        assertEquals((short) 1, Short.valueOf(payload.get("id").toString()));
    }

    @Test
    public void remove() {
        webTarget = client.target(REST_URI).path("13");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).delete();
        assertEquals(HTTP_OK, response.getStatus());
    }

    @Test
    public void getByName() {
        webTarget = client.target(REST_URI).path("Trakinas");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        assertEquals(HTTP_OK, response.getStatus());
    }

    @Test
    public void update() {
        webTarget = client.target(REST_URI).path("15");
        JsonObject product = Json.createObjectBuilder()
                .add("name", "Doritos")
                .add("amount", (short) 205)
                .add("price", BigDecimal.valueOf(5.39))
                .add("validateDate", LocalDate.of(2021, 6, 18).toString()).build();

        Response response = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.json(product));
        JsonObject payload = response.readEntity(JsonObject.class);
        assertNotNull(payload);
    }
}
