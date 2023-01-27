# City Dispatcher
School assignment of Péter Kiss.

## Description

## Game guide

### Rules / How to play

## TODO

### Done
- [x] Migrate local database to MS Azure 01/26
- [x] Create SQL table for lines 01/26
- [x] Implement creating lines between cities 01/27
  - [x] Open a form
  - [x] Choose color and vehicle type (dependent on the type of departure city)
  - [x] Click on arrival city to finish creating the line
- [x] Refactor cities and lines from ArrayList to HashMap
- [x] Fix cities' locations on screen

### Working on
- [ ] Improve line creation
  - [x] Make line curved and stackable
  - [x] Load lines from database on startup
  - [ ] Make line removable
  - [ ] Ask user to choose arrival city in the info label
- [ ] Add info label to main menu

### To do
- [ ] Migrate NetBeans forms to IntelliJ
- [ ] Fix Ant debug target
- [ ] Implement statistics form for each city
- [ ] Refactor JOptionPane.showMessagDialog to Util.showError & Util.showWarning
