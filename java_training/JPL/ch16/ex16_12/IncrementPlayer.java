package ch16.ex16_12;

import ch16.ex16_12.Game.Reply;

public class IncrementPlayer extends Player
{

    @Override
    public int strategy(int previous, Reply result)
    {
        switch (result)
        {
            case EQUAL:
                return previous;
            case GREATER:
                return previous - 1;
            case LESS:
                return previous + 1;
            default:
                return previous;
        }
    }

}
