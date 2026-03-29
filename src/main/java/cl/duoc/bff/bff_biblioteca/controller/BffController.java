package cl.duoc.bff.bff_biblioteca.controller;

import cl.duoc.bff.bff_biblioteca.service.FaaSOrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biblioteca")
public class BffController {

    @Autowired
    private FaaSOrchestratorService orquestadorService;

    // Orquestación de llamadas a la FaaS de Usuarios
    @PostMapping("/usuarios")
    public ResponseEntity<String> crearUsuario(@RequestBody String requestBody) {
        return orquestadorService.orquestarUsuarios(HttpMethod.POST, requestBody, "");
    }

    @GetMapping("/usuarios")
    public ResponseEntity<String> obtenerTodosLosUsuarios() {
        return orquestadorService.orquestarUsuarios(HttpMethod.GET, null, "");
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable String id, @RequestBody String requestBody) {
         return orquestadorService.orquestarUsuarios(HttpMethod.PUT, requestBody, "");
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String id) {
         return orquestadorService.orquestarUsuarios(HttpMethod.DELETE, null, "?idUsuario=" + id);
    }

    // Orquestación de llamadas a la FaaS de Préstamos
    @PostMapping("/prestamos")
    public ResponseEntity<String> crearPrestamo(@RequestBody String requestBody) {
        return orquestadorService.orquestarPrestamos(HttpMethod.POST, requestBody, "");
    }

    @GetMapping("/prestamos")
    public ResponseEntity<String> obtenerTodosLosPrestamos() {
        return orquestadorService.orquestarPrestamos(HttpMethod.GET, null, "");
    }
    
    @PutMapping("/prestamos/{id}")
    public ResponseEntity<String> actualizarPrestamo(@PathVariable String id, @RequestBody String requestBody) {
         return orquestadorService.orquestarPrestamos(HttpMethod.PUT, requestBody, "");
    }

    @DeleteMapping("/prestamos/{id}")
    public ResponseEntity<String> eliminarPrestamo(@PathVariable String id) {
         return orquestadorService.orquestarPrestamos(HttpMethod.DELETE, null, "?idPrestamo=" + id);
    }
}