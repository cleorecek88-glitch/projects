package za.co.wethinkcode.botworld.model;


import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static za.co.wethinkcode.botworld.model.Heading.*;

// Note that we don't need to explicitly test the `setFuelPerKlik` and `getFuelPerKlik` methods:
// they're implicitly tested just by being used in other tests. Don't write unnecessary and
// meaningless tests.
public class LMSExplorerBotFuelTest extends LMSAbstractBotTest
{
    LMSExplorerBotFuelTest(){
        super( 10, 10 );
    }

    @Test
    void maxFuelIs100(){
        ExplorerBot bot = new ExplorerBot( world, world.midPoint(), ExplorerBot.MAX_FUEL );
        bot.addFuel( 1f );
        Assertions.assertEquals( ExplorerBot.MAX_FUEL, bot.fuelLevel() );
    }

    @Test
    void minFuelIs0(){
        ExplorerBot bot = new ExplorerBot( world, world.midPoint(), ExplorerBot.MIN_FUEL );
        bot.addFuel( -1f );
        Assertions.assertEquals( 0f, bot.fuelLevel() );
    }

    @Test
    void movingConsumesFuel_LegalMoveN(){
        fuelTest( new ExplorerBot( world, world.midPoint(), 100f ),
            Heading.North,
            Matchers.lessThan( 100f ));
    }

    @Test
    void movingConsumesFuel_LegalMoveS(){
        fuelTest( new ExplorerBot( world, world.midPoint(), 100f ),
            Heading.South,
            Matchers.lessThan( 100f ));
    }

    @Test
    void movingConsumesFuel_LegalMoveE(){
        fuelTest( new ExplorerBot( world, world.midPoint(), 100f ),
            Heading.East,
            Matchers.lessThan( 100f ));
    }

    @Test
    void movingConsumesFuel_LegalMoveW(){
        fuelTest( new ExplorerBot( world, world.midPoint(), 100f ),
            Heading.West,
            Matchers.lessThan( 100f ));
    }

    @Test
    void movingConsumesFuel_IllegalMoveN(){
        fuelTest( new ExplorerBot( world, world.midPoint().x(), world.northEdge(), 100f ),
            Heading.North,
            Matchers.equalTo( 100f ));
    }

    @Test
    void movingConsumesFuel_IllegalMoveS(){
        fuelTest( new ExplorerBot( world, world.midPoint().x(), world.southEdge(), 100f ),
            Heading.South,
            Matchers.equalTo( 100f ));
    }

    @Test
    void movingConsumesFuel_IllegalMoveE(){
        fuelTest( new ExplorerBot( world, world.eastEdge(), world.midPoint().y(), 100f ),
            Heading.East,
            Matchers.equalTo( 100f ));
    }

    @Test
    void movingConsumesFuel_IllegalMoveW(){
        fuelTest( new ExplorerBot( world, world.westEdge(), world.midPoint().y(), 100f ),
            Heading.West,
            Matchers.equalTo( 100f ));
    }

    @Test
    void turningConsumesNoFuel(){
        ExplorerBot bot = new ExplorerBot( world, world.midPoint(), 100f );
        bot.turnTo( Heading.North );
        bot.turnTo( Heading.South );
        bot.turnTo( Heading.East );
        bot.turnTo( Heading.West );
        MatcherAssert.assertThat( bot.fuelLevel(), Matchers.equalTo( 100f ) );
    }

    @Test
    void lowFuelMeansNoMoving(){
        ExplorerBot bot = new ExplorerBot( world, world.midPoint(), 1f );
        bot.setFuelConsumptionPerKlik( 2f );
        bot.move();
        Assertions.assertEquals( world.midPoint(), bot.position() );
    }

    private void fuelTest( ExplorerBot aBot, Heading hdg, Matcher<Float> expectation ){
        aBot.turnTo( hdg );
        aBot.move();
        MatcherAssert.assertThat( aBot.fuelLevel(), expectation );
    }
}
