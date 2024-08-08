package onlinevotingsystem;

public class VoteResults
{
    public VoteResults(String elecName,String cName,String partyName)
    {
        super();
        this.elecName = elecName;
        this.cName = cName;
        this.partyName = partyName;
//        this.voteCount = voteCount;

    }
    public VoteResults()
    {

    }

    private String elecName;
    private String cName;
    private String partyName;
//    private int voteCount;

    public String getElecName() {
        return elecName;
    }

    public void setElecName(String elecName) {
        this.elecName = elecName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

//    public int getVoteCount() {
//        return voteCount;
//    }
//
//    public void setVoteCount(int voteCount) {
//        this.voteCount = voteCount;
//    }

}
