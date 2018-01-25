# Toll Parking Library

The Toll Parking Library represents a java API to simulate a Toll Parking behavior.


## Requirements
A toll parking contains multiple **parking slots** of different types :
 - the standard parking slots for sedan cars (gasoline-powered)
 - parking slots with 20kw power supply for electric cars
 - parking slots with 50kw power supply for electric cars

20kw electric cars cannot use 50kw power supplies and vice-versa.

Every Parking is free to implement it's own pricing policy :
- Some only bills their customer for each hour spent in the parking (nb hours * hour price)
- Some other bill a fixed amount + each hour spent in the parking (fixed amount + nb hours * hour price)

In the future, there will be **others pricing policies**.

Cars of all types come in and out randomly, the API must :
- Send them to the right parking slot of refuse them if there is no slot (of the right type) left.
- Mark the parking slot as Free when the car leaves it
- Bill the customer when the car leaves.

Wanted outcome :
- A Java API ready to use, tested, documented (how to build from sources, how to use the API).


## Design Choices

### Identify the API contract

The first thing to do is to understand what the API should do and to identify the use cases it will fulfill.
As requested, the API should simulate a toll car parking.
The requirements are quite straightforward, they say the parking should do 3 things when the cars arrive to the parking:

```
- Send them to the right parking slot of refuse them if there is no slot (of the right type) left.
```

The first requirement represents the first use case: **a car arrives to the parking**. In this case, the parking should indicate the right parking spot
or refuse it if no place left. The first API method could be:

**carCheckIn** which parks the car and return the parking spot or reject the car by throwing an exception.
 
```
- Mark the parking slot as free when the car leaves it
```
The second requirement doesn't really represent a use case for the external API use. It's more like an internal update, so it won't be
exposed by the API, but it give us a hint on the next use case: **a car leaves the parking**. This second use case is related to the last 
requirement:
```
- Bill the customer when the car leaves.
```
Indeed, the value action for the external API user is to have the bill when the car leaves. The bill is issued only when the car leaves the parking. This lead us to the second API method: 
**carCheckOut** which issues the bill when the car leaves the parking.

Of course, we can imagine a lot more methods of the API like bill details, reports, statistics etc... but the requirement doesn't ask for more.
 
### Identify the API objects
The API objects are created by identifying their data and responsibilities (behavior).

#### IParking 
- represents the facade object, it exposes the above external API methods to the outside world.
- the parking has different parking slots and it has the visibility over the parked cars and the parking slots.
- the parking has a pricing policy to be able to bill the customers.

#### IParkingSlot
- can be used by a car.
- can be of several types, each parking slot type corresponding to a car type.

#### ICar 
- represents a customer for the parking.
- can be of several types

The ICar can park or unpark on/from a IParkingSlot, so it has been implemented as a Visitor Design Pattern on the IParkingSlot element structure.

The choice of a Visitor can be seen as overkilling for the current requirements, but it keeps the design highly extensible if other Car and ParkingSlots
objects are added with more complex placing constraints. In this case, the design remains the same and the API doesn't need to change it's initial contract. 

#### CarType
- represents the three types of cars and parking lots
        - sedan,
        - electric with 20kw power supply
        - electric with 50kw power supply

As the ICar and IParkingSlot are bounded in a bijective way, the design can be simplified. Instead of creating three subclasses for each ICar type and three subclasses for each IParkingSlot type, both derived hierarchies are reduced to a single class:
- Car for ICar
- ParkingSlot for IParkingSlot.

In this way the design remains straightforward, but allows the further addition of other ICar and IParkingSlots types if needed.
If the added hierarchies become too heterogeneous regarding the parking rules, they can be decoupled from the CarType attribute and implement the corresponding visitor methods between the specific ICar and IParkingSlot objects. 


#### IPricingPolicy
- computes parking fees to bill the customer.
- it can be extended without modifying the API by using a Decorator design pattern.


## How to build from the sources
Make sure you have a java 1.8 compiler and jre installed on you system:
```sh
$ java -version
$ javac -version
```
Check the installed maven version (3.3.9 recommended) :
```sh
$ mvn --version
```
Open a shell, go to the parking folder and run:
```sh
$ mvn clean install
```
## How to use
The usage of the API is detailed in the following junit test class:
```
com.exercises.parking.impl.ParkingTest.
```
Add the following maven dependency to your project pom.xml file:
```java
<dependency>
    <groupId>com.exercises</groupId>
    <artifactId>parking</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Code example usage:
```java

    //create some parking slots
    parkingSlots = new HashSet<IParkingSlot>();
    parkingSlots.addAll(Arrays.asList(
            new ParkingSlot(CarType.SEDAN),
            new ParkingSlot(CarType.SEDAN),
            new ParkingSlot(CarType.ELECTRIC_20KW),
            new ParkingSlot(CarType.ELECTRIC_50KW)));

    //create a pricing policy
    IPricingPolicy pricingPolicy = new BasicPricingPolicy(2.0);

    //create the parking API object
    IParking parking = new Parking(parkingSlots, pricingPolicy);

    //create a car
    ICar car = new Car(CarType.SEDAN);

    //use the parking API:

    // car enters the parking
    try{
        IParkingSlot slot = parking.checkIn(car, new Date());
    } catch (ParkingException e) {
      // manage exception case
    }

    // car leaves the parking and get billed
    try{
        IBill bill = parking.checkOut(car, departureTime);
    } catch (ParkingException e) {
      // manage exception case
    }
```

