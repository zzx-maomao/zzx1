package dao;

import tools.Jdbc;
import vo.Download;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DownloadDao {
    public List<Download> getDownLoadlist() throws Exception {
        List<Download>downloadList=new ArrayList<>();
        Jdbc jdbc = new Jdbc();
        Connection connection = jdbc.getConnection();
        String SQL = "SELECT * FROM t_downloadlist WHERE id like '%'";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Download download=new Download(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("path"),resultSet.getString("description"),fileSizeTransfer(resultSet.getLong("size")),resultSet.getInt("star"),resultSet.getString("image"),resultSet.getDate("time"));
            downloadList.add(download);

        }
        connection.close();
        return downloadList;
    }

    public Download getDownload(int id) throws Exception {
        Download download=null;
        Jdbc jdbc = new Jdbc();
        Connection connection = jdbc.getConnection();
        String SQL = "SELECT * FROM t_downloadlist WHERE id =?";
        PreparedStatement preparedStatement=connection.prepareStatement(SQL);
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            download=new Download(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("path"),resultSet.getString("description"),fileSizeTransfer(resultSet.getLong("size")),resultSet.getInt("star"),resultSet.getString("image"),resultSet.getDate("time"));
        }
        connection.close();
        return download;
    }


    public String fileSizeTransfer(long fileSize){
        String mFileSize;
        DecimalFormat decimalFormat=new DecimalFormat("######0.00");
        double size=(double)fileSize;
        if (size>1024*1024*1024){
            size=size/(1024*1024*1024);
            mFileSize=decimalFormat.format(size)+"G";
        }else if (size>1024*1024){
            size=size/(1024*1024);
            mFileSize=decimalFormat.format(size)+"MB";
        }
        else if (size>1024){
            size=size/1024;
            mFileSize=decimalFormat.format(size)+"KB";
        }else {
            mFileSize=decimalFormat.format(size)+"B";
        }
        return  mFileSize;
    }
}
