// 1 - Pacote
package petstore;


//2- Bibliotecas


import io.restassured.specification.Argument;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;

//3 - É a Classe
public class Pet {
    //3.1 Atribuitos
    //URI - endereço da documentação
    String uri = "https://petstore.swagger.io/v2/pet";
    private Object available;

    //3.2 métodos e funções
    public String lerJson(String camihoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(camihoJson)));
    }

    // incluir um novo registro
    @Test    // Identifica o métido ou função como teste para o testNG

    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given()
                .contentType("application/json") //comum em API Rest //API antiga é "text/xml"
                .log().all()
                .body(jsonBody)
                .when()
                .post(uri)
                .then()
                .log().all()
                .statusCode(200))
        ;
                //.body("name", is("Billy Francisco"));
        // }
