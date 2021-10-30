package demolition;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppConfig {

    private int lives;
    private List<Level> levels = new ArrayList<>();

    AppConfig(String filePath, PApplet pApplet) {
        JSONObject jsonObject = pApplet.loadJSONObject(filePath);
        this.lives = jsonObject.getInt("lives");
        JSONArray levels = jsonObject.getJSONArray("levels");
        for(int i=0; i < levels.size(); i++) {
            JSONObject level = levels.getJSONObject(i);
            String mapPath = level.getString("path");
            int time = level.getInt("time");
            this.levels.add(new Level(mapPath, time));
        }
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}
