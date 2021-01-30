# MyTunes

MyTunes is a SPRING MVC application with Thymeleaf templating frontend. MyTunes uses SQLlite database with the Chinook database for doing a set of  queries against the database. 

## Participants

Thomas Gulli and Bjørnar Pedersen

## Motivation

This project was made as an assignment during the Experis Academy Java Fullstack course.

## Code example

Example query is retrieving highest spending customers from Chinook database.
The following code shows all methods involved.

```java

//Prepared statement against the Chinook SQLLite database.
@Override
public ArrayList<HighestEarningCostumer> getHighestSpendingCustomers() {
    //Get instance of Singleton Database class
    dataBaseConnection = SingletonDBConnector.getInstance();
    //Get connection from Singleton
    myConnection = dataBaseConnection.getConn();

    try {
        // Prepare Statement
        PreparedStatement preparedStatement =
        myConnection.prepareStatement(
            "SELECT Customer.FirstName, Customer.LastName, SUM(Invoice.Total) AS totalSum " +
            "FROM Invoice INNER JOIN Customer ON Customer.CustomerId = Invoice.CustomerId " +
            "GROUP BY Invoice.customerId ORDER BY totalSum DESC");

        // Execute Statement
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<HighestEarningCostumer> arrayList = new ArrayList<>();
        // Process Results
        while (resultSet.next()){
            arrayList.add(new HighestEarningCostumer(resultSet.getString(1) + " " + resultSet.getString(2), resultSet.getString(3)));
        }
        return arrayList;
    }
    catch (SQLException e){
        System.out.println(e.getMessage());
    }
    finally {
        dataBaseConnection.closeConnection();
    }
    return null;
}


//REST Controller endpoint
@GetMapping("api/customers/highest-spenders/")
public ArrayList<HighestEarningCostumer> getHighestSpenders(){
    return db.getHighestSpendingCustomers();
}

//Local Controller endpoint
@GetMapping(value = "customers/getMostPopularGenreFromSpecificCustomer/{id}")
public String getMostPopularGenreFromSpecificCustomer(@PathVariable(name = "id") String id, Model model){
    //POJO object for arist name and genres (name and number of songs)
    PopularGenres popularGenres = db.getMostPopularGenreFromSpecificCustomer(id);
    model.addAttribute("popularGenres", popularGenres);
     return "customerGenre";
}

```

## Project tree
```bash
+---.idea
|   +---dataSources
|   \---libraries
+---.mvn
|   \---wrapper
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---example
|   |   |           \---MyTunes
|   |   |               +---controller
|   |   |               +---dataAccess
|   |   |               +---model
|   |   |               \---util
|   |   \---resources
|   |       +---static
|   |       |   +---css
|   |       |   +---img
|   |       |   \---js
|   |       \---templates
|   \---test
|       \---java
|           \---com
|               \---example
|                   \---MyTunes
\---target
    +---classes
    |   +---com
    |   |   \---example
    |   |       \---MyTunes
    |   |           +---controller
    |   |           +---dataAccess
    |   |           +---model
    |   |           \---util
    |   +---static
    |   |   +---css
    |   |   +---img
    |   |   \---js
    |   \---templates
    +---generated-sources
    |   \---annotations
    +---generated-test-sources
    |   \---test-annotations
    \---test-classes
        \---com
            \---example
                \---MyTunes
```

## Credits
Thanks to Nicholas Lennox for great guidance
