# Gantt-chart
A Gantt chart is a type of bar chart that illustrates a project schedule. This chart lists the tasks to
be performed on the vertical axis, and time intervals on the horizontal axis. The width of the
horizontal bars in the graph shows the duration of each activity. Gantt charts illustrate the start and
finish dates of the terminal elements and summary elements of a project. Terminal elements and
summary elements constitute the work breakdown structure of the project

## Team
- Kostia Synytsia - kostiantyn.synytsia@gmail.com
- Sviatoslav Konstantyniv - svyatopato@gmail.com
- Anton Vovk - archwolf@protonmail.com
- Vladislav Manzhula - jay.k0lad@gmail.com

## Task includes:
Implement a JAVA program to read Gantt chart from XML file, present in graphical form and determine the critical path.
- Design of XML structure;
- Implement loading data from XML structure;
- Present in graphical form considering dependences between task
- Determine and present critical path

## Assumptions of task:
- At least 2 parallel paths of project
- Static presentation of WBS
- Presentation of references between tasks
- Technology requirenments:
  - Java 8
  - Maven/Gradle build automation tool
  - JUnit for unit testsing is a plus

## What does the application do
The application reads an XML file which contains tasks then validates it using XSD. 
If validation is successful, the application will parse the file into our classes. 
After the data was parsed, it is checked for correction(dependencies, dates, e.c.)
Then critical path is calculated. If there more than one critical path, they all 
will be displayed. Finally, it is displayed on the screen.

## Getting started
To build this project first add all files in `lib` folder to project as a library
Then build project
Now you can run project from class `com.example.gantt_chart.Main`

## Errors which is handled
- A task can't depend on task from another sub-tasks
- A depended task can't go earlier than the previous task finishes
- A task with the id from dependencies does not exist
- Dates of sub-tasks go earlier or later than the date of this task 

## XML input instruction
To see how to set XML file see [XML examples](docs/ExamplesXML.md)

## Screenshots

An example of execution of [this](docs/ExamplesXML.md#correct-xml) input 
![screenshot1](docs/screenshots/1.png)

![screenshot2](docs/screenshots/2.png)
