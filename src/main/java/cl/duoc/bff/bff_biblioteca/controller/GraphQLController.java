package cl.duoc.bff.bff_biblioteca.controller; // Ajusta el package si es distinto

import cl.duoc.bff.bff_biblioteca.service.FaaSOrchestratorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GraphQLController {

    @Autowired
    private FaaSOrchestratorService orquestadorService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // QUERIES (Equivalentes a GET)
    @QueryMapping
    public List<Map<String, Object>> obtenerUsuarios() {
        try {
            ResponseEntity<String> response = orquestadorService.orquestarUsuarios(HttpMethod.GET, null, "");
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return objectMapper.readValue(response.getBody(), new TypeReference<List<Map<String, Object>>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en consola si falla la conversión
        }
        return new ArrayList<>();
    }

    @QueryMapping
    public List<Map<String, Object>> obtenerPrestamos() {
        try {
            ResponseEntity<String> response = orquestadorService.orquestarPrestamos(HttpMethod.GET, null, "");
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                 return objectMapper.readValue(response.getBody(), new TypeReference<List<Map<String, Object>>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // MUTATIONS (Equivalentes a POST)
    @MutationMapping
    public String crearUsuario(@Argument String usuarioInput) {
        ResponseEntity<String> response = orquestadorService.orquestarUsuarios(HttpMethod.POST, usuarioInput, "");
        return response.getBody();
    }

    @MutationMapping
    public String crearPrestamo(@Argument String prestamoInput) {
        ResponseEntity<String> response = orquestadorService.orquestarPrestamos(HttpMethod.POST, prestamoInput, "");
        return response.getBody();
    }
}