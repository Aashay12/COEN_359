# Dungeon Project - PDP (COEN 359)

# Summary
- The project is about implementing a MedievalLevelBuilder class that can be used to construct levels for a game set in a medieval fantasy world. The builder class is responsible for creating the game levels and ensuring that all the details of the game are consistent.

- The MedievalLevelBuilder class should allow the game developers to create levels by adding rooms, monsters, and treasures. It enforces constraints such as the maximum number of rooms, monsters, and treasures allowed in a level.

- The class should provide methods to add different types of monsters (such as goblins, orcs, ogres, and humans) to specific rooms, as well as methods to add different types of treasures (such as potions, gold, weapons, and special treasures) to specific rooms.

- The builder class should throw exceptions when the maximum number of monsters or treasures is reached, or when attempting to add monsters or treasures to a room that hasn't been created yet. It should also throw an exception if the build method is called before the level is fully constructed.



## This code implements the Builder pattern in Java. 
The Builder pattern is a type of a creative design pattern that allows the construction of complex objects step by step. 
It separates the construction of an object from its representation, allowing the same construction process to create different representations.

# All the implementaion of the code can be done by running the testcases. 

## MedievalLevelBuilder.java

The MedievalLevelBuilder class serves as the builder. It provides methods for constructing a Level object in a step-by-step manner. 
- Here's how the Builder pattern is used in this code:
- The MedievalLevelBuilder class has a private Level object named level, which represents the object being constructed.
- The constructor of MedievalLevelBuilder initializes the necessary parameters for building the Level object.
- The builder provides methods such as addRoom, addMonster, and addTreasure for adding components to the Level object.
- Each method in the builder returns an instance of the MedievalLevelBuilder itself (return this;). This allows method chaining, where multiple methods can be called sequentially on the same instance of the builder.
- The build method is used to finalize the construction process and return the constructed Level object. It performs a final sanity check to ensure that all required components have been added.

By using the Builder pattern, the code provides a structured and flexible way to construct a Level object. It allows the client code to create a MedievalLevelBuilder instance and gradually add components to build a complete Level object. The builder encapsulates the construction logic and ensures that the object is constructed in a valid state. It also simplifies the client code by providing a clear and readable API for constructing complex objects.






