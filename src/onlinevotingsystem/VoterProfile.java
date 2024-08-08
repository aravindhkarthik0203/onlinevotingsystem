package onlinevotingsystem;

public class VoterProfile
{
    public VoterProfile(String vname, String voterid , long adharno, long mobileno,int vage)
    {
        super();
        this.vname = vname;
        this.voterid = voterid;
        this.adharno = adharno;
        this.mobileno = mobileno;
        this.vage = vage;

    }

    public VoterProfile()
    {

    }

    private String vname;
    private String voterid;
    private long adharno;
    private long mobileno;
    private int vage;

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVoterid() {
        return voterid;
    }

    public void setVoterid(String voterid) {
        this.voterid = voterid;
    }

    public long getAdharno() {
        return adharno;
    }

    public void setAdharno(long adharno) {
        this.adharno = adharno;
    }

    public long getMobileno() {
        return mobileno;
    }

    public void setMobileno(long mobileno) {
        this.mobileno = mobileno;
    }

    public int getVage()
    {
        return vage;
    }

    public void setVage(int vage) {
        this.vage = vage;
    }
}
