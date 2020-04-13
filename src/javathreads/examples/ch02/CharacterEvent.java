package javathreads.examples.ch02;

/**
 * @author csq
 * @date 2020/1/15 10:09
 * @description
 **/
public class CharacterEvent {
    public CharacterSource source;
    public int character;
    public CharacterEvent (CharacterSource cs, int c){
        source = cs;
        character = c;
    }
}
