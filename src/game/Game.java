package game;

public class Game {
    
    private String name;
    private int year;
    private int maxPlaytime;
    private int minPlaytime;
    private int playtime;
    private String band;
    
    public Game(String name, int year, int minPlaytime, int maxPlaytime) {
        this.name = name;
        this.year = year;
        this.maxPlaytime = maxPlaytime;
        this.minPlaytime = minPlaytime;      
        this.playtime = maxPlaytime - minPlaytime;
        if (playtime >= 180) {
            this.band = ">= 180";
        } else if (playtime >= 120) {
            this.band = "120 - 180";
        } else if (playtime >= 60) {
            this.band = "60 - 120";
        } else if (playtime >= 30) {
            this.band = "30 - 60";
        } else {
        this.band = "< 30";
        }
    }

    public String getName() {return name;}

    public int getYear() {return year;}

    public String getBand() {return band;}
}
