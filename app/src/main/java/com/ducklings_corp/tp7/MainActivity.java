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
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;




public class MainActivity extends Activity {

    CCGLSurfaceView MainView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        MainView = new CCGLSurfaceView(this);
        setContentView(MainView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        clsGame myGame;
        myGame = new clsGame(MainView);
        myGame.startGame();
    }

    public class clsGame {
        CCGLSurfaceView _GameView;
        CCSize _screen;
        Sprite _gaymer;

        public clsGame(CCGLSurfaceView GameView) {
            Log.d("clsGame", "clsgame");
            _GameView = GameView;
        }

        public void startGame() {
            Log.d("clsGame", "startgame");

            Director.sharedDirector().attachInView(_GameView);

            _screen = Director.sharedDirector().displaySize();
            Log.d("clsGame", "Pantalla - Ancho: " + _screen.getWidth() + " - Alto: " + _screen.getHeight());

            Scene scene = startScene();
            Director.sharedDirector().runWithScene(scene);
        }

        private Scene startScene() {
            Log.d("clsGame", "start");
            Scene returnScene;
            returnScene = Scene.node();

            Log.d("clsGame", "startScene");
            GameLayer aLayer = new GameLayer();
            returnScene.addChild(aLayer);

            return returnScene;
        }

        class GameLayer extends Layer {
            public GameLayer() {
                place();
            }

            void place() {
                _gaymer = Sprite.sprite("gaymer.jpg");
                CCPoint initialPos = new CCPoint();
                initialPos.x = (float)Math.random() * _screen.getWidth();
                initialPos.y = (float)Math.random() * _screen.getHeight();
                _gaymer.setPosition(initialPos.x,initialPos.y);

                findCorner(initialPos);

                super.addChild(_gaymer);
            }
        }
        CCPoint findCorner(CCPoint point) {
            int x = (int)point.x, y = (int)point.y;
            CCPoint[] corners = {
                    CCPoint.make(0f,_screen.height),
                    CCPoint.make(_screen.width,_screen.height),
                    CCPoint.make(0,  0),
                    CCPoint.make(_screen.width,  0),
            };
            int farthest = -1;
            float distance = 0;
            for(int i = 0; i < 4;i++) {
                float cornerX = corners[i].x,cornerY = corners[i].y;
                float dst = pythagoras( cornerX-x ,cornerY-y  );
                if(dst>distance) {
                    distance = dst;
                    farthest = i;
                }
            }
            return corners[farthest];
        }

    }



    float pythagoras(float w,float h) {
        return (float)Math.sqrt(w*w+h*h);
    }

}
