#Math Util
The Math Util web service is intended to be used to
calculate; 
- the maximum value(s) in a given list with a quantifier for how many values in that
list to return
- the minimum value(s) in a given list with a quantifier for how many values in that
  list to return
- the average in a given list 
- the median in a given list
- Ther percentile in a given list with a quantifier acting as a percentage

##How To Run
##### Maven Wrapper
./mvnw spring-boot:run

##### IDE
- Some IDEs will autoconfigure a run function in the main class.

##How to Use
http://localhost:8080/swagger-ui/index.html or Postman.

##Requirements
- Minimum Version - Java 11

##Assumptions

- I allowed negative and positive numbers with max two decimal places in the list.
- I only allowed 1 - 100 for the quantifier and the same for the list size. 
- Quantifier is an int so any decimals passed to the quantifier via postman will be converted & rounded down. E.g 1.99, will be 1.
- I Assumed that repetitive numbers were allowed (see future considerations)

##Future Considerations

I didn't get around to cleaning certain bits of code up but the following are my thoughts.

- I used SpringBoot as it's quick and easy and there was a mention of resources not being an issue
  but if that were the case, you could use Spring or a lightweight framework like micronaut.
- I would add some controller tests like webmvc and integration tests, 
  but I didn't have time, so I just added tests around the service layer (main functionality).
- I would ask for confirmation for what to do with lists with repetitive numbers like {0,0,1,1}.
  There could be a future requirement to filter them out. Same with lists that just contain {0,0,0,0}.
  We would just return 0 or an exception depending on different factors. All this would need to be clarified
  with product/team lead. This is an issue that affects differently endpoints. The argument could be made that
  they should be filtered out/ exception thrown for max, min and left in for the other endpoints.   
- I would set up a separate constants class to hold all the string values in each class.
- We will only accept decimals of up to two places, but we are returning more decimals,
  This is something we would also need to consider.
- Exceptions would be better defined and the controller advice class would be tidied up and include more information
  about each exception thrown building up an API error class & java docs.
- If performance was an issue I would revisit the validation logic and figure out a better way to validate in one stream.
- I would ask for clarification around the use of negative numbers in the list.
