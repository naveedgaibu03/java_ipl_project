class Deliveries {
    private int matchId;
    private String bowler;
    private int runs;
    private int extraRuns;

    public Deliveries(int matchId, String bowler, int runs, int extraRuns) {
        this.matchId = matchId;
        this.bowler = bowler;
        this.runs = runs;
        this.extraRuns = extraRuns;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getBowler() {
        return bowler;
    }

    public int getRuns() {
        return runs;
    }

    public int getExtraRuns() {
        return extraRuns;
    }
}
