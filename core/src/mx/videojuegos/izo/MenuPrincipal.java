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


//Solo es una de las pantallas de la aplicacion

public class MenuPrincipal implements Screen {

	private static final int ANCHO_MUNDO = 1280;
	private static final int ALTO_MUNDO = 720;

	private final Juego juego;

	//escena en la pantalla
	private Stage escena;

	//Textura para la imagen de fondo
	private Texture texturaFondo;

	//Textura para el titulo
	//private Texture texturaTitulo2;


	//textura para los botones
	private Texture texturaBtnJugar;
	private Texture texturaBtnSettings;
	private Texture texturaBtnAcercaDe;
	private Texture texturaBtnSalir;
	private Texture texturaBtnScore;

	//Administra la carga de assets
	private final AssetManager assetManager = new AssetManager();

	//Camara
	private OrthographicCamera camara;
	private Viewport vista;


	public MenuPrincipal(Juego juego) {
		this.juego = juego;
	}


	public void create() {
		//Se ejecuta al cargar la pantalla
		cargarTexturas();

		escena = new Stage();

		//Habilitar el manejo de eventos
		Gdx.input.setInputProcessor(escena);

		//Calcular ancho y alto
		float ancho = ANCHO_MUNDO;//Gdx.graphics.getWidth();
		float alto = ALTO_MUNDO;//Gdx.graphics.getHeight();

		//fondo
		Image imgFondo = new Image(texturaFondo);

		//Escalar Imagen fondogradlew desktop:run
		float escalaX = ancho / imgFondo.getWidth();
		float escalaY = alto / imgFondo.getHeight();
		imgFondo.setScale(escalaX, escalaY);
		escena.addActor(imgFondo);


		//Agregar botones
		//Jugar
		TextureRegionDrawable trBtnJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnJugar));
		ImageButton btnJugar = new ImageButton(trBtnJugar);
		btnJugar.setPosition((ancho/2)-(btnJugar.getWidth()/2)+13,(alto/2)-btnJugar.getHeight());
		escena.addActor(btnJugar);

		//SeleccionarNivel
		TextureRegionDrawable trBtnSettings = new TextureRegionDrawable(new TextureRegion(texturaBtnSettings));
		ImageButton btnSettings = new ImageButton(trBtnSettings);
		btnSettings.setPosition(ancho/2 - btnSettings.getWidth() +351.3f/2, 0.428f*alto-161);
		escena.addActor(btnSettings);

		//BotonScore
		TextureRegionDrawable trBtnScore = new TextureRegionDrawable(new TextureRegion(texturaBtnScore));
		ImageButton btnScore = new ImageButton(trBtnScore);
		btnScore.setPosition(ancho/2 - btnSettings.getWidth() +360/2+153, 0.428f*alto-162);
		escena.addActor(btnScore);

		//AcercaDe
		TextureRegionDrawable trBtnAcercaDe = new TextureRegionDrawable(new TextureRegion(texturaBtnAcercaDe));
		ImageButton btnAcercaDe = new ImageButton(trBtnAcercaDe);
		btnAcercaDe.setPosition((((ancho/3)*2)+(btnAcercaDe.getWidth()/3)*2)-btnAcercaDe.getWidth()/4+65,(alto/2)-btnAcercaDe.getHeight()+3);
		escena.addActor(btnAcercaDe);

		//Salir
		TextureRegionDrawable trBtnSalir = new TextureRegionDrawable(new TextureRegion(texturaBtnSalir));
		ImageButton btnSalir = new ImageButton(trBtnSalir);
		btnSalir.setPosition(ancho-btnSalir.getWidth()-10,15);
		escena.addActor(btnSalir);

		/*Image imgTitulo2 = new Image(texturaTitulo2);
		imgTitulo2.setPosition(ancho / 2 - imgTitulo2.getWidth()/ 2+188, 0.428f * alto+8);
		escena.addActor(imgTitulo2);*/

		//Registrar listener para atender evento del boton
		btnJugar.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("clicked", "TAP sobre el boton de Jugar");
				juego.setScreen(new PantallaJugar(juego));
			}
		});

		btnScore.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("clicked", "TAP sobre el boton de Score");
				juego.setScreen(new PantallaScore(juego));
			}
		});
		btnSettings.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.log("clicked", "TAP sobre el boton de Settings");
				juego.setScreen(new PantallaSettings(juego));
			}
		});

		btnAcercaDe.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("clicked", "TAP sobre el boton de AcercaDe");
				juego.setScreen(new PantallaAcercaDe(juego));
			}
		});
		btnSalir.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("clicked", "TAP sobre el boton de Salir");
				System.exit(1);
			}
		});

		Gdx.input.setInputProcessor(escena);
	}


	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		escena.setViewport(vista);
		escena.draw();
	}

	private void cargarTexturas() {
		//Textura de fondo
		assetManager.load("MenuDef.png", Texture.class);

		//Textura de botones
		assetManager.load("BtmPlay.png", Texture.class);
		assetManager.load("BtmSettings.png",Texture.class);
		assetManager.load("BtmInfo.png", Texture.class);
		assetManager.load("BtmExit.png",Texture.class);
		assetManager.load("BtmPremios.png",Texture.class);

		//Textura Titulo
		//assetManager.load("titulo1.png",Texture.class);


		//se bloquea hasta cargar los recursos
		assetManager.finishLoading();


		//Cuando termina leemos las texturas
		texturaFondo = assetManager.get("MenuDef.png");

		texturaBtnJugar = assetManager.get("BtmPlay.png");
		texturaBtnSettings = assetManager.get("BtmSettings.png");
		texturaBtnAcercaDe = assetManager.get("BtmInfo.png");
		texturaBtnSalir = assetManager.get("BtmExit.png");
		texturaBtnScore = assetManager.get("BtmPremios.png");
	}

	@Override
	public void show() {
		//CAMARA
		camara = new OrthographicCamera(ANCHO_MUNDO,ALTO_MUNDO);
		camara.position.set(ANCHO_MUNDO/2,ALTO_MUNDO/2,0);
		camara.update();
		vista = new FitViewport(ANCHO_MUNDO,ALTO_MUNDO,camara);

		//Equivale a create
		create();
	}

	@Override
	public void render(float delta) {
		//Equivalente a render
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
		//liberar la memoria utilizada por los recursos
		texturaFondo.dispose();

		texturaBtnJugar.dispose();
		texturaBtnSettings.dispose();
		texturaBtnAcercaDe.dispose();
		texturaBtnSalir.dispose();
		texturaBtnScore.dispose();
		escena.dispose();
	}
}

