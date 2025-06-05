package za.co.wethinkcode.botworld.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static za.co.wethinkcode.botworld.model.Heading.*;

public class LMSExplorerBotTest
{
    private World world = new World( 3, 3 );

    @Test
    void move_north_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, insideX(), insideY() );
        bot.turnTo( North );
        bot.move();
        assertEquals( insideY() - 1, bot.position().y() );
    }

    @Test
    void move_south_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, insideX(), insideY() );
        bot.turnTo( South );
        bot.move();
        assertEquals( insideY() + 1, bot.position().y() );
    }

    @Test
    void move_west_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, insideX(), insideY() );
        bot.turnTo( West );
        bot.move();
        assertEquals( insideX() - 1, bot.position().x() );
    }

    @Test
    void move_east_insideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, insideX(), insideY() );
        bot.turnTo( East );
        bot.move();
        assertEquals( insideX() + 1, bot.position().x() );
    }

    @Test
    void move_north_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, insideX(), 0 );
        bot.turnTo( North );
        bot.move();
        assertEquals( 0, bot.position().y() );
    }

    @Test
    void move_south_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, insideX(), world.southEdge() );
        bot.turnTo( South );
        bot.move();
        assertEquals( world.southEdge(), bot.position().y() );
    }

    @Test
    void move_west_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, 0, insideY() );
        bot.turnTo( West );
        bot.move();
        assertEquals( 0, bot.position().x() );
    }

    @Test
    void move_east_outsideWorld(){
        ExplorerBot bot =  new ExplorerBot( world );
        world.add( bot, world.eastEdge(), insideY() );
        bot.turnTo( East );
        bot.move();
        assertEquals( world.eastEdge(), bot.position().x() );
    }

    // Answer a point somewhere near the middle of the world's N-S extent
    private int insideY(){
        return world.height() / 2;
    }

    // Answer a point somewhere near the middle of the world's N-S extent
    private int insideX(){
        return world.width() / 2;
    }
}