# TECHNICAL CHALLENGE

Practical exercise where a system is basically a sales pipeline 
where leads could convert into prospects of the company

# Decisions
* It was decided to use a clean-architecture approach to have a good separate of concerns.
* The two external systems where simulated with methods that return randomly ```true``` or ```false```. 
* To make the parallel calls, it was used a ```CompleteFuture``` approach in order to avoid modifying the 
respectively services to support threads.
 
# Assumptions
The next assumptions where made:
* If a client data match with the data in the national registry identification external 
system, and the client does not have judicial records, then the system could consider to
that client become a prospect. Otherwise, it will not be consider as a prospect.

## Improvements that can be done on the technical challenge.
In this challenge, can be implemented new approaches to simulate the external systems, in that
cases, the unit tests developed for that specific part must be updated.


## Contact
Juan Camilo Mojica <jcmojicap@gmail.com>

## Compiling

To compile:

    $ ./gradlew clean build

## Testing

To testing:

    $ ./gradlew test   

## Running

To run:

    $ ./gradlew bootRun