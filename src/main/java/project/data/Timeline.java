package project.data;

import java.util.*;
public class Timeline {
    private static final ArrayList<Year> timeline = new ArrayList<Year>();
    /**
     * @author Robert Engel, T03, March 17 2022
     *Gets the ints representing all Years in the timeline
     * @return a List of Integers showing all years
     */
    public static ArrayList<Integer> getAllYears(){
        ArrayList<Integer> yearList = new ArrayList<Integer>();
        for(int i = 0; i < timeline.size(); i++){
            yearList.add(i, timeline.get(i).yearNumber);
        }
        Collections.sort(yearList);
        return yearList;
    }
    /**
     * @author Robert Engel, T03, March 17 2022
     * adds a project.data.Year to the timeline array if it doesn't already exist
     */
    public static void addYear(int yearNumber){
        Year newYear = new Year(yearNumber);
        if(timeline.contains(newYear)){
            System.out.println("project.data.Year already Exists! project.data.Year not added");
        }
        else if(newYear.yearNumber == 0){
            System.out.println("Error, project.data.Year not created correctly!");
        }
        else{
            timeline.add(newYear);
        }
    }
    public static ArrayList<Year> getTimeline(){
        return timeline;
    }

    /**
     * @author Adrian Ponce, T03, March 20,2022
     * Gets all the games in a given year
     * @param yearNumber Year number that the user provided
     * @return Returns a list of all the games within the year provided
     */

    public static ArrayList<String> getAllGameInAYear(int yearNumber){
        Year year = Timeline.getYear(yearNumber);
        ArrayList<String> games = new ArrayList<String>();
        for(int i = 0; i < year.getGameList().size(); i++){
            games.add(year.getGameList().get(i).getId());
        }
        return games;
    }

    /**
     * @author Adrian Ponce, T03, March 20,2022
     * Gets all the games ever played
     * @return Returns a list of all the games ever played.
     */
    public static ArrayList<String> getAllGamesEverPlayed() {
        ArrayList<Year> games = Timeline.getTimeline();
        ArrayList<String> allGames = new ArrayList<String>();
        for (int i = 0; i < games.size(); i++) {
            for (int j = 0; j < games.get(i).getGameList().size(); j++) {
                allGames.add(games.get(i).getGameList().get(j).getId());
            }
        }
        return allGames;
    }

    /**
     * @author Robert Engel, T03, March 17 2022
     * adds a project.data.Game to the project.data.Year if it doesn't already exist
     */

    public static void addGameToYear(Game newGame, int year){
        boolean hasAddedGame = false;
        for(int i = 0; i < timeline.size(); i++){
            if(timeline.get(i).yearNumber == year){
                timeline.get(i).addGame(newGame);
                hasAddedGame = true;
            }
        }
        if(hasAddedGame == false){
            System.out.println("Error, game not added!");
        }
    }
    public static Year getYear(int yearNumber){
        Year checkYear = new Year(yearNumber);
        if(timeline.contains(checkYear)){
            return timeline.get(timeline.indexOf(checkYear));
        }
        else{
            System.out.println("Error! Year not found!");
            return null;
        }
    }
}
