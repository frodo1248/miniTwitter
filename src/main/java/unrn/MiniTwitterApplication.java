package unrn;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import unrn.model.Retweet;
import unrn.model.Tweet;
import unrn.model.Usuario;
import unrn.repository.TweetRepository;
import unrn.repository.TweetRepositoryJpa;
import unrn.repository.UsuarioRepository;
import unrn.repository.UsuarioRepositoryJpa;
import unrn.service.TwitterService;
import unrn.utils.EmfBuilder;

@SpringBootApplication(
    exclude = {
        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
    }
)
@ComponentScan(basePackages = {"unrn.*"})
public class MiniTwitterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniTwitterApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public EntityManagerFactory entityManagerFactory() {
        return new EmfBuilder()
                .addClass(Usuario.class)
                .addClass(Tweet.class)
                .addClass(Retweet.class)
                .build();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }

    @Bean
    public UsuarioRepository usuarioRepository(EntityManager em) {
        return new UsuarioRepositoryJpa(em);
    }

    @Bean
    public TweetRepository tweetRepository(EntityManager em) {
        return new TweetRepositoryJpa(em);
    }

    @Bean
    public TwitterService twitterService(UsuarioRepository usuarioRepo, TweetRepository tweetRepo) {
        return new TwitterService(usuarioRepo, tweetRepo);
    }
}

