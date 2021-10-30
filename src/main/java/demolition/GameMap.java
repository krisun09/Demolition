 package demolition;

 import processing.core.PApplet;
 import processing.core.PFont;
 import processing.core.PImage;

 import java.util.ArrayList;
 import java.util.List;

 // Maps are 13 rows tall, and 15 columns wide, with a 64 pixel offset between the top of the screen and the start of the first row.
 // Each grid space is 32x32 pixels.
 // There are 4 different tile types within the map: solid, broken, empty and goal
 // Map files store the maps as multidimensional character arrays, where each character represents what is in that cell
 // A map is valid if it has a bounding border, a starting location for Bomb Guy, and a Goal Tile
 public class GameMap {
     public static final int CELL_SIZE = 32;
     private int lives;
     private int time;
     private int ticks = App.FPS;
     private String[] map;
     private Player mario;
     private List<Enemy> enemyList = new ArrayList<>();

     // update the x and y of mario
     // then update the map2

     public GameMap(Level level, int lives, PApplet applet) {
        this.time = level.getTime();
        this.lives = lives;
        this.map = applet.loadStrings(level.getPath());
     }

     public void tick(PApplet pApplet) {
         // refresh map etc
         ticks--;
         if (ticks == 0) {
             decreaseTime();
             moveEnemies();
             // call enemy movement etc etc

             ticks = App.FPS;
         }

         //redraw the refresh status
         drawMap(pApplet);
     }

     public void drawMap(PApplet pApplet) {
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

         for (int i = 0; i < map.length; i++) {
             String[] rowArr = map[i].split("");
             for (int j = 0; j < rowArr.length; j++) {
                 switch (rowArr[j]) {
                     case "W":
                         SolidWall.draw(pApplet, j * CELL_SIZE, (i+2) * CELL_SIZE);
                         break;
                     case "B":
                         BrokenWall.draw(pApplet, j * CELL_SIZE, (i+2) * CELL_SIZE);
                         break;
                     case "G":
                         GoalTile.draw(pApplet, j * CELL_SIZE, (i+2) * CELL_SIZE);
                         break;
                     default:
                         EmptyTile.draw(pApplet, j * CELL_SIZE, (i+2) * CELL_SIZE);
                         break;
                 }
             }
         }

         for (int i = 0; i < map.length; i++) {
             String[] rowArr = map[i].split("");
             for (int j = 0; j < rowArr.length; j++) {
                 switch (rowArr[j]) {
                     case "P":
                         Player.draw(pApplet, j * CELL_SIZE, (i+2) * CELL_SIZE * 0.93F);
                         break;
                     case "R":
                         RedEnemy.draw(pApplet, j * CELL_SIZE , (i+2) * CELL_SIZE * 0.93F);
                         enemyList.add(new RedEnemy(i, j, pApplet.loadImage("src/main/resources/red_enemy/red_down1.png")));
                         break;
                     case "Y":
                         YellowEnemy.draw(pApplet, j * CELL_SIZE, (i+2) * CELL_SIZE * 0.93F);
                         break;
                     default:
                         break;
                 }
             }
         }
    }

     private void decreaseTime() {
         time--;
     }

     private void moveEnemies() {

//         enemyList.forEach(enemy -> enemy.moveX(map, 1));

         for (int i = 0; i < enemyList.size(); i+=60) {
             enemyList.get(i).moveX(map, 1);
         }

     }

 }


