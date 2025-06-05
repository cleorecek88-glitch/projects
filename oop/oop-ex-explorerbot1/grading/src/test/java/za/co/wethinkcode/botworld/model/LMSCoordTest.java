package za.co.wethinkcode.botworld.model;

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
    void additionTest(){
        Coord p1 = new Coord( 5, 5 );
        Coord p2 = p1.add( new Coord( 5, -5 ));
        assertEquals( new Coord( 10, 0 ), p2 );
    }

    @Test
    void subtractionTest(){
        Coord p1 = new Coord( 5, 5 );
        Coord p2 = p1.minus( new Coord( 5, -5 ));
        assertEquals( new Coord( 0, 10 ), p2 );
    }

    @Test
    void multiplicationTest(){
        Coord p1 = new Coord( 5, 5 );
        Coord p2 = p1.multiplyBy( 3 );
        assertEquals( new Coord( 15, 15 ), p2 );
    }

    @Test
    void divisionTest(){
        Coord p1 = new Coord( 5, 5 );
        Coord p2 = p1.divideBy( 2 );
        assertEquals( new Coord( 2, 2 ), p2 );
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
