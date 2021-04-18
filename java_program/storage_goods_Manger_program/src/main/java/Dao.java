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
    public static TbGysinfo getGysInfo(Item item)
    {
        String where = "name='" + item.getName() +"'";
        if(item.getId()!=null)
            where = "id = '" +item.getId()+"'";
        TbGysinfo info =  new TbGysinfo();
        ResultSet set = findForResultSet("select * from tb_gysinfo where"+where);
        try{
            if(set.next()){
                info.setId(set.getString("id").trim());
                info.setAddress(set.getString("address").trim());
                info.setBianma(set.getString("bianma").trim());
                info.setFax(set.getString("fax").trim());
                info.setJc(set.getString("jc").trim());
                info.setLian(set.getString("lian").trim());
                info.setLtel(set.getString("Itel").trim());
                info.setMail(set.getString("mail").trim());
                info.setName(set.getString("name").trim());
                info.setTel(set.getString("tel").trim());
                info.setYh(set.getString("yh").trim());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int updateGys(TbGysinfo gysinfo){
        return update("update tb_gysinfo set jc = '" + gysinfo.getJc()
                    +"',address ='"+gysinfo.getAddress() +"'bianma = "
                    +gysinfo.getBianma()+"',tek=" +gysinfo.getTel()
                    +"',fax = "+ gysinfo.getFax() +"',lian="+gysinfo.getLian()
                    +"',ltel=" + gysinfo.getLtel()+"',mail="
                    +gysinfo.getMail()+"',yh="+gysinfo.getYh()
                    +"'where id='" +gysinfo.getId()+"'");
        )
    }
    public static boolean insertRukuInfo(TbRukuMain ruMain){
        try {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            insert("insert into tb_ruku_main value('"+ruMain.getRkId()
                    +","+ruMain.getPzs()+"',"+ ruMain.getJe()+",'"
                    +ruMain.getYsjl()+"','"+ruMain.getGysname()+"','"
                    +ruMain.getRkdate()+"','"+ruMain.getCzy()+"','"
                    +ruMain.getJsr()+"','"+ruMain.getJsfs()+"')'"
            );
            Set<TbRukuDetail> rkDetails = ruMain.getTabRukuDetails();
            for(iterator<TbRukuDetail> iter = rkDetails.iterator();iter.hasNext();)
            {
                TbRukuDetail details = iter.next();
                insert("insert into tb_ruku_detail_values('"+ruMain.getRkId()
                    +"','" +details.getTabSpinfo+"',"+details.getDj()+","+details.getSl()+")");
                Item item = new Item();
                item.setId(details.getTabSpinfo());
                TbSpinfo spInfo = getSpInfo(item);
                if(spInfo.getId()!=null && !spInfo.getId().isEmpty()){
                    TbKucun kucun = getKucun(item);
                    if(kucun.getId() == null ||kucun.getId().isEmpty()) {
                    insert("insert into tb_kucun values ('"+ spInfo.getId()
                        +"','"+spInfo.getSpname()+"','"+spInfo.getJc()+"','"+spInfo.getCd()
                            +"','"+spInfo.getGg()+"','"+spInfo.getBz()+"','"+spInfo.getDw()
                            +"',"+details.getDj()+","+details.getSl()+")");
                    }else{
                        int sl=kucun.getKcsl()+details.getSl();
                        update("update tb_kucun set kcs!=" + sl + ",dj=" + details.getDj()+"where id = "+kucun.getId()+"'");
                    }
                }
            }
        conn.commit();
        conn.setAutoCommit(autoCommit);
        }catch (SQLException e){
            try {
                conn.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return true;
    }

    public static TbKucun getKucun(Item item){
        String where = "spname = '"+ item.getName()+"'";
        if(item.getId()!=null)
            where  = "id ='"+item.getId()+"'";
        ResultSet rs = findForResultSet("select * from tb_kucun where "+where);
        TbKucun kucun = new TbKucun();
        try {
            if(rs.next()){
                kucun.setId(rs.getString("id"));
                kucun.setSpname(rs.getString("spname"));
                kucun.setJc(rs.getString("jc"));
                kucun.setBz(rs.getString("bz"));
                kucun.setCd(rs.getString("cd"));
                kucun.setDj(rs.getString("dj"));
                kucun.setDw(rs.getString("dw"));
                kucun.setGg(rs.getString("gg"));
                kucun.setKcsl(rs.getString("kcsl"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return kucun;
    }
}