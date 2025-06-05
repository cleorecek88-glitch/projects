package za.co.wethinkcode.botworld.model;

abstract class LMSAbstractBotTest
{
    protected World world;

    protected LMSAbstractBotTest( int worldWidth, int worldHeight ){
        world = new World( worldWidth, worldHeight );
    }

    protected int insideX(){
        return world.midPoint().x();
    }

    protected int insideY(){
        return world.midPoint().y();
    }
}
