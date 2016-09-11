package mx.videojuegos.izo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by isain on 9/7/2016.
 */
public class PantallaSettings implements Screen {
    private static final int ANCHO_MUNDO = 1280;
    private static final int ALTO_MUNDO = 720;

    private final Juego juego;

    private Stage escena;
    private Texture texturaFondo;

    //Textura botones
    private Texture texturaBtnBack;

    //Administra la carga de assets
    private final AssetManager assetManager = new AssetManager();

    //Camara
    private OrthographicCamera camara;
    private Viewport vista;

    public PantallaSettings(Juego juego) {
        this.juego = juego;
    }


    public void create(){

        //Cargar texturas
        cargarTexturas();

        escena = new Stage();

        //Habilitar el manejo de eventos
        Gdx.input.setInputProcessor(escena);

        //Calcular ancho y alto
        float ancho = ANCHO_MUNDO; //Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO; //Gdx.graphics.getHeight();

        Image imgFondo = new Image(texturaFondo);

        //Escalar Imagen fondo
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX, escalaY);
        escena.addActor(imgFondo);

        //Crear boton de back
        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBtnBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
        btnBack.setPosition(ancho / 2 - btnBack.getWidth()+620/ 2+285, 0.428f * alto-280);
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
    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        escena.setViewport(vista);
        escena.draw();
    }

    private void cargarTexturas() {
        //Textura de fondo
        assetManager.load("SettingsDef.png", Texture.class);
        assetManager.load("back.png",Texture.class);

        //se bloquea hasta cargar los recursos
        assetManager.finishLoading();

        //Cuando termina leemos las texturas
        texturaFondo = assetManager.get("SettingsDef.png");

        //Textura botones
        texturaBtnBack = assetManager.get("back.png");
    }

    @Override
    public void show() {
        //CAMARA
        camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
        camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
        camara.update();
        vista = new FitViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

        //llamar a create
        create();
    }


    @Override
    public void render(float delta) {
        render();
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    public void dispose() {
        texturaBtnBack.dispose();
        texturaFondo.dispose();
        escena.dispose();
    }
}
