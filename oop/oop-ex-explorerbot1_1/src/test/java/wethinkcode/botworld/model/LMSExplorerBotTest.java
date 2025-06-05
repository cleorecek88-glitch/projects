package wethinkcode.botworld.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static wethinkcode.botworld.model.Heading.*;

public class LMSExplorerBotTest
{
    private static final int X_INSIDE = ExplorerBot.WORLD_MAX_X / 2;
    private static final int Y_INSIDE = ExplorerBot.WORLD_MAX_Y / 2;

    @Test
    void createBot_outsideWorld_xTooSmall(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( -1, Y_INSIDE ));
    }

    @Test
    void createBot_outsideWorld_xTooLarge(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( ExplorerBot.WORLD_MAX_X + 1, 0 ));
    }

    @Test
    void createBot_outsideWorld_yTooSmall(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( X_INSIDE, -1 ));
    }

    @Test
    void createBot_outsideWorld_yTooLarge(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( X_INSIDE, ExplorerBot.WORLD_MAX_Y + 1 ));
    }

    @Test
    void createBot_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( X_INSIDE, Y_INSIDE );
    }

    @Test
    void move_north_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( X_INSIDE, Y_INSIDE );
        bot.turnTo( N );
        bot.move();
        assertEquals( Y_INSIDE - 1, bot.position().y() );
    }

    @Test
    void move_south_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( X_INSIDE, Y_INSIDE );
        bot.turnTo( S );
        bot.move();
        assertEquals( Y_INSIDE + 1, bot.position().y() );
    }

    @Test
    void move_west_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( X_INSIDE, Y_INSIDE );
        bot.turnTo( W );
        bot.move();
        assertEquals( X_INSIDE - 1, bot.position().x() );
    }

    @Test
    void move_east_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( X_INSIDE, Y_INSIDE );
        bot.turnTo( E );
        bot.move();
        assertEquals( X_INSIDE + 1, bot.position().x() );
    }

    @Test
    void move_north_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( X_INSIDE, 0 );
        bot.turnTo( N );
        bot.move();
        assertEquals( 0, bot.position().y() );
    }

    @Test
    void move_south_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( X_INSIDE, ExplorerBot.WORLD_MAX_Y );
        bot.turnTo( S );
        bot.move();
        assertEquals( ExplorerBot.WORLD_MAX_Y, bot.position().y() );
    }

    @Test
    void move_west_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( 0, Y_INSIDE );
        bot.turnTo( W );
        bot.move();
        assertEquals( 0, bot.position().x() );
    }

    @Test
    void move_east_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( ExplorerBot.WORLD_MAX_X, Y_INSIDE );
        bot.turnTo( E );
        bot.move();
        assertEquals( ExplorerBot.WORLD_MAX_X, bot.position().x() );
    }
}