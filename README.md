<h1>Arkanoid</h1>

This is a simple [Arkanoid](https://en.wikipedia.org/wiki/Arkanoid)-type block breaker game, which I created during an OOP course in my university.
The game contains four levels of varying difficulty which can be played in any order.

<h3>Prerequisites</h3>

- JDK(written using OpenJDK 15 but might compile on other versions)
- Optional: [Ant](https://ant.apache.org/)

A build.xml ant file is provided for compiling and executing convenience.
Steps to run the game:
- Clone the repository using `git clone` and `cd` to the downloaded **arkanoid** folder.
- Now, compile the .java files by running `ant`, this will created a /bin directory and place all .class files there.
- In order to run the game, use `ant run -Dargs="Level numbers"`.
  - For example, `ant run -Dargs="1 3 2 4"` will run levels 1, 3, 2, 4.
- Now, after you've enjoyed playing the game and want an easy removal of the /bin folder, you can use `ant clean`




The library "biuoop-1.4.jar" was provided by the Bar-Ilan Univrsity OOP course instructors and contains various GUI elements which I used in this project.
