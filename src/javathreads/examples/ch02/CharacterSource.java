package javathreads.examples.ch02;

/**
 * @author： csq
 * @date ：2020/1/15 10:02
 * @description：
 **/
public interface CharacterSource {

    void addCharacterListener(CharacterListener cl);
    void removeCharacterListener(CharacterListener cl);
    void nextCharacter();
}
