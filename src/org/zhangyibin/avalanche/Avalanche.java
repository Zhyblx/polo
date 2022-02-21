package org.zhangyibin.avalanche;

import org.zhangyibin.maintenanceitem.EnterMaintenanceRecords;
import org.zhangyibin.maintenanceitem.MaintenanceInformationQuery;
import org.zhangyibin.service.PoloParameter;
import org.zhangyibin.service.ProductEnum;
import org.zhangyibin.service.Report;
import org.zhangyibin.util.ImageUtil;
import org.zhangyibin.util.StringConvertArray;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

import org.zhangyibin.service.QueryHistory;

/**
 * Avalanche (雪崩 软件)
 * <p>
 * 作用：保养查询工具UI-windows版
 * 变更：2021年12月11日已更换JDK8
 *
 * @author zhangyibin
 */
public class Avalanche {

    private JFrame frame;
    private JMenuItem checkMaintenance;
    private JMenuItem recordMaintenance;
    private JMenuItem aboutSoftware;
    private JPanel panelGroup;
    private JPanel queryPanel;
    private JPanel recordPanel;
    private JPanel aboutPanel;
    private JLabel logoJLabel;
    private JLabel recentMileageJLabel;
    private JTextField queryTextField;
    private DefaultTableModel defaultTableModel;
    private JLabel maintenanceTimeLabel;
    private JLabel maintenanceMileageLabel;
    private JTextField maintenanceTimeTextField;
    private JTextField maintenanceMileageTextField;
    private JLabel selectProductLabel;

    private int currentMileage = 0; // 里程参数
    private int recentMileageNumber = 0; //初始化查询最近一次的保养里程数
    private String recentTime = ""; //初始化查询最近一次的保养时间
    private JTable table;
    private QueryHistory queryHistory;

    /**
     * 启动应用程序。
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Avalanche window = new Avalanche();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建应用程序
     */
    public Avalanche() {
        /*
         * 1.程序初始化加载时，完成上一次保养里程数据查询。
         * 2.程序初始化加载时，完成查询上一次保养里程数据的查询记录。
         * 说明：汽油车的保养是按照机油的使用里程进行计算的
         */
        String product = ProductEnum.机油.getProductEnum();
        Map<String, Integer> recentMileageMap = Report.onMileage(product);
        Map<String, String> recentTimeMap = Report.onNearestdate(product);
        recentMileageNumber = recentMileageMap.get(product);
        recentTime = recentTimeMap.get(product);
        queryHistory = new QueryHistory();
        initialize();

    }

    /**
     * 初始化框架的内容。
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 800, 500);
        frame.setTitle("上汽大众保养查询系统");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        /*
         * 菜单面板
         * 1.checkMenuPanel-> 查询保养
         * 2.recordMenuPanel-> 记录保养
         * 3.aboutMenuPanel-> 关于软件
         *
         */
        JPanel checkMenuPanel = new JPanel();
        checkMenuPanel.setBackground(Color.WHITE);
        checkMenuPanel.setBounds(0, 0, 100, 50);
        frame.getContentPane().add(checkMenuPanel);

        JPanel recordMenuPanel = new JPanel();
        recordMenuPanel.setBackground(Color.WHITE);
        recordMenuPanel.setBounds(0, 60, 100, 50);
        frame.getContentPane().add(recordMenuPanel);

        JPanel aboutMenuPanel = new JPanel();
        aboutMenuPanel.setBackground(Color.WHITE);
        aboutMenuPanel.setBounds(0, 120, 100, 50);
        frame.getContentPane().add(aboutMenuPanel);


