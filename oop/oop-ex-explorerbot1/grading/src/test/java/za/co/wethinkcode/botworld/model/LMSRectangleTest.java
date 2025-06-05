package za.co.wethinkcode.botworld.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LMSRectangleTest
{
    @Test
    void topLeftIsAlwaysLessThanBottomRight(){
        Coord p1 = new Coord( 10, 10 );
        Coord p2 = new Coord( 0, 10 );

        Rectangle r1 = new Rectangle( p1, p2 );
        assertTrue( r1.top() <= r1.bottom() );
        assertTrue( r1.left() <= r1.right() );

        Rectangle r2 = new Rectangle( p2, p1 );
        assertTrue( r2.top() <= r2.bottom() );
        assertTrue( r2.left() <= r2.right() );
    }

    @Test
    void createRectArea_singlePointAreaIsOK(){
        Coord p1 = new Coord( 10, 10 );
        Coord p2 = new Coord( 10, 10 );
        new Rectangle( p1, p2 );
    }

    @Test
    void whatIsInsideTheRectangle(){
        Rectangle r = new Rectangle( 0, 0, 10, 10 );
        assertTrue( r.contains( new Coord( 5, 5 )));
        assertTrue( r.contains( new Coord( 0, 0 )));
        assertTrue( r.contains( new Coord( 10, 10 )));
        assertFalse( r.contains( new Coord( -1, 5 )));
        assertFalse( r.contains( new Coord( 5, -1 )));
        assertFalse( r.contains( new Coord( 11, 5 )));
        assertFalse( r.contains( new Coord( 5, 11 )));
    }
}
