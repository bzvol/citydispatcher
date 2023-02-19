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
- [x] Refactor cities and lines from ArrayList to HashMap 01/27
- [x] Fix cities' locations on screen 01/27
- [x] Add info label to main screen 02/18
- [x] Refactor JOptionPane.showMessageDialog to Util.showError & Util.showWarning 02/19

### Working on
- [ ] Improve line creation
  - [x] Make line curved and stackable
  - [x] Load lines from database on startup
  - [ ] Make line removable
  - [ ] Ask user to choose arrival city **on a label**
- [ ] Implement data saving & loading
  - [x] Implement json saving
  - [ ] Implement json loading
- [ ] Migrate NetBeans forms to IntelliJ forms

### To do
- [ ] Fix Ant debug target
- [ ] Implement statistics form for cities
- [ ] Add game templates e.g. map of Hungary - border + larger cities
- [ ] Replace A* with Dijkstra's algorithm
  - ***Why?*** *Though A\* is faster, it does not guarantee to find the shortest path, while Dijkstra's algorithm does.*
- [ ] Implement syncing from DB to map, and vice versa
  - [ ] *(after)* Sync data to DB when loading from json

## Assignment requirements
- [x] Implement adjacency matrix
- [x] ~~Implement latitude traversal~~
- [x] Implement connected graph test
- [x] Implement Dijkstra's algorithm - *implemented A\* instead*
