 package demolition;

 import processing.core.PApplet;
 import processing.core.PFont;
 import processing.core.PImage;

 import java.util.ArrayList;
 import java.util.List;

 public class GameMap {

     /**
      *   Maps are with a 64 pixel offset between the top of the screen and the start of the first row.
      *   Each grid space is 32x32 pixels.
      *   There are 4 different tile types within the map: solid, broken, empty and goal
      *   Map files store the maps as multidimensional character arrays, where each character represents what is in that cell
      *   A map is valid if it has a bounding border, a starting location for Bomb Guy, and a Goal Tile
      */

     public static final int CELL_SIZE = 32;
     private int lives;
     private int time;
     private int ticks = App.FPS;
     private char[][] map;
     private Player mario;
     private List<Enemy> enemyList;
     private App pApplet;
     private List<Bomb> bombs;
     private List<Explosion> explosions;
     private int levelNumber;

     public GameMap(Level level, int lives, App applet) {
         this.pApplet = applet;
         this.levelNumber = 0;
         this.time = level.getTime();
         this.lives = lives;
         this.map = initLevelMap(level, this.pApplet);
     }

     public char[][] initLevelMap(Level level, PApplet applet) {
         enemyList = new ArrayList<>();
         bombs = new ArrayList<>();
         explosions = new ArrayList<>();
         char [][] retMap = new char[15][15];
         retMap[0] = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
         retMap[1] = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
         String[] configMap = applet.loadStrings(level.getPath());
         for (int i = 0; i < configMap.length; i++) {
             for (int j = 0; j < configMap[i].length(); j++) {
                 char val = configMap[i].charAt(j);
                 retMap[i + 2][j] = val == 'P' || val == 'Y' || val == 'R' ? ' ' : val;
             }
         }
         initCharacters(configMap);
         return retMap;
     }

     public void tick() {
         // refresh map etc
         ticks--;
         if (ticks == 0) {
             decreaseTime();
             moveEnemies();
             ticks = App.FPS;
         }
         //redraw the refresh status
         drawMap();
     }

     public void drawMap() {
         if (lives == 0) {
             String gameOver = "Game Over";
             pApplet.background(243, 112, 33);
             PFont gameOverPFont = pApplet.createFont("PressStart2P-Regular.ttf", 26, true, gameOver.toCharArray());
             pApplet.textFont(gameOverPFont);
             pApplet.fill(0);
             pApplet.text(gameOver, Math.floorDiv(App.WIDTH, 4) , Math.floorDiv(App.HEIGHT, 2));
             pApplet.noLoop();
             return;
         }

         checkEnemyCollision();

         pApplet.background(243, 112, 33);
         PImage playerImg = pApplet.loadImage("src/main/resources/icons/player.png");
         pApplet.image(playerImg, 4 * CELL_SIZE,CELL_SIZE / 2);
         pApplet.fill(0);
         PFont font = pApplet.createFont("PressStart2P-Regular.ttf", 22);
         pApplet.textFont(font);
         pApplet.text(this.lives, 5.2F * CELL_SIZE, CELL_SIZE / 2 + playerImg.height - 2);

         PImage clockImg = pApplet.loadImage("src/main/resources/icons/clock.png");
         pApplet.image(clockImg, 8 * CELL_SIZE,CELL_SIZE / 2);
         pApplet.text(this.time, 9.2F * CELL_SIZE, CELL_SIZE / 2 + clockImg.height - 2);

         for (int i = 2; i < map.length; i++) {
             char[] rowArr = map[i];
             for (int j = 0; j < rowArr.length; j++) {
                 switch (rowArr[j]) {
                     case 'W':
                         SolidWall.draw(pApplet, j * CELL_SIZE, (i) * CELL_SIZE);
                         break;
                     case 'B':
                         BrokenWall.draw(pApplet, j * CELL_SIZE, (i) * CELL_SIZE);
                         break;
                     case 'G':
                         GoalTile.draw(pApplet, j * CELL_SIZE, (i) * CELL_SIZE);
                         break;
                     default:
                         EmptyTile.draw(pApplet, j * CELL_SIZE, (i) * CELL_SIZE);
                         break;
                 }
             }
         }

         mario.drawChar(pApplet);
         enemyList.forEach(enemy -> enemy.drawChar(pApplet));

         bombs.forEach(bomb -> {
             bomb.tick();
             if (bomb.getTicks() == 0) {
                 explodeBomb(bomb);
             } else {
                 bomb.draw(pApplet);
             }
         });
         // remove the exploded bombs
         bombs.removeIf(bomb -> bomb.getTicks() == 0);

         explosions.forEach(explosion -> {
             explosion.tick();
             if (explosion.getTicks() > 0) {
                 explosion.draw(pApplet, map);
             } else {
                 this.handleExplosion(explosion);
             }
         });
         // remove the exploded bombs
         explosions.removeIf(explosion -> explosion.getTicks() == 0);
     }

     private void initCharacters(String[] map) {
         for (int i = 0; i < map.length; i++) {
             String[] rowArr = map[i].split("");
             for (int j = 0; j < rowArr.length; j++) {
                 switch (rowArr[j]) {
                     case "P":
                         mario = new Player(j, i + 2, pApplet);
                         break;
                     case "R":
                         enemyList.add(new RedEnemy(j, i + 2, pApplet));
                         break;
                     case "Y":
                         enemyList.add(new YellowEnemy(j, i + 2, pApplet));
                         break;
                     default:
                         break;
                 }
             }
         }
     }

     private void checkEnemyCollision() {
         // check collision
         for (Enemy enemy : enemyList) {
             if (mario.x == enemy.x && mario.y == enemy.y) {
                 lives --;
                 resetLevel();
                 return;
             }
         }
     }

     private void resetLevel() {
         Level level = pApplet.getAppConfig().getLevels().get(this.levelNumber);
         this.map = initLevelMap(level, pApplet);
         //reset time if needed
         this.time = level.getTime();
     }

     private void decreaseTime() {
         time--;
     }

     private void moveEnemies() {
         enemyList.forEach(enemy -> enemy.move(map));
     }

     public void movePlayer(Direction direction) {
         mario.move(map, direction);
     }

     public void placeBomb() {
         Bomb bomb = new Bomb(mario.getX(), mario.getY(), pApplet);
         this.bombs.add(bomb);
     }

     /**
      * checks if the explosion killed the player or the enemies
      */
     private void handleExplosion(Explosion explosion) {
         int x = explosion.getX();
         int y = explosion.getY();

         // loose life and reset if player caught in explosion
         boolean hasWallNearBy = false;
         boolean sameLine = (x-2 <= mario.getX()) && (mario.getX() <= x+2) && (y == mario.getY());
         boolean sameRow = (y-2 <= mario.getX()) && (mario.getX() <= y+2) && (x == mario.getX());

         if (sameLine || sameRow){


             // chekcs if wall is stopping the bomb on the top and the bottom of the player

             // TODO: seperate the following into left and right cases with wall
             if (map[x+1][y] == 'W' || map[x+1][y] == 'B' || map[x-1][y] == 'W' || map[x-1][y] == 'B') {
                 hasWallNearBy = true;
                 // then check if player is within the bomb's horizontal explosion range
                 if (y-2 <= mario.getY() && mario.getY() <= y+2 && x == mario.getX()) {
                     lives--;
                     resetLevel();
                     return;
                 }
             }

             if (map[x][y+1] == 'W' || map[x][y+1] == 'B' || map[x][y-1] == 'W' || map[x][y-1] == 'B') {
                 hasWallNearBy = true;
                 if (x-2 <= mario.getY() && mario.getY() <= x+2 && y == mario.getY()) {
                     lives--;
                     resetLevel();
                     return;
                 }
             }
             else if (!hasWallNearBy) {
                 lives--;
                 resetLevel();
                 return;
             }
         }

         // kill enemies if they are caught in the explosion
         for (Enemy enemy : enemyList) {
             hasWallNearBy = false;
             sameLine = (x-2 <= enemy.getX()) && (enemy.getX() <= x+2) && (y == enemy.getY());
             sameRow = (y-2 <= enemy.getX()) && (enemy.getX() <= y+2) && (x == enemy.getX());

             if (sameLine || sameRow) {
                 // reset x & y to the correct, original ones
                 x = explosion.getX();
                 y = explosion.getY();

                 // chekcs if wall is stopping the bomb on the left and the right of the enemy
                 if (map[x + 1][y] == 'W' || map[x + 1][y] == 'B' || map[x - 1][y] == 'W' || map[x - 1][y] == 'B') {
                     hasWallNearBy = true;
                     // then check if enemy is within the bomb's horizontal explosion range
                     if (y - 2 <= enemy.getY() && enemy.getY() <= y + 2 && x == enemy.getX()) {
                         enemy.setKilled(true);
                     }
                 }

                 if (map[x][y + 1] == 'W' || map[x][y + 1] == 'B' || map[x][y - 1] == 'W' || map[x][y - 1] == 'B') {
                     hasWallNearBy = true;
                     if (x - 2 <= enemy.getY() && enemy.getY() <= x + 2 && y == enemy.getY()) {
                         enemy.setKilled(true);
                     }
                 } else if (!hasWallNearBy) {
                     enemy.setKilled(true);
                 }
             }
         }
         // remove enemy from enemy list if enemy has been killed
         // not using simplified expression because this is easier to understand
         enemyList.removeIf(enemy -> enemy.isKilled());

         // update the map remove broken tiles caught in the explosion
         char cellVal = map[y][x - 1];
         if (cellVal == 'B') {
             map[y][x - 1] = ' ';
         } else if (cellVal == ' ') {
             if (x > 1 && map[y][x - 2] == 'B') {
                 map[y][x - 2] = ' ';
             }
         }

         cellVal = map[y][x + 1];
         if (cellVal == 'B') {
             map[y][x + 1] = ' ';
         } else if (cellVal == ' '){
             if (map[y][x + 2] == 'B') {
                 map[y][x + 2] = ' ';
             }
         }

         cellVal = map[y-1][x];
         if (cellVal == 'B') {
             map[y-1][x] = ' ';
         } else if (cellVal == ' '){
             if (y > 3 && map[y - 2][x] == 'B') {
                 map[y - 2][x] = ' ';
             }
         }

         cellVal = map[y+1][x];
         if (cellVal == 'B') {
             map[y+1][x] = ' ';
         } else if (cellVal == ' '){
             if (map[y + 2][x] == 'B') {
                 map[y + 2][x] = ' ';
             }
         }
     }

     private void explodeBomb(Bomb bomb) {
         Explosion explosion = new Explosion(bomb.getX(), bomb.getY(), pApplet);
         explosions.add(explosion);
    }

     public void goToNextLevel() {
         if (pApplet.getAppConfig().getLevels().size() > this.levelNumber + 1) {
             this.levelNumber++;
             Level level = pApplet.getAppConfig().getLevels().get(this.levelNumber);
             this.map = initLevelMap(level, pApplet);
             this.time = level.getTime();
         } else {
             System.out.println("You win");
             String youWin = "You win";
             pApplet.background(243, 112, 33);
             PFont gameOverPFont = pApplet.createFont("PressStart2P-Regular.ttf", 26, true, youWin.toCharArray());
             pApplet.textFont(gameOverPFont);
             pApplet.fill(0);
             pApplet.text(youWin, Math.floorDiv(App.WIDTH, 4) , Math.floorDiv(App.HEIGHT, 2));
             pApplet.noLoop();
             return;
         }
     }

 }




