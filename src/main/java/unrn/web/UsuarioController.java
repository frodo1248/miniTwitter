package unrn.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import unrn.service.TwitterService;
import unrn.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final TwitterService twitterService;

    public UsuarioController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistrarUsuarioRequest request) {
        try {
            twitterService.registrarUsuario(request.getUserName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException ex) {
            if (Usuario.ERROR_USERNAME_DUPLICADO.equals(ex.getMessage()) ||
                Usuario.ERROR_USERNAME_NULL.equals(ex.getMessage()) ||
                Usuario.ERROR_USERNAME_LONGITUD.equals(ex.getMessage())) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado");
        }
    }

    @GetMapping
    public ResponseEntity<java.util.List<UsuarioResponse>> listarUsuarios() {
        var usuarios = twitterService.listarUsuarios();
        var response = usuarios.stream()
                .map(u -> new UsuarioResponse(u.userName()))
                .toList();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Error de validación")
                .findFirst()
                .orElse("Datos inválidos");
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
