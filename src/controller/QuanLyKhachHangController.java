/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.KhachHang;
import service.KhachHangService;
import service.KhachHangServiceImpl;
import shopsach.KhachHangJFrame;
import ublity.ClassTableModel;

/**
 *
 * @author Long
 */
public class QuanLyKhachHangController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    
    private KhachHangService khachHangService = null;
    
    private String[] listColumn = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Tình trạng"};
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyKhachHangController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.khachHangService = new KhachHangServiceImpl(); 
    }
    public void setDataToModel(){
        List<KhachHang> listItem = khachHangService.getList();
        DefaultTableModel model = new ClassTableModel().setTableKhachHang(listItem, listColumn);
        JTable table = new JTable(model);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        jtfSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                }
                else{
                    try{
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }catch(PatternSyntaxException ex){
                        if (text.length() < jtfSearch.getText().length()){
                            rowSorter.setRowFilter(null);
                    }else{
                            System.out.println("Invalid search pattern" + ex.getMessage());
                        }
                    }    
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("?i" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               
            }

            private Exception Exception(Error err) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        });
        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,14));
        table.getTableHeader().setPreferredSize(new Dimension(100,50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);
                    
                    KhachHang khachHang = new KhachHang();
                    khachHang.setMa_khach_hang((int) model.getValueAt(selectedRowIndex, 0));
                    khachHang.setTen_khach_hang((String) model.getValueAt(selectedRowIndex, 1));
                    khachHang.setDia_chi((String) model.getValueAt(selectedRowIndex, 2));
                    khachHang.setSo_dien_thoai((String) model.getValueAt(selectedRowIndex, 3));
                    khachHang.setTinh_trang((boolean) model.getValueAt(selectedRowIndex, 4));
                    
                    KhachHangJFrame frame = new KhachHangJFrame(khachHang);
                    frame.setTitle("Thông tin khách hàng");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                    
            }
            
        });
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300,400));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }
    public void setEvent(){
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                KhachHangJFrame jframe = new KhachHangJFrame(new KhachHang()); 
                jframe.setTitle("Thêm thông tin khách hàng");
                jframe.setLocationRelativeTo(null);
                jframe.setResizable(false);
                jframe.setVisible(true);
            }
            
            
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100,221,23));
            }
            
            
            
        });
    }
}
