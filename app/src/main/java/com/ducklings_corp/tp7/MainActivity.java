package com.ducklings_corp.tp7;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

public class MainActivity extends Activity {

    CCGLSurfaceView MainView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        MainView=new CCGLSurfaceView (this);
        setContentView(MainView);
    }

    @Override
    protected void onStart(){
        super.onStart();
        clsGame myGame;
        myGame=new clsGame(MainView);
        myGame.startGame();
    }

    public class clsGame {
        CCGLSurfaceView _GameView;
        CCSize _Screen;
        Sprite _Gaymer;

        public clsGame (CCGLSurfaceView GameView){
            Log.d("clsGame", "clsgame");
            _GameView=GameView;
        }

        public void startGame(){
            Log.d("clsGame", "startgame");

            Director.sharedDirector().attachInView(_GameView);

            _Screen=Director.sharedDirector().displaySize();
            Log.d("clsGame", "Pantalla - Ancho: "+_Screen.getWidth()+" - Alto: "+_Screen.getHeight());

            Scene scene = startScene();
            Director.sharedDirector().runWithScene(scene);
        }

        private Scene startScene(){
            Log.d("clsGame", "start");
            Scene returnScene;
            returnScene=Scene.node();

            Log.d("clsGame", "startScene");
            GameLayer aLayer = new GameLayer();
            returnScene.addChild(aLayer);

            return returnScene;
        }

        class GameLayer extends Layer {

        }

    }

}
