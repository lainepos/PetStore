// 1 - Pacote
package petstore;


//2- Bibliotecas


import io.restassured.response.ValidatableResponse;
import io.restassured.specification.Argument;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.*;

//3 - � a Classe
public class Pet {
    //3.1 Atribuitos
    //URI - endere�o da documenta��o
    String uri = "https://petstore.swagger.io/v2/pet";
    private Object available;

    //3.2 m�todos e fun��es
    public String lerJson(String camihoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(camihoJson)));
    }

    // incluir um novo registro
    @Test    // Identifica o m�tido ou fun��o como teste para o testNG

    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Ent�o
        // Given - When - Then

        given()
                .contentType("application/json") //comum em API Rest //API antiga � "text/xml"
                .log().all()
                .body(jsonBody)
                .when()
                .post(uri)
                .then()
                .log().all()
                .statusCode(200)

        ;
    }


    @Test
    public void consultarPet() {
        String petId = "30011986727";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)

                .then()
                .log().all()
                .statusCode(200)
                          ;

    }
    @Test
    public void alterarPet() throws IOException {
        String jsonBody= lerJson("db/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
            .put(uri)
        .then()
                .log().all()
                .statusCode(200)

               ;

    }

    @Test
    public void excluirPet (){
        String petId = "30011986727";

        given()
                .contentType("application/json")
                .log().all()

        .when()
                .delete(uri+"/"+petId)
        .then()
                .log().all()
                .statusCode(200);



    }

}
