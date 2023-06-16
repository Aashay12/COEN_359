package dungeon;

/**
 * The class Medieval level builder that can be used to create a level in a medieval game
 */
public class MedievalLevelBuilder {

    private Level level;
    private int num_levels;
    private int num_rooms, curr_avail_rooms;
    private int num_monsters, curr_avail_monsters;
    private int num_treasures, curr_avail_treasure;

    /**
     * Create a new object of MedievalLevelBuilder type
     *
     * @param num_levels the num of levels
     * @param num_rooms the number of rooms
     * @param num_monsters the number of monsters
     * @param num_treasures the number of treasures
     *
     * @throws IllegalArgumentException if sanity checks fail
     */
    public MedievalLevelBuilder(int num_levels, int num_rooms, int num_monsters, int num_treasures) throws IllegalArgumentException{

        if (num_levels < 0 || num_rooms < 0 || num_monsters < 0 || num_treasures < 0){
            throw new IllegalArgumentException("All provided inputs need to be greater than 0!!");
        }

        this.num_levels = num_levels;
        this.num_rooms = num_rooms;
        this.curr_avail_rooms = num_rooms;
        this.num_monsters = num_monsters;
        this.curr_avail_monsters = num_monsters;
        this.num_treasures = num_treasures;
        this.curr_avail_treasure = num_treasures;

        level = new Level(this.num_levels);

    }

    /**
     * A level can have many rooms (1L -> MR)
     */
    public MedievalLevelBuilder addRoom(String description){
        if (curr_avail_rooms > 0) {
            level.addRoom(description);
            this.curr_avail_rooms = this.curr_avail_rooms - 1;
        }else{
            throw new IllegalStateException("The level has reached maximum number of rooms! Cannot add anymore rooms!");
        }
        return this;
    }

    /**
     * Common method to add any type of monster
     *
     * @param room_number the room number where the monster is to be added
     * @param monster of Monster type
     *
     * @throws IllegalStateException if sanity checks fail
     * @throws IllegalArgumentException if sanity checks fail
     */
    public void addMonster(int room_number, Monster monster) throws IllegalStateException, IllegalArgumentException{

        if (this.curr_avail_monsters <= 0){
            throw new IllegalStateException("No more monsters available to add!");
        }

        if ((room_number < 0 || room_number > this.num_rooms) || (room_number >= (this.num_rooms - this.curr_avail_rooms))) {
            throw new IllegalArgumentException("Room number is out of bounds!");
        }

        level.addMonster(room_number, monster);
        this.curr_avail_monsters = this.curr_avail_monsters - 1;
    }

    /**
     * Add a monster -> 'goblin'
     *
     * @param room_number the room number where the monster is to be added
     * @param num_goblins the number of goblins
     *
     * @return MedievalLevelBuilder instance
     */
    public MedievalLevelBuilder addGoblins(int room_number, int num_goblins){

        if (num_goblins <= 0){
            throw new IllegalArgumentException("Number of Goblins has to be positive!");
        }

        Monster goblin = new Monster("goblin", "mischievous and very unpleasant, vengeful, and greedy creature whose primary purpose is to cause trouble to humankind", 7);

        for (int i = 0; i < num_goblins; i++) {
            this.addMonster(room_number, goblin);
        }

        return this;
    }

    /**
     * Add a monster -> 'orc'
     *
     * @param room_number the room number where the monster is to be added
     *
     * @return MedievalLevelBuilder instance
     */
    public MedievalLevelBuilder addOrc(int room_number){

        Monster orc = new Monster("orc", "brutish, aggressive, malevolent being serving evil", 20);

        this.addMonster(room_number, orc);

        return this;
    }

    /**
     * Add a monster -> 'ogre'
     *
     * @param room_number the room number where the monster is to be added
     *
     * @return MedievalLevelBuilder instance
     */
    public MedievalLevelBuilder addOgre(int room_number){

        Monster ogre = new Monster("ogre", "large, hideous man-like being that likes to eat humans for lunch", 50);

        this.addMonster(room_number, ogre);

        return this;
    }