        /*
         * 查询保养事件
         */
        checkMaintenance = new JMenuItem("· 查询保养");
        checkMaintenance.setBackground(Color.WHITE);
        checkMaintenance.setForeground(SystemColor.textHighlight); // 蓝色字体
        checkMaintenance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMaintenance.setForeground(SystemColor.textHighlight);
                recordMaintenance.setForeground(SystemColor.activeCaptionText);
                aboutSoftware.setForeground(SystemColor.activeCaptionText);
                /*
                 * 查询面板显示
                 */
                queryPanel.setVisible(true);
                recordPanel.setVisible(false);
                aboutPanel.setVisible(false);
                /*
                 * 显示logo
                 */
                queryPanel.add(logoJLabel);

            }
        });
        checkMenuPanel.add(checkMaintenance);

        /*
         * 记录保养事件
         */
        recordMaintenance = new JMenuItem("· 记录保养");
        recordMaintenance.setBackground(Color.WHITE);
        recordMaintenance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMaintenance.setForeground(SystemColor.activeCaptionText);
                recordMaintenance.setForeground(SystemColor.textHighlight);
                aboutSoftware.setForeground(SystemColor.activeCaptionText);
                /*
                 * 记录面板显示
                 */
                queryPanel.setVisible(false);
                recordPanel.setVisible(true);
                aboutPanel.setVisible(false);
                /*
                 * 显示logo
                 */
                recordPanel.add(logoJLabel);

            }
        });
        recordMenuPanel.add(recordMaintenance);

        /*
         * 关于软件事件
         */
        aboutSoftware = new JMenuItem("· 关于软件");
        aboutSoftware.setBackground(Color.WHITE);
        aboutSoftware.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMaintenance.setForeground(SystemColor.activeCaptionText);
                recordMaintenance.setForeground(SystemColor.activeCaptionText);
                aboutSoftware.setForeground(SystemColor.textHighlight);
                /*
                 * 关于面板显示
                 */
                queryPanel.setVisible(false);
                recordPanel.setVisible(false);
                aboutPanel.setVisible(true);
                /*
                 * 显示logo
                 */
                aboutPanel.add(logoJLabel);

            }
        });
        aboutMenuPanel.add(aboutSoftware);

        /*
         * Logo
         */
        logoJLabel = new JLabel("上汽大众保养查询系统", JLabel.CENTER);
        logoJLabel.setFont(new Font("宋体", Font.PLAIN, 30));
        logoJLabel.setBounds(150, 25, 340, 60);

        /*
         * 面板集团:目的是把保养相关的功能面板集中起来
         */
        panelGroup = new JPanel();
        panelGroup.setBackground(Color.WHITE);
        panelGroup.setBounds(110, 0, 676, 463);
        frame.getContentPane().add(panelGroup);
        panelGroup.setLayout(null);

        /*
         * 查询面板
         */
        queryPanel = new JPanel();
        queryPanel.setBackground(Color.WHITE);
        queryPanel.setBounds(0, 0, 676, 463);
        panelGroup.add(queryPanel);
        queryPanel.setLayout(null);
        queryPanel.add(logoJLabel); // 默认显示logo

        /*
         * 输入框
         */
        queryTextField = new JTextField();
        queryTextField.setBounds(50, 120, 480, 35);
        queryPanel.add(queryTextField);
        queryTextField.setText("上次查询记录：" + queryHistory.getReadFile()); // 默认展示上次查询里程
        queryTextField.setColumns(10);

        /*
         * 按钮
         */
        JButton queryButton = new JButton("查询");
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    currentMileage = Integer.valueOf(queryTextField.getText());
                    if (currentMileage < recentMileageNumber) {
                        JOptionPane.showMessageDialog(null, "查询里程数不可小于上次一次保养里程数！", "系统提示！", JOptionPane.ERROR_MESSAGE);

                    } else if (currentMileage > PoloParameter.ultimateMileage) {
                        JOptionPane.showMessageDialog(null, "汽车里程数不可大于100万公里！", "系统提示！", JOptionPane.ERROR_MESSAGE);

                    } else {
                        setTableArray(StringConvertArray.getStringConvertArray(
                                MaintenanceInformationQuery.getMaintenanceInformationQuery(currentMileage)
                                        .onMaintenanceInformationQuery()));
                        defaultTableModel.setDataVector(getTableArray(), tableHeader);// 刷新表格数据
                    }
                } catch (Exception ei) {
                    JOptionPane.showMessageDialog(null, "请输入保养里程数！", "系统提示！", JOptionPane.ERROR_MESSAGE);

                }
                queryHistory.setWriteFile(String.valueOf(currentMileage));
                System.out.println(currentMileage);

            }
        });
        queryButton.setBackground(SystemColor.textHighlight);
        //queryButton.setForeground(SystemColor.text);
        queryButton.setBounds(555, 120, 93, 35);
        queryPanel.add(queryButton);

        /*
         * 表格信息
         */
        defaultTableModel = new DefaultTableModel(this.getTableArray(), this.tableHeader);
        table = new JTable(defaultTableModel);
        table.setForeground(SystemColor.textHighlight);
        table.setBackground(Color.WHITE);
        table.setBounds(50, 200, 598, 200);
        table.setEnabled(false); // 表格-单元格内容不可编辑

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBounds(50, 180, 598, 210);
        queryPanel.add(scrollPane);

        /*
         * 初始化最近一次保养记录
         */
        recentMileageJLabel = new JLabel();
        recentMileageJLabel.setFont(new Font("宋体", Font.PLAIN, 10));
        recentMileageJLabel.setText("上次保养时间/里程数：" + recentTime + "/" + String.valueOf(recentMileageNumber));
        recentMileageJLabel.setBounds(50, 420, 300, 15);
        queryPanel.add(recentMileageJLabel);

        /*
         * 记录面板
         */
        recordPanel = new JPanel();
        recordPanel.setBackground(Color.WHITE);
        recordPanel.setBounds(0, 0, 676, 463);
        panelGroup.add(recordPanel);
        recordPanel.setLayout(null);


        maintenanceTimeLabel = new JLabel("保养时间：");
        maintenanceTimeLabel.setBounds(50, 100, 108, 26);
        maintenanceTimeLabel.setBackground(Color.WHITE);
        recordPanel.add(maintenanceTimeLabel);

        maintenanceTimeTextField = new JTextField();
        maintenanceTimeTextField.setBounds(150, 100, 400, 30);
        maintenanceTimeTextField.setText(new PoloParameter().toDate()); // 保养时间系统默认读取系统当前时间
        maintenanceTimeTextField.setColumns(10);
        recordPanel.add(maintenanceTimeTextField);


        maintenanceMileageLabel = new JLabel("保养里程：");
        maintenanceMileageLabel.setBounds(50, 200, 108, 26);
        maintenanceMileageLabel.setBackground(Color.WHITE);
        recordPanel.add(maintenanceMileageLabel);

        maintenanceMileageTextField = new JTextField();
        maintenanceMileageTextField.setBounds(150, 200, 400, 30);
        maintenanceMileageTextField.setText(""); //保养里程默认为空
        maintenanceMileageTextField.setColumns(10);
        recordPanel.add(maintenanceMileageTextField);


        selectProductLabel = new JLabel("选择产品：");
        selectProductLabel.setBounds(50, 300, 108, 26);
        selectProductLabel.setBackground(Color.WHITE);
        recordPanel.add(selectProductLabel);

        StringBuffer strTires = new StringBuffer(); //轮胎
        StringBuffer strAirFilter = new StringBuffer(); //空气滤芯
        StringBuffer strAirConditioningFilter = new StringBuffer(); //空调滤芯
        StringBuffer strGearboxOil = new StringBuffer(); //变速箱油
        StringBuffer strAntifreeze = new StringBuffer(); //防冻液
        StringBuffer strSparkPlug = new StringBuffer(); //火花塞
        StringBuffer strGasolineFilter = new StringBuffer(); //汽油滤芯
        StringBuffer strBrakeFluid = new StringBuffer(); //刹车油
        StringBuffer strEngineOil = new StringBuffer(); //机油

        JCheckBox tiresJCheckBox = new JCheckBox("轮胎");
        tiresJCheckBox.setBackground(Color.WHITE);
        tiresJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tiresJCheckBox.isSelected()) {
                    strTires.append(ProductEnum.轮胎.getProductEnum());
                    //System.out.println("true");
                } else {
                    strTires.delete(0, strTires.length());
                }

            }
        });
        tiresJCheckBox.setBounds(150, 300, 110, 25);
        recordPanel.add(tiresJCheckBox);

        JCheckBox airFilterJCheckBox = new JCheckBox("空气滤芯");
        airFilterJCheckBox.setBackground(Color.WHITE);
        airFilterJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (airFilterJCheckBox.isSelected()) {
                    strAirFilter.append(ProductEnum.空气滤芯.getProductEnum());
                    //System.out.println("true");
                } else {
                    strAirFilter.delete(0, strAirFilter.length());
                    //System.out.println("false");
                }
            }
        });
        airFilterJCheckBox.setBounds(300, 300, 110, 25);
        recordPanel.add(airFilterJCheckBox);

        JCheckBox airConditioningFilterJCheckBox = new JCheckBox("空调滤芯");
        airConditioningFilterJCheckBox.setBackground(Color.WHITE);
        airConditioningFilterJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (airConditioningFilterJCheckBox.isSelected()) {
                    strAirConditioningFilter.append(ProductEnum.空调滤芯.getProductEnum());
                    //System.out.println("true");
                } else {
                    strAirConditioningFilter.delete(0, strAirConditioningFilter.length());
                    //System.out.println("false");
                }
            }
        });
        airConditioningFilterJCheckBox.setBounds(450, 300, 110, 25);
        recordPanel.add(airConditioningFilterJCheckBox);

        JCheckBox gearboxOilJCheckBox = new JCheckBox("变速箱油");
        gearboxOilJCheckBox.setBackground(Color.WHITE);
        gearboxOilJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gearboxOilJCheckBox.isSelected()) {
                    strGearboxOil.append(ProductEnum.变速箱油.getProductEnum());
                    //System.out.println("true");
                } else {
                    strGearboxOil.delete(0, strGearboxOil.length());
                    //System.out.println("false");
                }
            }
        });
        gearboxOilJCheckBox.setBounds(150, 330, 110, 25);
        recordPanel.add(gearboxOilJCheckBox);

        JCheckBox antifreezeJCheckBox = new JCheckBox("防冻液");
        antifreezeJCheckBox.setBackground(Color.WHITE);
        antifreezeJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (antifreezeJCheckBox.isSelected()) {
                    strAntifreeze.append(ProductEnum.防冻液.getProductEnum());
                    //System.out.println("true");
                } else {
                    strAntifreeze.delete(0, strAntifreeze.length());
                    //System.out.println("false");
                }
            }
        });
        antifreezeJCheckBox.setBounds(300, 330, 110, 25);
        recordPanel.add(antifreezeJCheckBox);

        JCheckBox sparkPlugJCheckBox = new JCheckBox("火花塞");
        sparkPlugJCheckBox.setBackground(Color.WHITE);
        sparkPlugJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sparkPlugJCheckBox.isSelected()) {
                    strSparkPlug.append(ProductEnum.火花塞.getProductEnum());
                    //System.out.println("true");
                } else {
                    strSparkPlug.delete(0, strSparkPlug.length());
                    //System.out.println("false");
                }
            }
        });
        sparkPlugJCheckBox.setBounds(450, 330, 110, 25);
        recordPanel.add(sparkPlugJCheckBox);

        JCheckBox gasolineFilterJCheckBox = new JCheckBox("汽油滤芯");
        gasolineFilterJCheckBox.setBackground(Color.WHITE);
        gasolineFilterJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gasolineFilterJCheckBox.isSelected()) {
                    strGasolineFilter.append(ProductEnum.汽油滤芯.getProductEnum());
                    //System.out.println("true");
                } else {
                    strGasolineFilter.delete(0, strGasolineFilter.length());
                    //System.out.println("false");
                }
            }
        });
        gasolineFilterJCheckBox.setBounds(150, 360, 110, 25);
        recordPanel.add(gasolineFilterJCheckBox);

        JCheckBox brakeFluidJCheckBox = new JCheckBox("刹车油");
        brakeFluidJCheckBox.setBackground(Color.WHITE);
        brakeFluidJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (brakeFluidJCheckBox.isSelected()) {
                    strBrakeFluid.append(ProductEnum.刹车油.getProductEnum());
                    //System.out.println("true");
                } else {
                    strBrakeFluid.delete(0, strBrakeFluid.length());
                    //System.out.println("false");
                }
            }
        });
        brakeFluidJCheckBox.setBounds(300, 360, 110, 25);
        recordPanel.add(brakeFluidJCheckBox);

        JCheckBox engineOilJCheckBox = new JCheckBox("机油");
        engineOilJCheckBox.setBackground(Color.WHITE);
        engineOilJCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (engineOilJCheckBox.isSelected()) {
                    strEngineOil.append(ProductEnum.机油.getProductEnum());
                    //System.out.println("true");
                } else {
                    strEngineOil.delete(0, strEngineOil.length());
                    //System.out.println("false");
                }
            }
        });
        engineOilJCheckBox.setBounds(450, 360, 110, 25);
        recordPanel.add(engineOilJCheckBox);

        JButton submitJButton = new JButton("提交");
        submitJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("提交事件");

                EnterMaintenanceRecords enterMaintenanceRecords = new EnterMaintenanceRecords();
