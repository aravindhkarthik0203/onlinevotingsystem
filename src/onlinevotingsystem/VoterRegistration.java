package onlinevotingsystem;

public class VoterRegistration
{
    public VoterRegistration(String name,long adharNum,long mobileNum,int vage,String voterid,String passWord)
    {
        super();
        this.name = name;
        this.adharNum = adharNum;
        this.mobileNum = mobileNum;
        this.vage = vage;
        this.voterid = voterid;
        this.passWord = passWord;
    }

    public VoterRegistration()
    {

    }

    private String name;
    private long adharNum;
    private long mobileNum;
    private int vage;
    private String voterid;
    private String passWord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdharNum() {
        return adharNum;
    }

    public void setAdharNum(long adharNum) {
        this.adharNum = adharNum;
    }

    public long getMobilNum()
    {
        return mobileNum;
    }
    public void setMobileNum(long mobileNum) {
        this.mobileNum = mobileNum;
    }

    public int getVage() {
        return vage;
    }

    public void setVage(int vage) {
        this.vage = vage;
    }

    public String getVoterId() {
        return voterid;
    }

    public void setVoterId(String voterid) {
        this.voterid = voterid;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
