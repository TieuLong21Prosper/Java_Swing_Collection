/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ublity;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;

/**
 *
 * @author Long
 */
public class ClassTableModel {
    public DefaultTableModel setTableKhachHang(List<KhachHang> listItem, String[] listColumn){
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        
    };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows>0){
            for (int i = 0; i < rows; i++) {
                KhachHang khachHang = listItem.get(i);
                obj = new Object[columns];
                obj[0] = khachHang.getMa_khach_hang();
                obj[1] = khachHang.getTen_khach_hang();
                obj[2] = khachHang.getDia_chi();
                obj[3] = khachHang.getSo_dien_thoai();
                obj[4] = khachHang.isTinh_trang();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
