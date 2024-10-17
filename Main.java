import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        List<Matches> matches = getMatchesData();
        List<Deliveries> deliveries = getDeliveriesData();

        findMatchesPlayedPerYear(matches);
        findMatchesWonPerTeam(matches);
        findTheMostEconomicalBowlerIn2015(matches, deliveries);
        findTheExtraRunsConcededPerTeamIn2016(matches, deliveries);
    }

    // Method to read match data from file and return a list of Matches objects
    private static List<Matches> getMatchesData() {
        List<Matches> matchesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/naveed/IdeaProjects/ipl-project1/data/matches.csv"))) {
            String line;
            br.readLine();  // Skip header
            while ((line = br.readLine()) != null) {
                String[] matchData = line.split(",");
                Matches match = new Matches(
                        Integer.parseInt(matchData[0]),  // matchId
                        Integer.parseInt(matchData[1]),  // season
                        matchData[4],                    // team1 (index 4 for team1)
                        matchData[5],                    // team2 (index 5 for team2)
                        matchData[10]                    // winner (index 10 for winner)
                );
                matchesList.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchesList;
    }

    // Method to read delivery data from file and return a list of Delivery objects
    private static List<Deliveries> getDeliveriesData() {
        List<Deliveries> deliveriesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/naveed/IdeaProjects/ipl-project1/data/deliveries.csv"))) {
            String line;
            br.readLine();  // Skip header
            while ((line = br.readLine()) != null) {
                String[] deliveryData = line.split(",");
                Deliveries delivery = new Deliveries(
                        Integer.parseInt(deliveryData[0]),  // matchId
                        deliveryData[8],                    // bowler (index 8 for bowler)
                        Integer.parseInt(deliveryData[17]),  // total runs (index 17 for total runs)
                        Integer.parseInt(deliveryData[16])   // extra runs (index 16 for extra runs)
                );
                deliveriesList.add(delivery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveriesList;
    }

    // Method to find matches played per year
    private static void findMatchesPlayedPerYear(List<Matches> matches) {
        Map<Integer, Integer> matchesPerYear = new HashMap<>();
        for (Matches match : matches) {
            matchesPerYear.put(match.getSeason(), matchesPerYear.getOrDefault(match.getSeason(), 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : matchesPerYear.entrySet()) {
            System.out.println("Year: " + entry.getKey() + ", Matches: " + entry.getValue());
        }
    }

    // Method to find matches won per team
    private static void findMatchesWonPerTeam(List<Matches> matches) {
        Map<String, Integer> winsPerTeam = new HashMap<>();
        for (Matches match : matches) {
            String winner = match.getWinner();
            if (!winner.equals("")) {
                winsPerTeam.put(winner, winsPerTeam.getOrDefault(winner, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : winsPerTeam.entrySet()) {
            System.out.println("Team: " + entry.getKey() + ", Wins: " + entry.getValue());
        }
    }

    // Method to find the most economical bowler in 2015
    private static void findTheMostEconomicalBowlerIn2015(List<Matches> matches, List<Deliveries> deliveries) {
        Map<String, Integer> bowlerRuns = new HashMap<>();
        Map<String, Integer> bowlerBalls = new HashMap<>();

        for (Matches match : matches) {
            if (match.getSeason() == 2015) {
                for (Deliveries delivery : deliveries) {
                    if (delivery.getMatchId() == match.getMatchId()) {
                        String bowler = delivery.getBowler();
                        bowlerRuns.put(bowler, bowlerRuns.getOrDefault(bowler, 0) + delivery.getRuns());
                        bowlerBalls.put(bowler, bowlerBalls.getOrDefault(bowler, 0) + 1);
                    }
                }
            }
        }

        String mostEconomicalBowler = "";
        double bestEconomy = Double.MAX_VALUE;

        for (String bowler : bowlerRuns.keySet()) {
            double economy = (double) bowlerRuns.get(bowler) / (bowlerBalls.get(bowler) / 6.0);
            if (economy < bestEconomy) {
                bestEconomy = economy;
                mostEconomicalBowler = bowler;
            }
        }

        System.out.println("Most Economical Bowler in 2015: " + mostEconomicalBowler + ", Economy: " + bestEconomy);
    }

    // Method to find extra runs conceded per team in 2016
    private static void findTheExtraRunsConcededPerTeamIn2016(List<Matches> matches, List<Deliveries> deliveries) {
        Map<String, Integer> teamExtraRuns = new HashMap<>();

        for (Matches match : matches) {
            if (match.getSeason() == 2016) {
                for (Deliveries delivery : deliveries) {
                    if (delivery.getMatchId() == match.getMatchId()) {
                        String team = match.getTeam1();  // Assuming team1 conceded extra runs
                        teamExtraRuns.put(team, teamExtraRuns.getOrDefault(team, 0) + delivery.getExtraRuns());
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : teamExtraRuns.entrySet()) {
            System.out.println("Team: " + entry.getKey() + ", Extra Runs Conceded: " + entry.getValue());
        }
    }
}

