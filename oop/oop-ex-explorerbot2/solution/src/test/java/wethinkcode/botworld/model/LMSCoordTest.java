package wethinkcode.botworld.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LMSCoordTest
{
    @Test
    void right(){
        Position p = new Position( 0, 0 );
        Position result = p.right();
        assertEquals( 1, result.x() );
        assertNotEquals( p, result );
    }

    @Test
    void decrementX(){
        Position p = new Position( 0, 0 );
        Position result = p.left();
        assertEquals( -1, result.x() );
        assertNotEquals( p, result );
    }

    @Test
    void down(){
        Position p = new Position( 0, 0 );
        Position result = p.down();
        assertEquals( 1, result.y() );
        assertNotEquals( p, result );
    }

    @Test
    void up(){
        Position p = new Position( 0, 0 );
        Position result = p.up();
        assertEquals( -1, result.y() );
        assertNotEquals( p, result );
    }

    @Test
    void equalPositionsAreEqualButNotTheSame(){
        Position p1 = new Position( 42, 55 );
        Position p2 = new Position( 42, 55 );
        assertEquals( p1, p2 );
        assertEquals( p1.hashCode(), p2.hashCode() );
        assertNotSame( p1, p2 );
    }
}
