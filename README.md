# FetchDemo
## Fetch Rewards Demo Payment System

Welcome to the Fetch Reward Payment System Demo!

Here we'll use http requests through cURL to enter and track our payments

### Setting up Demo:
   First, run this command on your terminal: 
   
   `git clone https://github.com/brock-huffar/FetchDemo.git`
   
Then, we have two choices on how to run this file:
  1. Open "demo" folder in your IDE, and run DemoApplication.java
     
  2. Navigate to "demo" folder and run as Jar executable with command
      `java -jar target/demo-0.0.1-SNAPSHOT.jar`
      
Once we have the application running, we will open a new terminal to start executing commands


### Executing Commands:

There are four commands we can run:

1. Add Payment
2. General Spend
3. Company Spend
4. Check Balance

### Add Payment

The add payment command will allow for a curl command to add a payment
  -add must include operation, payer, points, and timestamp
  
  !!!!!
  
  **(Note: based on specification from project write up file, two add functions cannot have the same timestamp)**
  
  add command will be executed like this: 
  
  `curl http://localhost:8080/functions\?'operation=add&payer=miller&points=1000&timeStamp=2022-11-02T14:00:00Z'`
  
To change operation, payer, points, or timeStamp, just enter your value in after the "=" in cURL command

If add is successful, it will return with the message "Add Complete" 


### General Spend

The general spend command allows points to be spent. The points will be taken from the payments in order of timestamp. 
  -generalspend only needs operation and points parameters
  
  generalspend command will be executed like this:
  
  `curl http://localhost:8080/functions\?'operation=generalspend&points=5000'`   
  
If successful, this command will return a list of payers and points taken from them


### Company Spend

The company spend command allows points to be spent from a single company. The points will be taken from company payments in the order they were timestamped
    
   companyspend needs operation, payer, and points parameters
    
   companyspend command will be executed like this:
    
   `curl http://localhost:8080/functions\?'operation=companyspend&payer=dannon&points=200'`
    
If successful, this command will return the payer and points taken from the payer


### Check Balance

The check balance command allows for a complete list of the companies and their point totals

   balance operation requires only the operation parameter
   
   balance command will be executed like this:
   
   `curl http://localhost:8080/functions\?'operation=balance'`
 
If successful, this command will return a list of all payers and their point totals





### Other Notes

While this is a functional demo, if this were to be scaled to production there are some changes I would make to allow it be used at scale.  

#### Data would not be stored locally

Data would not be stored locally like it is in this application.  Data would most likely be stored in cloud database like AWS.  

#### Transaction Safety and ACID Princicals

With a system at scale we can assume mistakes would happen; system failure, incomplete transaction, multiple transactions at once.  For this we would rely on the ACID properties of *Atomicity*, *Consistency*, *Isolation*, and *Durability* to ensure transaction happen in order, are complete, and all data is kept.  

#### Authentification

Depending on how the system is set up, we could add an authentification system, ensuring only valid "users" are able to submit transactions.  
  
