package mx.videojuegos.izo;

import com.badlogic.gdx.Game;

/**
 * Created by isain on 9/5/2016.
 * Pinche Github
 */
public class Juego extends Game {
    public void create() {
        setScreen(new PrincipalPantalla(this));
    }

}
