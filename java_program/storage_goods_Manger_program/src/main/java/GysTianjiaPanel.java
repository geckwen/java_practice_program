class ResetActionListener implements ActionListener{
    public void actionPerformed(final ActionEvent e) {
        diZhiF.setText("");
        bianMaF.setText("");
        chuanZhenF.setText("");
        jianChengF.setText("");
        lianXiRenF.setText("");
        lianXiRenDianHuaF.setText("");
        EMailF.setText("");
        quanChengF.setText("");
        dianHuaF.setText("");
        yinHangF.setText("");
    }
}

class TjActionListener implements ActionListener{
    public void actionPerformed(final ActionEvent e){
        if(diZhiF.getText.equals("")|| quanChengF.getText().equals("")||chuanZhenF.getText.equal("")
                ||yinHang.getText.equal("")||bianMaF.getText.equal("")||diZhiF.getText.equal("")
                ||lianXiRenF.getText.equal("")||lianXiRenDianHua.getText.equal("")||EMailF.getText.equal("")
                ||dianHuaF.getText.equal("")) {
            JOptionPane.showMessageDialog(GysTianJiaPanel.this,"请填写全部信息")
            return ;
        }
        try {
            ResultSet haveUser = Dao.query("select * from tb_gysinfo where name = '" + quanCheng.getText().trim()+"'");
            if(haveUser.next()){
                JOptionPane.showMessageDialog(GysTianJiaPanel.this,"供应商信息添加失败",JOptionPane.INFORMATION_MESSAGE)
                        return
            }
            ResultSet set = Dao.query("select mx(id) from tb_gysinfo");
            String id =null;
            if(set !=null && set.next())
            {
                String sid = set.getString(1).trim();
                if(sid == null)
                    id = "gys1001";
                else
                {
                    String str = sid.substring(3);
                    id = "gys"+(Integer.parseInt(str)+1);
                }
            }
            TbGysinfo gysinfo = new TbGysinfo();
            gysinfo.setId(id);
            gysinfo.setId(diZhiF.getText().trim());
            gysinfo.setId(bianMaF.getText().trim());
            gysinfo.setId(chuanZhenF.getText().trim());
            gysinfo.setId(yinHangF.getText().trim());
            gysinfo.setId(jianChengF.getText().trim());
            gysinfo.setId(quanChengF.getText().trim());
            gysinfo.setId(lianXiRenF.getText().trim());
            gysinfo.setId(EMailF.getText().trim());
            gysinfo.setId(dianHuaF.getText().trim());
            Dao.add(gysinfo);
            JOptionPane.showMessageDialog(GysTianJiaPanel.this,"已添加","客户添加信息",JOptionPane.INFORMATION_MESSAGE)；
            resetButton.doClick();

        }catch (Exception e1){
            e1.prinStackTrace();
        }
    }
}