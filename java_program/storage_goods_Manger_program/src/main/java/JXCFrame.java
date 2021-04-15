import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicToolBarUI;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

class JXCFrame{
    private JDesktopPane desktopPane;
    private JFrame frame;
    private JLabel backLabel;
    private Preferences preferences;
    private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
    public JXCFrame(){
        frame = new JFrame("企业管理系统");
        frame.addComponentListener(new BasicToolBarUI.FrameListener());
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setBounds(100 , 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backLabel = new JLabel();
        backLabel.setVerticalAlignment(SwingConstants.TOP);
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateBackImage();
        desktopPane = new JDesktopPane();
        desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
        frame.getContentPane().add(desktopPane);
        JTabbedPane navigationPanel = createNavigationPanel();
        frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private JTabbedPane createNavigationPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        tabbedPane.setBackground(new Color(211,230,194));
        tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        JPanel baseManagePanel = new JPanel();
        baseManagePanel.setBackground(new Color(215,223,194));
        baseManagePanel.setLayout(new BoxLayout(baseManagePanel,BoxLayout.X_AXIS));
        baseManagePanel.add(createFrameButton("客户信息管理","customerManager"));
        baseManagePanel.add(createFrameButton("商品信息管理","goodsManager"));
        baseManagePanel.add(createFrameButton("供应商信息管理","supplierManager"));
        JPanel depotManegerPanel = new JPanel();
        depotManegerPanel.setBackground(new Color(215,223,194));
        depotManegerPanel.setLayout(new BoxLayout(depotManegerPanel,BoxLayout.X_AXIS));
        depotManegerPanel.add(createFrameButton("库存盘点","inventoryCheck"));
        depotManegerPanel.add(createFrameButton("价格调整", "priceAdjustment"));

        JPanel sellManagePanel = new JPanel();
        sellManagePanel.setBackground(new Color(215, 223,194));
        sellManagePanel.setLayout(new BoxLayout(sellManagePanel, BoxLayout.X_AXIS));
        sellManagePanel.add(createFrameButton("销售单","sell list"));
        sellManagePanel.add(createFrameButton("销售退货","returnedPurchase"));

        JPanel searchStatusPanel = new JPanel();
        searchStatusPanel.setBounds(0,0,600,41);
        searchStatusPanel.setName("searchStaticPanel");
        searchStatusPanel.setBackground(new Color(215,223,194));
        searchStatusPanel.setLayout(new BoxLayout(searchStatusPanel,BoxLayout.X_AXIS));
        searchStatusPanel.add(createFrameButton("客户信息查询","customerInfoSearch"));
        searchStatusPanel.add(createFrameButton("商品信息查询","goodsInfoSearch"));
        searchStatusPanel.add(createFrameButton("供应商信息查询","supplierInfoSearch"));
        searchStatusPanel.add(createFrameButton("销售信息查询","sellInfoSearch"));
        searchStatusPanel.add(createFrameButton("入库查询","customerInfoSearch"));
        searchStatusPanel.add(createFrameButton("入库退货查询","storageAndreturnSearch"));
        searchStatusPanel.add(createFrameButton("销售排行","sellRanking"));

        JPanel stockManagePanel = new JPanel();
        stockManagePanel.setBackground(new Color(215,223,194));
        stockManagePanel.setLayout(new BoxLayout(stockManagePanel,BoxLayout.X_AXIS));
        stockManagePanel.add(createFrameButton("进货单","storageOrder"));
        stockManagePanel.add(createFrameButton("进货退货","storageOrReturn"));
        JPanel sysManagePanel = new JPanel();
        sysManagePanel.setBackground(new Color(215,223,194));
        sysManagePanel.setLayout(new BoxLayout(sysManagePanel,BoxLayout.X_AXIS));
        sysManagePanel.add(createFrameButton("操作员管理","operatorManage"));
        sysManagePanel.add(createFrameButton("更改密码","changePwd"));
        sysManagePanel.add(createFrameButton("权限管理","authorityManagement"));
        tabbedPane.addTab("基础信息管理",null,baseManagePanel,"基础信息管理");
        tabbedPane.addTab("进货管理",null,stockManagePanel,"进货管理");
        tabbedPane.addTab("销售管理",null,sellManagePanel,"销售管理");
        tabbedPane.addTab("查询统计",null,searchStatusPanel,"查询统计");
        tabbedPane.addTab("库存管理",null,depotManegerPanel,"库存管理");
        tabbedPane.addTab("系统管理",null,baseManagePanel,"系统管理");
        return tabbedPane;
    }

    private Component createFrameButton(String chinese_info, String english_info) {
    }

    private void updateBackImage() {
        if(backLabel !=null) {
            int backw = frame.getWidth();
            int backh = frame.getHeight();
            backLabel.setSize(backw,backh);
            backLabel.setText("<html><body><image width=" + backw + "height=" + (backh-10)+"src = "
                        +JXCFrame.this.getClass().getResource("welcome.jpg")+"></img></body></html>");
        }
    }
    static {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
public static void main(String[] args){
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new Login();
        }
    });
}