package mx.videojuegos.izo;

import com.badlogic.gdx.Game;

/**
 * Created by isain on 9/5/2016.
 *
 * Sistema de control de versiones
 * ejercicio isain
 * Listo Adriana :)
 * Siegfried es un hacker
 * Diana :D
 */
public class Juego extends Game {
    public void create() {
        setScreen(new PrincipalPantalla(this));
    }

}