    /**
     * Add a monster -> 'human'
     *
     * @param room_number the room number where the monster is to be added
     * @param name the name of the human monster
     * @param description the description of the human monster
     * @param hit_points the hitpoints of the human monster
     *
     * @return MedievalLevelBuilder instance
     */
    public MedievalLevelBuilder addHuman(int room_number, String name, String description, int hit_points){

        if (name == null || name.isBlank() || name.equals("") ||  description == null  || description.isBlank() || hit_points <= 0){
            throw new IllegalArgumentException("Arguments provided are not appropriate, please verify them!");
        }

        Monster human = new Monster(name, description, hit_points);
        this.addMonster(room_number, human);

        return this;
    }

    /**
     * Common method to add any type of treasure
     *
     * @param room_number the room number where the treasure is to be added
     * @param treasure of Treasure type
     *
     * @throws IllegalStateException if sanity checks fail
     * @throws IllegalArgumentException if sanity checks fail
     */
    private void addTreasure(int room_number, Treasure treasure) {

        if (this.curr_avail_treasure <= 0){
            throw new IllegalStateException("No more treasures available to add!");
        }

        if ((room_number < 0 || room_number > this.num_rooms) || (room_number >= (this.num_rooms - this.curr_avail_rooms))) {
            throw new IllegalArgumentException("Room number is out of bounds!");
        }

        level.addTreasure(room_number, treasure);
        this.curr_avail_treasure = this.curr_avail_treasure - 1;
    }

    /**
     * Add a treasure -> 'potion'
     *
     * @param room_number the room number where the treasure is to be added
     *
     * @return MedievalLevelBuilder instance
     */
    public MedievalLevelBuilder addPotion(int room_number) {

        Treasure treasure = new Treasure("a healing potion", 1);

        this.addTreasure(room_number, treasure);
        return this;
    }

    /**
     * Add a treasure -> 'gold'
     *
     * @param room_number the room number where the treasure is to be added
     * @param value the value of the treasure
     *
     * @return MedievalLevelBuilder instance
     */
    public MedievalLevelBuilder addGold(int room_number, int value) {

        if (value <= 0){
            throw new IllegalArgumentException("Value of the treasure has to be greater than 0!");
        }

        Treasure treasure = new Treasure("pieces of gold", value);

        this.addTreasure(room_number, treasure);
        return this;
    }

    /**
     * Add a treasure -> 'weapon'
     *
     * @param room_number the room number where the treasure is to be added
     * @param weapon the description of the treasure
     *
     * @return MedievalLevelBuilder instance
     */
    public MedievalLevelBuilder addWeapon(int room_number, String weapon) {

        if (weapon.isBlank() || weapon.equals("")){
            throw new IllegalArgumentException("There has to be some detail of the weapon!");
        }

        Treasure treasure = new Treasure(weapon, 10);

        this.addTreasure(room_number, treasure);
        return this;
    }

    /**
     * Add a treasure -> 'special'
     *
     * @param room_number the room number where the treasure is to be added
     * @param special the description of the treasure
     * @param value the value of the treasure
     *
     * @return MedievalLevelBuilder instance
     *
     * @throws IllegalArgumentException if sanity checks fail
     */
    public MedievalLevelBuilder addSpecial(int room_number, String special, int value) throws IllegalArgumentException{

        if (special.isBlank() || value <= 0){
            throw new IllegalArgumentException("Description cannot be blank and the value has to be greater than 0!");
        }

        Treasure treasure = new Treasure(special, value);

        this.addTreasure(room_number, treasure);
        return this;
    }

    /**
     * Build the level and return it if everything is acceptable
     *
     * @return Level
     *
     * @throws IllegalStateException if sanity checks fail
     */
    public Level build() throws IllegalStateException {

        if (this.curr_avail_rooms == 0 && this.curr_avail_monsters == 0 && this.curr_avail_treasure == 0) {
            return this.level;
        }else {
            throw new IllegalStateException("Either of rooms, monsters or treasures are still available to fill!");
        }
    }
}