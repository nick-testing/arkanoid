<h1>Arkanoid</h1>

This is a simple [Arkanoid](https://en.wikipedia.org/wiki/Arkanoid)-type block breaker game, which I created during an OOP course in my university.
The game contains four levels of varying difficulty which can be played in any order.

<h3>Prerequisites</h3>

- JDK(tested on OpenJDK 15 but might compile on other versions)
- Optional: [Ant](https://ant.apache.org/)

A build.xml ant file is provided for compiling and executing convenience.
Steps to run the game:
- Clone the repository using `git clone` and `cd` to the downloaded **arkanoid** folder.
- Now, compile the .java files by running `ant`, this will created a /bin directory and place all .class files there.
- In order to run the game, use `ant run -Dargs="Level numbers"`.
  - For example, `ant run -Dargs="1 3 2 4"` will run levels 1, 3, 2, 4.
- Now, after you've enjoyed the game use `ant clean` to remove the /bin folder.


## Screenshots

<img src="https://user-images.githubusercontent.com/80259776/156460497-f1cbfbfe-18f0-4f0f-a412-dba0bd8cc2d2.png" width = 50% height=50% title="Level 1"><img src="https://user-images.githubusercontent.com/80259776/156460506-7ec078ce-e608-4278-baef-19faa82e0524.png" width=50% height=50% title="Level 2">
<img src="https://user-images.githubusercontent.com/80259776/156460531-ebafc42a-7dae-480f-844c-ad423d785d6e.png" width=50% height=50% title="Level 3"><img src="https://user-images.githubusercontent.com/80259776/156460538-1c32fde4-0bb5-4abf-87b2-e541349686af.png" width=50% height=50% title="Level4">




_The library "biuoop-1.4.jar" was provided by the Bar-Ilan Univrsity OOP course instructors and contains various GUI elements which I used in this project._
