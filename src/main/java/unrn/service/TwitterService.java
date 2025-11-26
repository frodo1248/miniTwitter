package unrn.service;

import unrn.model.Tweet;
import unrn.model.Usuario;
import unrn.repository.TweetRepository;
import unrn.repository.UsuarioRepository;

public class TwitterService {
    private final UsuarioRepository usuarioRepo;
    private final TweetRepository tweetRepo;

    public TwitterService(UsuarioRepository usuarioRepo, TweetRepository tweetRepo) {
        this.usuarioRepo = usuarioRepo;
        this.tweetRepo = tweetRepo;
    }

    public void registrarUsuario(String userName) {
        if (userName == null) {
            throw new RuntimeException(Usuario.ERROR_USERNAME_NULL);
        }
        if (usuarioRepo.buscarPorUserName(userName) != null) {
            throw new RuntimeException(Usuario.ERROR_USERNAME_DUPLICADO);
        }
        // La validación de unicidad ya se hizo arriba, pasamos lista vacía
        Usuario usuario = new Usuario(userName, java.util.List.of());
        usuarioRepo.guardar(usuario);
    }

    public void publicarTweet(String userName, String texto) {
        Usuario usuario = usuarioRepo.buscarPorUserName(userName);
        usuario.publicarTweet(texto);
        usuarioRepo.guardar(usuario);
    }

    public void retwittear(String userName, Long tweetOriginalId) {
        Usuario usuario = usuarioRepo.buscarPorUserName(userName);
        Tweet tweetOriginal = tweetRepo.buscarPorId(tweetOriginalId);
        usuario.retwittear(tweetOriginal);
        usuarioRepo.guardar(usuario);
    }

    public void eliminarUsuario(String userName) {
        Usuario usuario = usuarioRepo.buscarPorUserName(userName);
        usuarioRepo.eliminar(usuario);
    }

    public java.util.List<Usuario> listarUsuarios() {
        return usuarioRepo.listarTodos();
    }

    public java.util.List<Tweet> listarTweetsDeUsuario(String userName) {
        Usuario usuario = usuarioRepo.buscarPorUserName(userName);
        return usuario.timeline();
    }

    public java.util.List<Tweet> listarTodosTweets() {
        return tweetRepo.listarTodos();
    }
}
