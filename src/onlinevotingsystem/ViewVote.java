package onlinevotingsystem;

public class ViewVote
{
    public ViewVote(String voterName, String voterId , String electionname,
                    String cName, String partyName, String vote)
    {
        super();
        this.voterName = voterName;
        this.voterId = voterId;
        this.electionname = electionname;
        this.cname = cName;
        this.partyName = partyName;
        this.vote = vote;
    }

    public ViewVote()
    {

    }

    private String voterName;
    private String voterId;
    private String electionname;
    private String cname;
    private String partyName;
    private String vote;

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getElectionname() {
        return electionname;
    }

    public void setElectionname(String electionname) {
        this.electionname = electionname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
