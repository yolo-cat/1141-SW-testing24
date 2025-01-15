package org.example;

public class Github implements GitHubService{

    @Override
    public int getLines(String gitHubRepo) {
        String line[] = new String[2];
        int i=0;
        for(String temp: gitHubRepo.split(",")) {
            line[i]=temp;
            i++;
        }
        int temp = Integer.parseInt(line[0]);
        return temp;
    }

    @Override
    public int getWMC(String gitHubRepo) {
        String wmc = "";
        for(String temp: gitHubRepo.split(",")) {
            wmc = temp;
        }
        int temp = Integer.parseInt(wmc);
        return temp;
    }


}
