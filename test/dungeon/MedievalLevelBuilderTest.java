package dungeon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedievalLevelBuilderTest {

    MedievalLevelBuilder test_obj;

    @Before
    public void setUp() throws Exception {
        test_obj = new MedievalLevelBuilder(1, 2, 5, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorInvalidInput_1() {
        new MedievalLevelBuilder(-1, 2, 9, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorInvalidInput_2() {
        new MedievalLevelBuilder(1, -10, 9, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorInvalidInput_3() {
        new MedievalLevelBuilder(1, 2, -9, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorInvalidInput_4() {
        new MedievalLevelBuilder(1, 2, 9, -14);
    }

    @Test
    public void addRoom() {
        test_obj = new MedievalLevelBuilder(1, 3, 5, 6);
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").addRoom("2").getClass());
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").getClass());
    }

    @Test
    public void addMonster() {
        test_obj.addRoom("Room 1").addGoblins(0, 5);
        assertEquals(MedievalLevelBuilder.class, test_obj.getClass());
    }

    @Test
    public void addGoblins() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").addGoblins(0, 5).getClass());
    }

    @Test
    public void addOrc() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").addOrc(0).getClass());
    }

    @Test
    public void addOgre() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").addOgre(0).getClass());
    }

    @Test
    public void addHuman() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").addHuman(0, "Evil", "Scary Human", 12).getClass());
    }

    @Test
    public final void testAddHumanCombined() {
        test_obj = new MedievalLevelBuilder(1, 2, 4, 5);
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").addRoom("Room 2")
                        .addHuman(0, "Evil", "Human", 10)
                        .addGoblins(1, 1)
                        .addOgre(0)
                        .addOrc(0).getClass());
    }

    @Test
    public void addPotion() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 1").addPotion(0).getClass());
    }

    @Test
    public void addGold() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 0").addGold(0, 52).getClass());
    }

    @Test
    public void addWeapon() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 0").addWeapon(0, "Shotgun").getClass());
    }

    @Test
    public void addSpecial() {
        assertEquals(MedievalLevelBuilder.class, test_obj.addRoom("Room 0").addSpecial(0, "NFT", 101).getClass());
    }

    @Test
    public void testBuildSuccess() {

        test_obj = new MedievalLevelBuilder(1, 3, 6, 5);

        test_obj.addRoom("Room 1").addRoom("Room 2").addRoom("Room 3");
        test_obj.addGoblins(0, 3);
        test_obj.addOrc(1);
        test_obj.addOgre(1);
        test_obj.addHuman(2, "Evil", "Human", 12);
        test_obj.addPotion(2).addPotion(1);
        test_obj.addGold(0, 52);
        test_obj.addWeapon(1, "Shotgun");
        test_obj.addSpecial(1, "NFT", 101);

        assertEquals(Level.class, test_obj.build().getClass());
    }

    @Test(expected = IllegalStateException.class)
    public final void testBuildFail() {

        test_obj = new MedievalLevelBuilder(1, 3, 6, 5);

        test_obj.addRoom("Room 1").addRoom("Room 2").addRoom("Room 3");
        test_obj.addGoblins(0, 3);
        test_obj.addOrc(1);
        test_obj.addOgre(1).addOgre(2);
        test_obj.addHuman(2, "Evil", "Human", 12);
        test_obj.addPotion(2).addPotion(1);
        test_obj.addGold(0, 52);
        test_obj.addWeapon(1, "Shotgun");
        test_obj.addSpecial(1, "NFT", 101);

        assertEquals(Level.class, test_obj.build().getClass());
    }
}