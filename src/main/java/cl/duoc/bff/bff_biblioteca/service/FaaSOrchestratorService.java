package cl.duoc.bff.bff_biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FaaSOrchestratorService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${faas.usuarios.url}")
    private String usuariosUrl;

    @Value("${faas.prestamos.url}")
    private String prestamosUrl;

    public ResponseEntity<String> orquestarUsuarios(HttpMethod method, String body, String path) {
        return ejecutarLlamadaFaaS(usuariosUrl + path, method, body);
    }

    public ResponseEntity<String> orquestarPrestamos(HttpMethod method, String body, String path) {
        return ejecutarLlamadaFaaS(prestamosUrl + path, method, body);
    }

    private ResponseEntity<String> ejecutarLlamadaFaaS(String url, HttpMethod method, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> requestEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        
        return restTemplate.exchange(url, method, requestEntity, String.class);
    }
}