class Matches {
    private int matchId;
    private int season;
    private String team1;
    private String team2;
    private String winner;

    public Matches(int matchId, int season, String team1, String team2, String winner) {
        this.matchId = matchId;
        this.season = season;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getSeason() {
        return season;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getWinner() {
        return winner;
    }
}
