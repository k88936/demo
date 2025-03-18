package kvtodev.demo;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.TypingLabel;

public class DialogUI extends Table {


    Image cardA = new Image();
    Image cardB = new Image();



    static final float[] d_x  ={300,200,100};
    static final float[] d_y  ={-300,-200,-100};
    static final float[] d_a  ={-0.5f,-0.25f ,-0.1f};
    static final float count_step = 4;

    static class OneTalk extends Table {
        TypingLabel label = new TypingLabel();
        {
            var game= MyGame.getInstance();
            label.setFont(new Font(game.font1));
            label.setTextSpeed(0.01f);
//            label.setPosition();
            addActor(label);
        }
    }
    DialogUI(){
        super();
        addActor(cardA);
        addActor(cardB);
    }



}
