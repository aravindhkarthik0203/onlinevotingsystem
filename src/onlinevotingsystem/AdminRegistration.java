package onlinevotingsystem;

public class AdminRegistration
{
    public AdminRegistration(String name,long adharNum, long mobileNum,int userid,String passWord)
    {
        super();
        this.name = name;
        this.adharNum = adharNum;
        this.mobilNum = mobileNum;
        this.userid = userid;
        this.passWord = passWord;

    }

    public AdminRegistration()
    {

    }

    private String name;
    private long adharNum;
    private long mobilNum;
    private int userid;
    private String passWord;


    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public long getAdharNum()
    {
        return adharNum;
    }
    public void setAdharNum(long adharNum)
    {
        this.adharNum = adharNum;
    }

    public long getMobilNum()
    {
        return mobilNum;
    }
    public void setMobilNum(long mobilNum)
    {
        this.mobilNum = mobilNum;
    }

    public int getUserId() {
        return userid;
    }
    public void setUserId(int userid) {
        this.userid = userid;
    }


    public String getPassWord()
    {
        return passWord;
    }
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
}
