package wethinkcode.botworld.model;

public class ExplorerBot
{
    public static final int WORLD_MAX_X = 10;
    public static final int WORLD_MAX_Y = 10;

    private Position position;
    private Heading heading = Heading.N;

    public ExplorerBot( int initialX, int initialY ){
        position = new Position( initialX, initialY );
        if( ! worldContains( position ) ) throw new IllegalArgumentException();
    }

    /**
     * Turn the receiver bot to a new heading.
     * @param newHeading Desired heading.
     */
    public void turnTo( Heading newHeading ){
        heading = newHeading;
    }

    /**
     * Answer the receiver bot's current heading.
     * @return a Heading
     */
    public Heading heading(){
        return heading;
    }

    /**
     * Answer the receiver's current position in the world.
     * @return The receiver's current position.
     */
    Position position(){
        return position;
    }

    /**
     * Move the receiver bot 1 klik in the direction it currently faces but not
     * beyond the edges of the world.
     */
    public void move(){
        Position newPosition = switch( heading() ){
            case N -> position().up();
            case S -> position().down();
            case W -> position().left();
            case E -> position().right();
        };
        if( worldContains( newPosition ) ) position = newPosition;
    }

    private boolean worldContains( Position aCoord ){
        return aCoord.x() >= 0
            && aCoord.x() <= WORLD_MAX_X
            && aCoord.y() >= 0
            && aCoord.y() <= WORLD_MAX_Y;
    }
}
