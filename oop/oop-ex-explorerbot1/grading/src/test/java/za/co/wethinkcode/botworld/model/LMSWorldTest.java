package za.co.wethinkcode.botworld.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class LMSWorldTest
{
    @Test
    void createWorld_negativeWidthOrHeightIsIllegal(){
        assertThrows( AssertionError.class, () -> new World( -10, 10 ));
        assertThrows( AssertionError.class, () -> new World( 10, -10 ));
    }

    @Test
    void createWorld_zeroWidthOrHeightIsIllegal(){
        assertThrows( AssertionError.class, () -> new World( 0, 10 ));
        assertThrows( AssertionError.class, () -> new World( 10, 0 ));
    }

    // It's a small world after all...
    // If this test runs without any exceptions or errors, that's the success we're looking for!
    @Test
    void createWorld_1x1SizeIsOK(){
        new World( 1, 1 );
    }

    @Test
    void createWorld_moreRealisticSize(){
        // If this test runs without any exceptions or errors, that's the success we're looking for!
        assertThrows( AssertionError.class, () -> new World( 10, 0 ));
    }

    @Test
    void worldContainsALocation_insideTheWorld(){
        World w = new World( 10, 10 );
        for( int x = 0; x < 10 ; x++ ){
            for( int y = 0; y < 10; y++ ){
                assertTrue( w.contains( new Coord( x, y )));
            }
        }
    }

    @Test
    void worldContainsALocation_tooFarNorth(){
        World w = new World( 10, 10 );
        assertFalse( w.contains( new Coord( 5, -1 )));
    }

    @Test
    void worldContainsALocation_tooFarSouth(){
        World w = new World( 10, 10 );
        assertFalse( w.contains( new Coord( 5, 10 )));
    }

    @Test
    void worldContainsALocation_tooFarWest(){
        World w = new World( 10, 10 );
        assertFalse( w.contains( new Coord( -1, 5 )));
    }

    @Test
    void worldContainsALocation_tooFarEast(){
        World w = new World( 10, 10 );
        assertFalse( w.contains( new Coord( 10, 5 )));
    }

    @Test
    void addBot_botIsOutsideTheWorld(){
        World world = new World( 1, 1 );
        ExplorerBot bot1 = new ExplorerBot( world );
        ExplorerBot bot2 = new ExplorerBot( world );
        assumeTrue( world.botCount() == 0 );

        world.add( bot1, 0, 0 );
        assertThrows( IllegalArgumentException.class, () -> world.add( bot2, 0, -1 ));
        assertEquals( 1, world.botCount() );
    }

    @Test
    void addBot_botIsInsideTheWorld(){
        World world = new World( 1, 1 );
        ExplorerBot b = new ExplorerBot( world );
        world.add( b, 0, 0 );
        assertEquals( 1, world.botCount() );
    }
}
