package onlinevotingsystem;

public class CandidateList
{
    public CandidateList( int id,String election,String voterID,String candidateName, String partyName,
    long canAdhar,long canMobile, String canAddress, String password , String elecDate,int cage)
    {
        super();
        this.id = id;
        this.election = election;
        this.voterID = voterID;
        this.candidateName = candidateName;
        this.partyName = partyName;
        this.canAdhar = canAdhar;
        this.canMobile = canMobile;
        this.canAddress = canAddress;
        this.password = password;
        this.elecDate = elecDate;
        this.cage = cage;


    }

    public CandidateList()
    {

    }
    private int id;
    private String election;
    private String voterID;
    private String candidateName;
    private  String partyName;
    private long canAdhar;
    private long canMobile;
    private  String canAddress;
    private  String password;
    private  String elecDate;
    private int cage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    public String getVoterID() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public long getCanAdhar() {
        return canAdhar;
    }

    public void setCanAdhar(long canAdhar) {
        this.canAdhar = canAdhar;
    }

    public long getCanMobile() {
        return canMobile;
    }

    public void setCanMobile(long canMobile) {
        this.canMobile = canMobile;
    }

    public String getCanAddress() {
        return canAddress;
    }

    public void setCanAddress(String canAddress) {
        this.canAddress = canAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getElecDate() {
        return elecDate;
    }

    public void setElecDate(String elecDate) {
        this.elecDate = elecDate;
    }

    public int getCage()
    {
        return cage;
    }

    public void setCage(int cage) {
        this.cage = cage;
    }
}
