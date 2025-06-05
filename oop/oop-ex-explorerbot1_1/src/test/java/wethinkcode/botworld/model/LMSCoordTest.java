package wethinkcode.botworld.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LMSCoordTest
{
    @Test
    void incrementX(){
        Coord p = new Coord( 0, 0 );
        Coord result = p.incrementX();
        assertEquals( 1, result.x() );
        assertNotEquals( p, result );
    }

    @Test
    void decrementX(){
        Coord p = new Coord( 0, 0 );
        Coord result = p.decrementX();
        assertEquals( -1, result.x() );
        assertNotEquals( p, result );
    }

    @Test
    void incrementY(){
        Coord p = new Coord( 0, 0 );
        Coord result = p.incrementY();
        assertEquals( 1, result.y() );
        assertNotEquals( p, result );
    }

    @Test
    void decrementY(){
        Coord p = new Coord( 0, 0 );
        Coord result = p.decrementY();
        assertEquals( -1, result.y() );
        assertNotEquals( p, result );
    }

    @Test
    void equalCoordsAreEqualButNotTheSame(){
        Coord p1 = new Coord( 42, 55 );
        Coord p2 = new Coord( 42, 55 );
        assertEquals( p1, p2 );
        assertEquals( p1.hashCode(), p2.hashCode() );
        assertNotSame( p1, p2 );
    }
}
