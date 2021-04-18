public class Dao{

    protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
    protected static String dbUrl = " jdbc:mysql://localhost:3306/db_JXC?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
    protected static String dbUser = "admin";
    protected static String dbPwd = "qaz0192";
    protected static String second = null;
    protected static Connection conn = null;
    static {
        try {
            if(conn=null){
                Class.forName(dbClassName).newInstance();
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean addGys(TbGysinfo gysInfo)
    {
        if(gysInfo == null)
            return false;
        return insert("insert tb_gysInfo values (" + gysInfo.getId()+","
                + gysInfo.getName()+","+ gysInfo.getJc()+","
                + gysInfo.getAddress()+","+ gysInfo.getBianma()+","
                + gysInfo.getTel()+"," + gysInfo.getFax()+","
                + gysInfo.getLian()+","+ gysInfo.getLtel()+","
                + gysInfo.getMail()+ ","+ gysInfo.getYh()+")");
        )
    }
}