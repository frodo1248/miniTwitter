package unrn.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tweets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("TWEET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tweet {
    public static final String ERROR_TEXTO_LONGITUD = "El texto del tweet debe tener entre 1 y 280 caracteres";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 280, nullable = false)
    private String texto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_username", referencedColumnName = "userName")
    private Usuario autor;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public Tweet(String texto, Usuario autor) {
        if (this.getClass() == Tweet.class) {
            assertTextoValido(texto);
        }
        this.texto = texto;
        this.autor = autor;
        this.fecha = LocalDateTime.now();
    }

    private void assertTextoValido(String texto) {
        if (texto == null || texto.length() < 1 || texto.length() > 280) {
            throw new RuntimeException(ERROR_TEXTO_LONGITUD);
        }
    }

    public boolean esRetweet() {
        return false;
    }

    public String texto() {
        return this.texto;
    }

    public String infoExtra() {
        return "";
    }

    public Usuario autor() {
        return this.autor;
    }

    public String userName() {
        return this.autor.userName();
    }

    public LocalDateTime fecha() {
        return this.fecha;
    }
}
