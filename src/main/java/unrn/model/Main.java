package unrn.model;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear lista de usuarios existente
        List<Usuario> usuarios = new ArrayList<>();
        // Crear usuarios
        Usuario usuario1 = new Usuario("leonardo", usuarios);
        usuarios.add(usuario1);
        Usuario usuario2 = new Usuario("martina", usuarios);
        usuarios.add(usuario2);

        // Publicar tweets
        usuario1.publicarTweet("Hola mundo!");
        usuario2.publicarTweet("Primer tweet de Martina");

        // Mostrar tweets de cada usuario
        System.out.println("Tweets de Leonardo:");
        usuario1.timeline().forEach(t -> System.out.println("- " + t.texto()));

        System.out.println("Tweets de Martina:");
        usuario2.timeline().forEach(t -> System.out.println("- " + t.texto()));

        // Hacer retweet
        Tweet tweetLeonardo = usuario1.timeline().get(0);
        usuario2.retwittear(tweetLeonardo);

        System.out.println("Tweets de Martina después de retwittear a Leonardo:");
        usuario2.timeline().forEach(t -> {
            System.out.print("- " + t.texto());
            if (t.esRetweet()) {
                System.out.print(" (retweet de " + t.tweetOriginal().autor().userName() + ")");
            }
            System.out.println();
        });

        // Intentar retwittear un tweet propio (debería lanzar excepción)
        try {
            usuario1.retwittear(tweetLeonardo);
        } catch (RuntimeException ex) {
            System.out.println("Excepción esperada: " + ex.getMessage());
        }

        // Eliminar usuario y verificar que se eliminan sus tweets
        usuario1.eliminar();
        System.out.println("Tweets de Leonardo después de eliminar usuario: " + usuario1.timeline().size());
    }
}
