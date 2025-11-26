package unrn.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para CorsConfig")
class CorsConfigTest {

    @Test
    @DisplayName("CorsConfig crea un WebMvcConfigurer correctamente")
    void corsConfigCreaWebMvcConfigurerCorrectamente() {
        // Setup
        CorsConfig corsConfig = new CorsConfig();

        // Ejercitación
        WebMvcConfigurer configurer = corsConfig.corsConfigurer();

        // Verificación
        assertNotNull(configurer, "El configurer no debe ser nulo");
    }

    @Test
    @DisplayName("WebMvcConfigurer tiene el método addCorsMappings")
    void webMvcConfigurerTieneMetodoAddCorsMappings() {
        // Setup
        CorsConfig corsConfig = new CorsConfig();
        WebMvcConfigurer configurer = corsConfig.corsConfigurer();

        // Verificación
        assertDoesNotThrow(() -> {
            CorsRegistry registry = new CorsRegistry();
            configurer.addCorsMappings(registry);
        }, "El método addCorsMappings debe ejecutarse sin errores");
    }

    @Test
    @DisplayName("CorsConfig bean puede ser instanciado")
    void corsConfigBeanPuedeSerInstanciado() {
        // Ejercitación y Verificación
        assertDoesNotThrow(() -> {
            CorsConfig config = new CorsConfig();
            assertNotNull(config, "CorsConfig debe poder ser instanciado");
        }, "CorsConfig debe ser instanciable");
    }
}

