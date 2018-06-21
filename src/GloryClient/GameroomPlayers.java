package GloryClient;


public class GameroomPlayers {
    private String name;
    private int score;
    private String init;


    public GameroomPlayers(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init.trim();
    }
}
