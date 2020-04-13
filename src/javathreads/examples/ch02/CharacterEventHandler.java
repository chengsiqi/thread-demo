package javathreads.examples.ch02;

import java.util.Vector;

/**
 * @author csq
 * @date 2020/1/15 10:24
 * @description
 **/
public class CharacterEventHandler {

    private Vector listeners = new Vector();

    public void addCharacterListener(CharacterListener cl){
        listeners.add(cl);
    }

    public void removeCharacterListener(CharacterListener cl){
        listeners.remove(cl);
    }

    public void fireNewCharacter(CharacterSource source, int c){
        CharacterEvent ce = new CharacterEvent(source, c);
        CharacterListener[] cl = (CharacterListener[]) listeners.toArray(new CharacterListener[0]);
        for(int i = 0; i < cl.length; i++){
            cl[i].newCharacter(ce);
        }
    }
}
