package unrn.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import unrn.model.Retweet;
import unrn.model.Tweet;
import unrn.service.TwitterService;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TwitterService twitterService;

    public TweetController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @PostMapping
    public ResponseEntity<?> publicarTweet(@Valid @RequestBody PublicarTweetRequest request) {
        try {
            twitterService.publicarTweet(request.getUserName(), request.getTexto());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException ex) {
            if (Tweet.ERROR_TEXTO_LONGITUD.equals(ex.getMessage())) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado");
        }
    }

    @PostMapping("/retweet")
    public ResponseEntity<?> retwittear(@Valid @RequestBody RetweetRequest request) {
        try {
            twitterService.retwittear(request.getUserName(), request.getTweetOriginalId());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException ex) {
            if (Retweet.ERROR_TWEET_NULL.equals(ex.getMessage()) ||
                Retweet.ERROR_RETWEET_PROPIO.equals(ex.getMessage())) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado");
        }
    }

    @GetMapping
    public ResponseEntity<java.util.List<TweetResponse>> listarTodosTweets() {
        var tweets = twitterService.listarTodosTweets();
        var response = tweets.stream()
                .map(this::convertirATweetResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{userName}")
    public ResponseEntity<java.util.List<TweetResponse>> listarTweetsDeUsuario(@PathVariable String userName) {
        try {
            var tweets = twitterService.listarTweetsDeUsuario(userName);
            var response = tweets.stream()
                    .map(this::convertirATweetResponse)
                    .toList();
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private TweetResponse convertirATweetResponse(Tweet tweet) {
        String fechaISO = tweet.fecha() != null ? tweet.fecha().toString() : null;

        if (tweet.esRetweet()) {
            Retweet retweet = (Retweet) tweet;
            return new TweetResponse(
                    tweet.getId(),
                    tweet.texto(),
                    tweet.userName(),
                    fechaISO,
                    true,
                    retweet.getTweetOriginal().getId(),
                    retweet.getTweetOriginal().userName()
            );
        } else {
            return new TweetResponse(
                    tweet.getId(),
                    tweet.texto(),
                    tweet.userName(),
                    fechaISO,
                    false,
                    null,
                    null
            );
        }
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

