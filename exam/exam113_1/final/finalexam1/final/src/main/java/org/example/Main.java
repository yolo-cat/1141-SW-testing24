package org.example;

public class Main {
    public static int calculateFee(int grade, int absenceCount, int typingMinutes, int wpm, String gitHubRepo) {
        if(grade<2) throw new IllegalArgumentException("grade must be equal or greater than 2");
        Github g = new Github();
        int price=500;
        if(absenceCount<5) price-=50;

        if(wpm>100) price-=150;
        else if(wpm>80) price-=100;
        else if(wpm>60) price-=50;

        if(grade==2) {
            if(typingMinutes>10) price-=200;
            else if(typingMinutes>5) price-=100;
            int line = g.getLines(gitHubRepo);
            if(line>4000) line=4000;
            price-=(line/1000)*50;
        }
        if (grade==3) {
            if(typingMinutes>15) price-=200;
            else if(typingMinutes>10) price-=100;
            if(g.getWMC(gitHubRepo)>50) {
                int line = g.getLines(gitHubRepo);
                if(line>4000) line=4000;
                price-=(line/1000)*50;
            }
        }
        if (grade==4) {
            if(typingMinutes>20) price-=200;
            else if(typingMinutes>15) price-=100;
            if(g.getWMC(gitHubRepo)>50) {
                int line = g.getLines(gitHubRepo);
                if(line>4000) line=4000;
                price-=(line/1000)*50;
            }
        }
        return price;
    }
    public static void main(String[] args) {}
}

