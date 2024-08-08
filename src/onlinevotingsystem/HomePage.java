package onlinevotingsystem;


import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage
{
    public static boolean isValidName(String firstn)
    {
        String regex = "^(?=.*[a-zA-Z\s_.])[a-zA-Z\s_.]{3,30}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(firstn);
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
    public static boolean isvalidateVoterId(String voterid) {
        String regex = "^[a-zA-Z0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(voterid);
        return matcher.matches();
    }

    public static void main(String[] args) throws Exception
    {
        UserDAO ud = new UserDAO();
        Scanner in = new Scanner(System.in);

        boolean option = true;
        while (option)
        {
            System.out.println(" ======================== WELCOME TO ONLINE VOTING SYSTEM ======================== ");
            System.out.println("1. ADMIN PAGE");
            System.out.println("2. CANDIDATE PAGE");
            System.out.println("3. VOTERS PAGE");
            System.out.println("4. EXIT");
            System.out.println("======================== ENTER THE OPTION ========================");
            int inneroption = Integer.parseInt(in.nextLine());

            switch (inneroption)
            {
                case 1:

                    boolean Option1= true;
                    while (Option1)
                    {
                        System.out.println("======================== ADMIN PAGE ========================");
                        System.out.println(" 1. Admin Registration");
                        System.out.println(" 2. Admin Login");
                        System.out.println(" 3. Exit");
                        System.out.println("======================== ENTER THE OPTION ========================");
                        int ch = Integer.parseInt(in.nextLine());

                        switch (ch)
                        {
                            case 1:
                                System.out.println("======================== ADMIN REGISTRATION PAGE ========================");
                                System.out.println(" Enter Your Name : ");
                                String name = in.nextLine();

                                if(isValidName(name))
                                {
                                    System.out.println(" Enter Your Adhar Number : ");
                                    long adharNum = in.nextLong();
                                    in.nextLine();

                                    if(isValidAdharNumber(adharNum))
                                    {
                                        System.out.println(" Enter Your Mobile Number : ");
                                        long mobileNum = in.nextLong();
                                        in.nextLine();

                                        if(isValidMobileNumber(mobileNum))
                                        {
                                            System.out.println("Enter a Password :");
                                            String passWord = in.nextLine();

                                            if(isValidEmptyPassword(passWord))
                                            {
                                                if(isValidPassword(passWord))
                                                {
                                                    Random random = new Random();
                                                    int userid = random.nextInt(99999);

                                                    ud.RegisterAdmin(new AdminRegistration(name,adharNum,mobileNum,userid,passWord));
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("Make sure Password Contain 8 Characters!!!");
                                                    break;
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("Error:Invalid Password !..Empty Password!!!");
                                                break;
                                            }

                                        }
                                        else
                                        {
                                            System.out.println("Invalid mobile number format. Please enter a 10-digit mobile number.!!!");
                                            break;
                                        }

                                    }
                                    else
                                    {
                                        System.out.println("Invalid Adhar number format. Please enter a 12-digit mobile number.!!!");
                                        break;
                                    }
                                }
                                else
                                {
                                    System.out.println("INVALID  NAME!!...Please enter a First Name.!!!");
                                    break;
                                }


                            case 2:
                                System.out.println("======================== ADMIN LOGIN PAGE ========================");

                                System.out.println(" Enter Registration ID :");
                                int auserid = in.nextInt();
                                in.nextLine();

                                System.out.println("Enter Password :");
                                String apass = in.nextLine();

                                ud.LoginAdmin(auserid,apass);
                                break;

                            case 3:
                                Option1 = false;
                                System.out.println("Exit");
                                break;

                            default:
                                System.out.println("Invalid Input. Please Enter a Valid Option");
                                break;
                        }
//                        break;
                    }
                    break;

                case 2:
                    System.out.println("======================== CANDIDATE LOGIN PAGE  ========================");
                    System.out.println(" Enter Voter ID :");
                    String cvoterid = in.nextLine();


                    System.out.println("Enter Password :");
                    String cpass = in.nextLine();

                    ud.LoginCandidate(cvoterid,cpass);
                    break;

                case 3:
                    boolean option3 = true;
                    while (option3)
                    {
                        System.out.println("======================== USER PAGE ========================");
                        System.out.println("1. Register Voter");
                        System.out.println("2. Login Voter");
                        System.out.println("3. Exit");
                        System.out.println("======================== ENTER THE OPTION ========================");

                        int ch1 = Integer.parseInt(in.nextLine());

                        switch (ch1)
                        {
                            case 1:
                                System.out.println("======================== REGISTER VOTERS ========================");
                                System.out.println(" Enter Your Name : ");
                                String name = in.nextLine();

                                if(isValidName(name))
                                {
                                    System.out.println(" Enter Your Adhar Number : ");
                                    long adharNum = in.nextLong();
                                    in.nextLine();

                                    if (isValidAdharNumber(adharNum))
                                    {
                                        System.out.println(" Enter Your Mobile Number : ");
                                        long mobileNum = in.nextLong();
                                        in.nextLine();

                                        if(isValidMobileNumber(mobileNum))
                                        {
                                            System.out.println("Enter Your Age : ");
                                            int vage = in.nextInt();
                                            in.nextLine();

                                            if(vage >= 18)
                                            {
                                                System.out.println(" Enter The Voter ID :");
                                                String voterid = in.nextLine();

                                                if(isvalidateVoterId(voterid))
                                                {
                                                    System.out.println("Enter a Password :");
                                                    String passWord = in.nextLine();

                                                    if(isValidEmptyPassword(passWord))
                                                    {
                                                        if(isValidPassword(passWord))
                                                        {
                                                            ud.RegisterVoter(new VoterRegistration(name,adharNum,mobileNum,vage,voterid,passWord));
                                                            break;
                                                        }
                                                        else
                                                        {
                                                            System.out.println("Make sure Password Contain 8 Characters!!!");
                                                            break;

                                                        }

                                                    }
                                                    else
                                                    {
                                                        System.out.println("Error:Invalid Password !..Empty Password!!!");
                                                        break;
                                                    }

                                                }
                                                else
                                                {
                                                    System.out.println("INVALID Voter ID..Please enter a valid Voter ID( EX121WQDS2 ).!!");
                                                    break;
                                                }

                                            }
                                            else
                                            {
                                                System.out.println("Your Not Eligible for Vote..!!!");
                                                break;
                                            }

                                        }
                                        else
                                        {
                                            System.out.println("Invalid mobile number format. Please enter a 10-digit mobile number.\n");
                                            break;
                                        }

                                    }
                                    else
                                    {
                                        System.out.println("Invalid Adhar number format. Please enter a 12-digit mobile number.!!!");
                                        break;
                                    }

                                }
                                else
                                {
                                    System.out.println("INVALID  NAME!!...Please enter a First Name.!!");
                                    break;
                                }

                            case 2:
                                System.out.println("======================== LOGIN VOTERS ========================");
                                System.out.println(" Enter Voter ID :");
                                String vvoterid = in.nextLine();

                                System.out.println("Enter Password :");
                                String vpass = in.nextLine();

                                ud.LoginVoter(vvoterid,vpass);
                                break;
                            case 3:
                                option3 = false;
                                break;
                            default:
                                System.out.println("INVALID OPTION");
                        }
                    }

                    break;

                case 4 :
                    option = false;
                    System.out.println("EXIT");
                    System.out.println("THANK YOU");
                    break;

                default:
                    System.out.println("Invalid Input. Please Enter a Valid Option");
                    break;
            }

        }

    }
}
