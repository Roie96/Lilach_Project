# Lliach - Project 
Lilach Flower Shop Desktop Application using Java Programming Language with JavaFX, Hibernate, Maven & OCSF.

Database - MySQL.

Main Features:
* User Interface and permission levels.
* Permissions Management.
* Online GUI refreshment.
* Products & Users modifying abillity.


![Screen Shot 2022-09-29 at 13 45 59](https://user-images.githubusercontent.com/100276577/193012657-ef532398-4238-4023-ba27-d2b755efb0f2.png)
![Screen Shot 2022-09-29 at 13 46 31](https://user-images.githubusercontent.com/100276577/193012675-0a88108e-5169-41f5-a31e-d604299318c4.png)
![Screen Shot 2022-09-29 at 13 48 52](https://user-images.githubusercontent.com/100276577/193012688-cda12790-777e-4aed-ac7f-e693d04ed499.png)


## Structure
Pay attention to the three modules:
1. **client** - a simple client built using JavaFX and OCSF. We use EventBus (which implements the mediator pattern) in order to pass events between classes (in this case: between SimpleClient and PrimaryController.
2. **server** - a simple server built using OCSF.
3. **entities** - a shared module where all the entities of the project live.

## Running
1. Run Maven install **in the parent project**.
2. Run the server using the exec:java goal in the server module.
3. Run the client using the javafx:run goal in the client module.
4. Press the button and see what happens!

*notice javafx does not work well on m1 computers
