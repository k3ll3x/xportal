package mx.videojuegos.izo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by isain on 9/1/2016.
 */
public class PantallaJugar implements Screen {

    private static final int ANCHO_MUNDO = 1280;
    private static final int ALTO_MUNDO = 720;

    private final Juego juego;

    private Stage escena;
    private Texture texturaFondo;

    //Textura Botones
    private Texture texturaBtnPause;
    private Texture texturaBtnIzquierdo;
    private Texture texturaBtnDerecho;
    private Texture texturaBtnSaltar;

    //Administra la carga de assets
    private final AssetManager assetManager = new AssetManager();

    //Camara
    private OrthographicCamera camara;
    private Viewport vista;


    public PantallaJugar(Juego juego) {this.juego = juego;}

    public void create(){

        //Cargando texturas
        cargarTexturas();

        escena = new Stage();

        //Habilitar el manejo de eventos
        Gdx.input.setInputProcessor(escena);

        //Calcular ancho y alto
        float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
        float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();

        //fondo
        Image imgFondo = new Image(texturaFondo);

        //Escalar Imagen fondo
        float escalaX = ancho / imgFondo.getWidth();
        float escalaY = alto / imgFondo.getHeight();
        imgFondo.setScale(escalaX, escalaY);
        escena.addActor(imgFondo);

        //Agregar botones
        //Crear boton de Pausa
        TextureRegionDrawable trdBtnPause = new TextureRegionDrawable(new TextureRegion(texturaBtnPause));
        ImageButton btnPause = new ImageButton(trdBtnPause);
        btnPause.setPosition(ancho / 2 - btnPause.getWidth()+620/ 2+285, 0.428f * alto+280);
        escena.addActor(btnPause);

        //Crear boton Izquierdo
        TextureRegionDrawable trdBtnIzquierdo = new TextureRegionDrawable(new TextureRegion(texturaBtnIzquierdo));
        ImageButton btnIzquierdo = new ImageButton(trdBtnIzquierdo);
        btnIzquierdo.setPosition(ancho / 2 - btnIzquierdo.getWidth()-200/ 2-380, 0.428f * alto-280);
        escena.addActor(btnIzquierdo);

        //Crear boton Derecho
        TextureRegionDrawable trdBtnDerecho = new TextureRegionDrawable(new TextureRegion(texturaBtnDerecho));
        ImageButton btnDerecho = new ImageButton(trdBtnDerecho);
        btnDerecho.setPosition(ancho / 2 - btnDerecho.getWidth()-200/ 2-200, 0.428f * alto-280);
        escena.addActor(btnDerecho);

        //Crear boton Salto
        TextureRegionDrawable trdBtnSaltar = new TextureRegionDrawable(new TextureRegion(texturaBtnSaltar));
        ImageButton btnSaltar = new ImageButton(trdBtnSaltar);
        btnSaltar.setPosition(ancho / 2 - btnSaltar.getWidth()+620/ 2+285, 0.428f * alto-280);
        escena.addActor(btnSaltar);

        //Registrar listener para atender evento del boton
        btnPause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //Regresar al menu principal
                Gdx.app.log("clicked", "TAP sobre el boton Pausa");
                juego.setScreen(new PantallPause(juego));
            }
        });
        btnIzquierdo.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("clicked", "TAP sobre el boton Izquierdo");
            }
        });
        btnDerecho.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("clicked", "TAP sobre el boton Derecho");
            }
        });
        btnSaltar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("clicked", "TAP sobre el boton Saltar");
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
        assetManager.load("Nivel1Temp.png", Texture.class);
        assetManager.load("BtmPausa.png",Texture.class);
        assetManager.load("BtmIzquierdo.png",Texture.class);
        assetManager.load("BtmDerecho.png",Texture.class);
        assetManager.load("BtmSaltar.png",Texture.class);

        //se bloquea hasta cargar los recursos
        assetManager.finishLoading();

        //Cuando termina leemos las texturas
        texturaFondo = assetManager.get("Nivel1Temp.png");

        //Textura Botones
        texturaBtnPause = assetManager.get("BtmPausa.png");
        texturaBtnIzquierdo = assetManager.get("BtmIzquierdo.png");
        texturaBtnDerecho = assetManager.get("BtmDerecho.png");
        texturaBtnSaltar = assetManager.get("BtmSaltar.png");
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
        texturaBtnPause.dispose();
        texturaBtnSaltar.dispose();
        texturaBtnDerecho.dispose();
        texturaBtnIzquierdo.dispose();
        texturaFondo.dispose();
        escena.dispose();
    }
}
