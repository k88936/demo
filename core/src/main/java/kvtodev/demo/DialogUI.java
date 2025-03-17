package kvtodev.demo;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class DialogUI extends Group {


    private boolean continueDialog= false;
    DialogUI(){
        super();
        addListener( new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                continueDialog = true;
                return false;
            }
        });


    }

}
