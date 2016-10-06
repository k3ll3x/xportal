package mx.videojuegos.izo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by isain on 9/7/2016.
 */
public class PantallaSalir implements Screen {
    private final Juego juego;
    private Stage escena;
    private Texture texturaBtnBack;
    private Texture texturaFondo;
    //Administra la carga de assets
    private final AssetManager assetManager = new AssetManager();


    public PantallaSalir(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        texturaBtnBack = new Texture("back.png");



        escena = new Stage();


        //Cargando fondo
        cargarTexturas();

        //Calcular ancho y alto
        float ancho = Gdx.graphics.getWidth();
        float alto = Gdx.graphics.getHeight();

        Image imgFondo = new Image(texturaFondo);

        //Escalar Imagen fondo
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX, escalaY);
        escena.addActor(imgFondo);

        //Crear boton de back
        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
        escena.addActor(btnBack);

        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //Regresar al menu principal
                juego.setScreen(new MenuPrincipal(juego));
            }
        });
        Gdx.input.setInputProcessor(escena);


    }
    private void cargarTexturas() {
        //Textura de fondo
        assetManager.load("PantallaSalir.png", Texture.class);

        //se bloquea hasta cargar los recursos
        assetManager.finishLoading();
        //Cuando termina leemos las texturas
        texturaFondo = assetManager.get("PantallaSalir.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f,0,0.8f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
