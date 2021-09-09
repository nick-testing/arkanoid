This is a simple arkanoid game, which I created during an OOP course in my university.
The game contains four levels of varrying difficulty which can be played in any order.

A build.xml ant file is provided for compiling and executing convenience.
Steps to run the game:
1. Open a terminal to the downloaded repository location.
2. Now, compile the .java files by running `ant`, this will created a /bin directory and place all .class files there.
3. In order to run the game, use `ant run -Dargs="Number(s) of the level(s) you want to run, seperated by spaces"`.
4. Now, after you've enjoyed playing the game and want an easy removal of the /bin/ folder, you can use `ant clean`.


The library "biuoop-1.4.jar" was provided by the Bar-Ilan OOP course instructors and contains various GUI elements which I used in this project.
