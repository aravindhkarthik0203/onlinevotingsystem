package onlinevotingsystem;


import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO
{
    static DBconnection dBconnection = new DBconnection();

    public void RegisterAdmin(AdminRegistration AdminRegistration) throws SQLException
    {
        String Query = "INSERT INTO admin (name,adharno,mobileno,userid,password) values(?,?,?,?,?)";
        try(Connection connection = dBconnection.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(Query))
        {
            preparedstatement.setString(1, AdminRegistration.getName());
            preparedstatement.setLong(2,AdminRegistration.getAdharNum());
            preparedstatement.setLong(3,AdminRegistration.getMobilNum());
            preparedstatement.setInt(4,AdminRegistration.getUserId());
            preparedstatement.setString(5,AdminRegistration.getPassWord());

            int rs = preparedstatement.executeUpdate();
            if (rs > 0)
            {
                System.out.println("ADMIN REGISTERED SUCCESSFULLY.....!!!");
                System.out.println("PLEASE SAVE REGISTRATION USER ID :" + AdminRegistration.getUserId());
            }
            else
            {
                System.out.println("SOMETHING WENT WRONG....!!!");
            }

        }
    }

    public void RegisterVoter(VoterRegistration VoterRegistration) throws SQLException
    {
        String Query1 = "INSERT INTO voters (voterid,name,age,adharno,mobileno,password) values(?,?,?,?,?,?)";
        try(Connection connection = dBconnection.getConnection();
            PreparedStatement preparedstatement1 = connection.prepareStatement(Query1))
        {
            preparedstatement1.setString(1,VoterRegistration.getVoterId());
            preparedstatement1.setString(2, VoterRegistration.getName());
            preparedstatement1.setInt(3, VoterRegistration.getVage());
            preparedstatement1.setLong(4,VoterRegistration.getAdharNum());
            preparedstatement1.setLong(5,VoterRegistration.getMobilNum());
            preparedstatement1.setString(6,VoterRegistration.getPassWord());

            int rs = preparedstatement1.executeUpdate();
            if (rs > 0)
            {
                System.out.println("VOTER REGISTERED SUCCESSFULLY.....!!!");
            }
            else
            {
                System.out.println("SOMETHING WENT WRONG....!!!");
            }

        }
    }

    public void LoginAdmin(int auserid,String apass) throws SQLException
    {
        String sql = "SELECT * FROM admin where userid=? and password=?";
        try(Connection connection = dBconnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,auserid);
            preparedStatement.setString(2,apass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                System.out.println("Login Successfully--------!!!!");

                boolean loginAdmin = true;

                while (loginAdmin)
                {
                    System.out.println("--------WELCOME"+" " +resultSet.getInt("userid")+":------------");
                    System.out.println("1. Add Election");
                    System.out.println("2. Add Candidate");
                    System.out.println("3. View Election List");
                    System.out.println("4. View Candidate List");
                    System.out.println("5. View Results");
                    System.out.println("6. View Votes");
                    System.out.println("7. Logout");
                    System.out.println("========== ENTER THE OPTION ===========");

                    Scanner in = new Scanner(System.in);
                    int choice = Integer.parseInt(in.nextLine());

                    switch (choice)
                    {
                        case 1:
                            AddElection(connection);
                            break;

                        case 2:
                            AddCandidate(connection);
                            break;
                        case 3:
                            ViewAllElectionList(connection);
                            break;
                        case 4:
                            System.out.println("====== VIEW CANDIDATE LIST ======");
                            List<CandidateList> list1 = getAllCandidateList();
                            System.out.println("============ ELECTION LIST =============");
                            System.out.println("ID  ELECTION  CANDIDATE NAME  PARTY NAME");
                            for(CandidateList CandidateList : list1)
                            {
                                System.out.println(CandidateList.getId() +"  "+CandidateList.getElection()+"  "+
                                        CandidateList.getCandidateName()+"  "+CandidateList.getPartyName());
                            }
                            System.out.println(" ");
                            break;

                        case 5:

                            ViewAllElectionList(connection);
                            System.out.println("Enter Election Name From Above List :");
                            String electionName = in.nextLine();
                            ViewVoteResults(connection,electionName);
                            break;

                        case 6:
                            ViewVote(connection);
                            break;

                        case 7:
                            loginAdmin = false;
                            System.out.println("Logout Successfully------!!!!");
                            break;

                        default:
                            System.out.println("INVALID INPUT!!");
                            break;
                    }
//                    break;
                }
            }
            else
            {
                System.out.println("Login Failed--------!!!");

            }
        }


    }

    public void LoginCandidate(String cvoterid,String cpass) throws SQLException
    {
        String sql = "SELECT * FROM candidate where cvoterid=? and password=?";
        try(Connection connection = dBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1,cvoterid);
            preparedStatement.setString(2,cpass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                System.out.println("Login Successfully--------!!!!");

                boolean loginCandidate = true;

                while (loginCandidate)
                {
                    System.out.println("--------WELCOME"+" " +resultSet.getString("cname")+":------------");
                    System.out.println("1. View Election Details");
                    System.out.println("2. View Profile ");
                    System.out.println("3. Change Password");
                    System.out.println("4. Logout");

                    Scanner in = new Scanner(System.in);
                    int choice = Integer.parseInt(in.nextLine());
                    List<CandidateList> list1 = getCandidateList(cvoterid);

                    switch (choice)
                    {
                        case 1:

                            System.out.println("============ ELECTION DETAILS =============");
                            System.out.println(" ======== ELECTION NAME ======");
                            for(CandidateList CandidateList : list1)
                            {
                                System.out.println("ELECTION : "+CandidateList.getElection());
                                System.out.println("END DATE : "+CandidateList.getElecDate());
                            }
                            break;

                        case 2:
                            ViewCanProfile(connection);
                            break;

                        case 3:
                            UpdateCanPass(connection);
                            break;

                        case 4:
                            loginCandidate = false;
                            System.out.println("Logout Successfylly------!!!!");
                            break;

                        default:
                            System.out.println("INVALID INPUT!!");
                            break;
                    }
//                    break;
                }
            }
            else
            {
                System.out.println("Login Failed--------!!!");

            }
        }

    }

    public void LoginVoter(String vvoterid, String vpass) throws SQLException
    {
        String sql = "SELECT * FROM voters where voterid=? and password=?";
        try(Connection connection = dBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1,vvoterid);
            preparedStatement.setString(2,vpass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                System.out.println("Login Successfully--------!!!!");

                boolean loginVoter = true;

                while (loginVoter)
                {
                    System.out.println("--------WELCOME"+" " +resultSet.getString("name")+":------------");
                    System.out.println("1. Election");
                    System.out.println("2. View Profile");
                    System.out.println("3. Logout");

                    Scanner in = new Scanner(System.in);
                    int choice = Integer.parseInt(in.nextLine());

                    switch (choice)
                    {
                        case 1:
                           Election(connection);
                            break;

                        case 2:
                            ViewProfile(connection);
                            break;

                        case 3:
                            loginVoter = false;
                            System.out.println("Logout Successfylly------!!!!");
                            break;

                        default:
                            System.out.println("INVALID INPUT!!");
                            break;
                    }
//                    break;
                }
            }
            else
            {
                System.out.println("Login Failed--------!!!");

            }
        }

    }

    public static void AddElection(Connection connection) throws SQLException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("================= ADD ELECTION PAGE =================");
        System.out.println(" Enter Election Name :");
        String elecName = in.nextLine();

        System.out.println("Please enter End  Date of Add Electoin (in format dd/MM/yyyy): ");
        String elecDate = in.nextLine();


        String Sql = "INSERT INTO election (electionname,elecdate) values(?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(Sql))
        {
            preparedStatement.setString(1,elecName);

            preparedStatement.setString(2,elecDate);

            int rs = preparedStatement.executeUpdate();

            if (rs >0)
            {
                System.out.println("Election Added successfully---!!!");

            }
            else
            {
                System.out.println("Failed to Add Election...!!!");
            }

        }

    }
    public static void AddCandidate(Connection connection) throws SQLException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("================ ADD CANDIDATE PAGE ================");

        System.out.println("  ");
        System.out.println(" Select Election From Below List :");
        System.out.println();
        List<ElectionList> list = getAllElectionList();
        System.out.println("============ ELECTION LIST =============");
        System.out.println("ID      ELECTION NAME     DATE OF NOMINATION");
        for(ElectionList ElectionList : list)
        {
            System.out.println(ElectionList.getId() +"    "+ElectionList.getElectionName()+"    "+ ElectionList.getElecDate());
        }
        System.out.println(" ");

        System.out.println("Enter a Election Name from Above List :");
        String electionName = in.nextLine();

        System.out.println(" Enter Candidate Name :");
        String cname = in.nextLine();

        if(isValidName(cname))
        {
            System.out.println(" Enter Candidate Age :");
            int cAge = in.nextInt();
            in.nextLine();

            if(cAge > 21)
            {
                System.out.println(" Enter Candidate Voter ID :");
                String voterID = in.nextLine();

                if(isvalidateVoterId(voterID))
                {
                    System.out.println(" Enter Party Name :");
                    String partyName = in.nextLine();

                    System.out.println(" Enter Candidate Adhar Number :");
                    long adharno = in.nextLong();
                    in.nextLine();

                    if(isValidAdharNumber(adharno))
                    {
                        System.out.println(" Enter Candidate Mobile Number :");
                        long mobileNo = in.nextLong();
                        in.nextLine();

                        if(isValidMobileNumber(mobileNo))
                        {
                            System.out.println(" Enter Candidate Address :");
                            String address = in.nextLine();

                            System.out.println(" Enter Password :");
                            String password = in.nextLine();

                            if(isValidEmptyPassword(password))
                            {
                                if(isValidPassword(password))
                                {
                                    System.out.println( " Enter Date of Nomination(in format dd/MM/yyyy) :");
                                    String date = in.nextLine();

                                    String Sql = "INSERT INTO candidate (election,cvoterid,cname,age,partyname,cadharno,cmobileno,address,password,elecdate) values (?,?,?,?,?,?,?,?,?,?)";
                                    try(PreparedStatement preparedStatement = connection.prepareStatement(Sql))
                                    {
                                        preparedStatement.setString(1,electionName);
                                        preparedStatement.setString(2,voterID);
                                        preparedStatement.setString(3,cname);
                                        preparedStatement.setInt(4,cAge);
                                        preparedStatement.setString(5,partyName);
                                        preparedStatement.setLong(6,adharno);
                                        preparedStatement.setLong(7,mobileNo);
                                        preparedStatement.setString(8,address);
                                        preparedStatement.setString(9,password);
                                        preparedStatement.setString(10,date);

                                        int rs = preparedStatement.executeUpdate();

                                        if (rs >0)
                                        {
                                            System.out.println("Candidate Added successfully---!!!");

                                        }
                                        else
                                        {
                                            System.out.println("Failed to Add Candidate...!!!");
                                        }
                                    }

                                }
                                else
                                {
                                    System.out.println("Make sure Password Contain 8 Characters!!!");
                                }

                            }
                            else
                            {
                                System.out.println("Error:Invalid Password !..Empty Password!!!");
                            }


                        }
                        else
                        {
                            System.out.println("Invalid mobile number format. Please enter a 10-digit mobile number.!!!");
                        }
                    }
                    else
                    {
                        System.out.println("Invalid Adhar number format. Please enter a 12-digit mobile number.!!!");
                    }

                }
                else
                {
                    System.out.println("INVALID Voter ID..Please enter a valid Voter ID( EX121WQDS2 ).!!");
                }
            }
            else
            {
                System.out.println("Your Not Eligible for Candidate to the Elecction.");
                System.out.println("Candidate Age Must be Greater than 21.");
            }

        }
        else
        {
            System.out.println("INVALID  NAME!!...Please enter a First Name.!!!");
        }

    }

    public static void Election(Connection connection) throws SQLException
    {
            Scanner in = new Scanner(System.in);

            List<ViewVote> list3 = getViewVotedList();
            System.out.println("================== ELECTION PAGE =================");
            System.out.println("Enter Your Name :");
            String vName = in.nextLine();

            System.out.println("Enter Your Voter ID :");
            String vvoterid = in.nextLine();


            boolean alreadyVoted = false;

            for (ViewVote viewVote : list3)
            {
                if (Objects.equals(viewVote.getVoterId(), vvoterid)) {
                    alreadyVoted = true;
                    break;
                }
            }

            if (alreadyVoted)
            {
                System.out.println("YOU ARE ALREADY VOTED......!!!");
            } else
            {

                System.out.println("  ");
                System.out.println(" Select Election From Below List :");
                ViewAllElectionList(connection);
                System.out.println();
                System.out.println("Select Candidate From The Below List :");
                List<CandidateList> list1 = getAllCandidateList();
                System.out.println("============ ELECTION LIST =============");
                System.out.println("ID  ELECTION  CANDIDATE NAME  PARTY NAME");
                for(CandidateList CandidateList : list1)
                {
                    System.out.println(CandidateList.getId() +"  "+CandidateList.getElection()+"  "+
                            CandidateList.getCandidateName()+"  "+CandidateList.getPartyName());
                }
                System.out.println(" ");

                System.out.println(" Enter Election Name :");
                String vElection = in.nextLine();

                System.out.println(" Enter Candidate Name :");
                String cName = in.nextLine();

                System.out.println(" Enter Party Name :");
                String partyName = in.nextLine();

                System.out.println(" Enter Your Vote (Voted):");
                String vote = in.nextLine();

                String Sql = "INSERT INTO voteresult (votername,voterid,electionname,cname,partyname,vote) values (?,?,?,?,?,?)";
                try(PreparedStatement preparedStatement = connection.prepareStatement(Sql))
                {
                    preparedStatement.setString(1, vName);
                    preparedStatement.setString(2, vvoterid);
                    preparedStatement.setString(3, vElection);
                    preparedStatement.setString(4, cName);
                    preparedStatement.setString(5, partyName);
                    preparedStatement.setString(6, vote);

                    int rs = preparedStatement.executeUpdate();

                    if (rs > 0)
                    {
                        System.out.println("You Voted Successfullly---!!!");

                    } else
                    {
                        System.out.println("You Already Voted...!!!");
                    }

                }

            }

    }
    public static void ViewProfile(Connection connection) throws SQLException
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Your Voter ID :");
        String voterid = in.nextLine();

        List<VoterProfile> list2 = getVoterDetails(voterid);
        for (VoterProfile VoterProfile : list2)
        {	System.out.println(" ");
            System.out.println("--------------------------- VOTERS PROFILE  DETAILS---------------------------------");
            System.out.println(" NAME :" + VoterProfile.getVname());
            System.out.println(" AGE : "+ VoterProfile.getVage());
            System.out.println(" VOTER ID :" +VoterProfile.getVoterid());
            System.out.println(" ADHAR NUMBER :"+VoterProfile.getAdharno());
            System.out.println(" MOBILE NUMBER :"+VoterProfile.getMobileno());
            System.out.println("=====================================================================================");
            System.out.println(" ");
        }
    }
    public static void ViewCanProfile(Connection connection) throws SQLException
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Your Voter ID :");
        String cvoterid = in.nextLine();

        List<CandidateList> list1 = getCandidateList(cvoterid);
        for (CandidateList CandidateList : list1)
        {	System.out.println(" ");
            System.out.println("--------------------------- VOTERS PROFILE  DETAILS---------------------------------");
            System.out.println("NAME :" + CandidateList.getCandidateName());
            System.out.println(" AGE :"+CandidateList.getCage());
            System.out.println("VOTER ID :" +CandidateList.getVoterID());
            System.out.println("PARTY NAME :" +CandidateList.getPartyName());
            System.out.println(" ADHAR NUMBER :"+CandidateList.getCanAdhar());
            System.out.println(" MOBILE NUMBER :"+CandidateList.getCanMobile());
            System.out.println(" ADDRESS :" +CandidateList.getCanAddress());
            System.out.println("=====================================================================================");
            System.out.println(" ");
        }
    }

    public static void ViewVoteResults(Connection connection, String electionType) throws SQLException
    {
        String sql = "SELECT * FROM voteresult WHERE electionname = ?";

        try (Connection connection1 = dBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, electionType);

            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                Map<String, Integer> partyNameCounts = new HashMap<>();

                while (resultSet.next()) {
                    String partyName = resultSet.getString("partyname");
                    partyNameCounts.put(partyName, partyNameCounts.getOrDefault(partyName, 0) + 1);
                }

                System.out.println("============= ALL PARTIES TOTAL VOTE COUNT  ==============");
                System.out.println("ELECTION TYPE : "+ electionType);
                System.out.println(" ");

                String maxPartyName = null;
                int maxCount = 0;

                for (Map.Entry<String, Integer> entry : partyNameCounts.entrySet())
                {
                    System.out.println(entry.getKey() + ": " + entry.getValue());

                    if (entry.getValue() > maxCount)
                    {
                        maxCount = entry.getValue();
                        maxPartyName = entry.getKey();
                    }
                }

                System.out.println(" ");
                System.out.println("The Highest Voted Party is : " + maxPartyName + " with a  Vote Count  of: " + maxCount);

                System.out.println("============= RESULTS FOR " + electionType + " ==============");
                System.out.println(" ");

                try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql)) {
                    preparedStatement2.setString(1, electionType);

                    try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {

                        Map<String, Integer> candidateNameCounts = new HashMap<>();

                        while (resultSet2.next()) {
                            String cname = resultSet2.getString("cname");
                            candidateNameCounts.put(cname, candidateNameCounts.getOrDefault(cname, 0) + 1);
                        }

                        boolean foundDuplicate = false;

                        System.out.println("The Winner Candidate of Election of " + electionType + ":");
                        for (Map.Entry<String, Integer> entry : candidateNameCounts.entrySet()) {
                            if (entry.getValue() > 1) {
                                foundDuplicate = true;
                                System.out.println(entry.getKey() + " to get " + entry.getValue()+" Votes. ");
                            }
                        }

                        if (!foundDuplicate) {
                            System.out.println("No Results are  found for Election of " + electionType);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void ViewVote(Connection connection) throws  SQLException
    {
        System.out.println("================== VIEW VOTES PAGE ==================");
        System.out.println(" ");
        List<ViewVote> list3 = getViewVotedList();
        System.out.println("--------------------------- VOTED LIS DETAILS---------------------------------");
        System.out.println("=====================================================================================");
        for (ViewVote ViewVote : list3)
        {
            System.out.println("VOTER NAME     : "+ViewVote.getVoterName());
            System.out.println("VOTER ID       : "+ViewVote.getVoterId());
            System.out.println("ELECTION NAME  : "+ViewVote.getElectionname());
            System.out.println("CANDIDATE NAME : "+ViewVote.getCname());
            System.out.println("PARTY NAME     : "+ViewVote.getPartyName());
            System.out.println("VOTE           : "+ViewVote.getVote());
            System.out.println(" ");
            System.out.println("=====================================================================================");
        }
    }

    public static void ViewAllElectionList(Connection connection)throws SQLException
    {
        System.out.println("====== VIEW ELECTION LIST ======");
        List<ElectionList> list = getAllElectionList();
        System.out.println("ID    ELECTION NAME    DATE");
        for(ElectionList ElectionList : list)
        {
            System.out.println(ElectionList.getId() +"  "+ElectionList.getElectionName()+"  "+
                    ElectionList.getElecDate());
        }
        System.out.println(" ");
    }
    public static List<ViewVote> getViewVotedList() throws SQLException
    {
        List<ViewVote> list3 = new ArrayList<>();
        ViewVote ViewVote = null;
        String sql = "SELECT * FROM voteresult";
        try (Connection connection = dBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
               ViewVote = new ViewVote();

                ViewVote.setVoterName(resultSet.getString("votername"));
                ViewVote.setVoterId(resultSet.getString("voterid"));
                ViewVote.setElectionname(resultSet.getString("electionname"));
                ViewVote.setCname(resultSet.getString("cname"));
                ViewVote.setPartyName(resultSet.getString("partyname"));
                ViewVote.setVote(resultSet.getString("vote"));

                list3.add(ViewVote);

            }
        }
        return list3;
    }
    public static List<VoterProfile> getVoterDetails(String voterid) throws SQLException
    {
        List<VoterProfile> list2 = new ArrayList<>();
        VoterProfile VoterProfile = null;
        String sql = "SELECT voterid,name,age,adharno,mobileno  FROM voters where voterid=?";
        try (Connection connection = dBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1,voterid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                VoterProfile = new VoterProfile();

                VoterProfile.setVname(resultSet.getString("name"));
                VoterProfile.setVage(resultSet.getInt("age"));
                VoterProfile.setVoterid(resultSet.getString("voterid"));
                VoterProfile.setAdharno(resultSet.getLong("adharno"));
                VoterProfile.setMobileno(resultSet.getLong("mobileno"));

                list2.add(VoterProfile);

            }
        }
        return list2;
    }

    public static List<ElectionList> getAllElectionList() throws SQLException
    {
        List<ElectionList> list = new ArrayList<>();
        ElectionList ElectionList = null;
        String sql = "SELECT * FROM election";
        try (Connection connection = dBconnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next())
           {
               ElectionList = new ElectionList();

               ElectionList.setId(resultSet.getInt("id"));
               ElectionList.setElectionName(resultSet.getString("electionname"));
               ElectionList.setElecDate(resultSet.getString("elecdate"));

               list.add(ElectionList);

           }
        }
        return list;
    }

    public static List<CandidateList> getCandidateList(String cvoterid) throws SQLException
    {
        List<CandidateList> list1 = new ArrayList<>();
        CandidateList CandidateList = null;
        String sql = "SELECT * FROM candidate where cvoterid=?";
        try (Connection connection = dBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1,cvoterid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                CandidateList = new CandidateList();

                CandidateList.setId(resultSet.getInt("id"));
                CandidateList.setElection(resultSet.getString("election"));
                CandidateList.setVoterID(resultSet.getString("cvoterid"));
                CandidateList.setCandidateName(resultSet.getString("cname"));
                CandidateList.setPartyName(resultSet.getString("partyname"));
                CandidateList.setCanAdhar(resultSet.getLong("cadharno"));
                CandidateList.setCanMobile(resultSet.getLong("cmobileno"));
                CandidateList.setCanAddress(resultSet.getString("address"));
                CandidateList.setPassword(resultSet.getString("password"));
                CandidateList.setElecDate(resultSet.getString("elecdate"));
                CandidateList.setCage(resultSet.getInt("age"));

                list1.add(CandidateList);

            }
        }
        return list1;
    }

    public static List<CandidateList> getAllCandidateList() throws SQLException
    {
        List<CandidateList> list1 = new ArrayList<>();
        CandidateList CandidateList = null;
        String sql = "SELECT * FROM candidate ";
        try (Connection connection = dBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                CandidateList = new CandidateList();

                CandidateList.setId(resultSet.getInt("id"));
                CandidateList.setElection(resultSet.getString("election"));
                CandidateList.setVoterID(resultSet.getString("cvoterid"));
                CandidateList.setCandidateName(resultSet.getString("cname"));
                CandidateList.setPartyName(resultSet.getString("partyname"));
                CandidateList.setCanAdhar(resultSet.getLong("cadharno"));
                CandidateList.setCanMobile(resultSet.getLong("cmobileno"));
                CandidateList.setCanAddress(resultSet.getString("address"));
                CandidateList.setPassword(resultSet.getString("password"));
                CandidateList.setElecDate(resultSet.getString("elecdate"));

                list1.add(CandidateList);

            }
        }
        return list1;
    }
    private static void UpdateCanPass(Connection connection) throws SQLException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("=============================ENTER YOUR DETAILS==============================");
        System.out.println("Enter your Voter ID: ");
        String cvoterid = in.nextLine();


        System.out.print("Enter new Password: ");
        String newPass = in.nextLine();
        System.out.println("=====================================================================================");

        String SQL = "UPDATE candidate SET password = ? WHERE cvoterid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, cvoterid);

            int rs = preparedStatement.executeUpdate();

            if (rs > 0)
            {
                System.out.println("Password updated successfully!");
                System.out.println("=====================================================================================");
            }
            else
            {
                System.out.println("Failed to update Password ...!");
                System.out.println("=====================================================================================");
            }
        }
    }

    private static  List<VoteResults> getVoteResult() throws SQLException
    {
        List<VoteResults> list4 = new ArrayList<>();
        VoteResults VoteResults = null;
        String sql ="SELECT * FROM voteresult";

        try(Connection connection = dBconnection.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                VoteResults = new VoteResults();
                VoteResults.setElecName(resultSet.getString("electionname"));
                VoteResults.setcName(resultSet.getString("cname"));
                VoteResults.setPartyName(resultSet.getString("partyname"));

                list4.add(VoteResults);
            }
        }

        return list4;
    }

    public static boolean isValidName(String firstn)
    {
        String regex = "^(?=.*[a-zA-Z\s_.])[a-zA-Z\s_.]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(firstn);
        return matcher.matches();
    }
    public static boolean isvalidateVoterId(String voterid) {
        String regex = "^[a-zA-Z0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(voterid);
        return matcher.matches();
    }
    public static boolean isValidAdharNumber(long mobileno)
    {
        String regex = "\\A[0-9]{12}\\z";
        String numberString = Long.toString(mobileno);
        return numberString.matches(regex);
    }
    public static boolean isValidMobileNumber(long mobileno)
    {
        String regex = "\\A[0-9]{10}\\z";
        String numberString = Long.toString(mobileno);
        return numberString.matches(regex);
    }

    public static boolean isValidPassword(String password)
    {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidEmptyPassword(String password) {

        return password != null && !password.isEmpty();
    }

}
