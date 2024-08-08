package onlinevotingsystem;

public class ElectionList
{
    public ElectionList(int id,String electionName, String elecDate)
    {
        super();
        this.id = id;
        this.electionName = electionName;
        this.elecDate = elecDate;

    }

    public ElectionList()
    {

    }

    private int id;
    private String electionName;
    private String elecDate;

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElectionName()
    {
        return electionName;
    }

    public void setElectionName(String electionName)
    {
        this.electionName = electionName;
    }

    public String getElecDate()
    {
        return elecDate;
    }

    public void setElecDate(String elecDate) {
        this.elecDate = elecDate;
    }
}
