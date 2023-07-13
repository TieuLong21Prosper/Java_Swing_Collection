/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.KhachHangDao;
import dao.KhachHangDaoImpl;
import java.util.List;
import model.KhachHang;

/**
 *
 * @author Long
 */
public class KhachHangServiceImpl implements KhachHangService{
    private KhachHangDao khachHangDao = null;
    
    public KhachHangServiceImpl(){
        khachHangDao = new KhachHangDaoImpl();
    }
    @Override
    public List<KhachHang> getList() {
        return khachHangDao.getList();
    }
    
}