//                System.out.println(enterMaintenanceRecords
//                      .onEnterMaintenanceRecords("", "机油", "2021-10-27", 106737));

                String dateTime = maintenanceTimeTextField.getText(); //获得日期时间
                String mileageData = maintenanceMileageTextField.getText(); //获得里程数据

                if (mileageData.equals("") || dateTime.equals("")) {
                    JOptionPane.showMessageDialog(null, "保养时间和保养里程为必填项！", "系统提示！", JOptionPane.ERROR_MESSAGE);

                } else {


                    if (strTires != null && strTires.length() > 0) {
                        //System.out.println(strTires.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strTires.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strAirFilter != null && strAirFilter.length() > 0) {
                        //System.out.println(strAirFilter.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strAirFilter.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strAirConditioningFilter != null && strAirConditioningFilter.length() > 0) {
                        //System.out.println(strAirConditioningFilter.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strAirConditioningFilter.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strGearboxOil != null && strGearboxOil.length() > 0) {
                        //System.out.println(strGearboxOil.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strGearboxOil.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strAntifreeze != null && strAntifreeze.length() > 0) {
                        //System.out.println(strAntifreeze.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strAntifreeze.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strSparkPlug != null && strSparkPlug.length() > 0) {
                        //System.out.println(strSparkPlug.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strSparkPlug.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strGasolineFilter != null && strGasolineFilter.length() > 0) {
                        //System.out.println(strGasolineFilter.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strGasolineFilter.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strBrakeFluid != null && strBrakeFluid.length() > 0) {
                        //System.out.println(strBrakeFluid.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strBrakeFluid.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    if (strEngineOil != null && strEngineOil.length() > 0) {
                        //System.out.println(strEngineOil.toString());
                        enterMaintenanceRecords
                                .onEnterMaintenanceRecords("", strEngineOil.toString(), dateTime, Integer.valueOf(mileageData));

                    }

                    JOptionPane.showMessageDialog(null, "提交成功", "系统提示！", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        submitJButton.setBackground(SystemColor.textHighlight);
        submitJButton.setBounds(200, 420, 93, 23);
        recordPanel.add(submitJButton);

        JButton cancelJButton = new JButton("重置");
        cancelJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                maintenanceTimeTextField.setText(new PoloParameter().toDate());
                maintenanceMileageTextField.setText("");

                tiresJCheckBox.setSelected(false);
                airFilterJCheckBox.setSelected(false);
                airConditioningFilterJCheckBox.setSelected(false);
                gearboxOilJCheckBox.setSelected(false);
                antifreezeJCheckBox.setSelected(false);
                sparkPlugJCheckBox.setSelected(false);
                gasolineFilterJCheckBox.setSelected(false);
                brakeFluidJCheckBox.setSelected(false);
                engineOilJCheckBox.setSelected(false);


            }
        });
        cancelJButton.setBounds(350, 420, 93, 23);
        recordPanel.add(cancelJButton);


        /*
         * 关于面板
         */
        aboutPanel = new JPanel();
        aboutPanel.setBackground(Color.WHITE);
        aboutPanel.setBounds(0, 0, 676, 463);
        panelGroup.add(aboutPanel);
        aboutPanel.setLayout(null);

        JLabel imageJLabel = new JLabel(); // 显示图片组件
        imageJLabel.setBounds(100, 80, 500, 300);
        imageJLabel.setIcon(ImageUtil.getImageIcon("polo.png"));
        aboutPanel.add(imageJLabel);

        JLabel authorLabel = new JLabel("张益斌自用工具(version:1.2)");
        authorLabel.setBounds(220, 400, 300, 15);
        aboutPanel.add(authorLabel);

        /*
         * 面板初始化，代码不可删除且必须放在initialize()方法的最后。
         */
        queryPanel.setVisible(true);
        recordPanel.setVisible(false);
        aboutPanel.setVisible(false);

    }

    /*
     * 1.表头 2.表格内容
     */

    private String[] tableHeader = {"产品信息", "是否保养", "超时天/里程数"};
    private String[][] tableArray = null;


    public void setTableArray(String[][] tableArray) {
        this.tableArray = tableArray;

    }

    public String[][] getTableArray() {
        // new String[][] { { "纽约", "50", "500.0" }, { "纽约", "10", "500.0" },
        // { "纽约", "10", "500.0" }, { "纽约", "40", "500.0" } };
        return this.tableArray;

    }
}
