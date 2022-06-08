package example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;
import pojo.Brand;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 品牌数据的增删改查
 */
public class BrandTest {

    @Test
    public void testDeleteById() throws Exception {

        //模拟接收页面提交的参数

        int id = 4;


        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        Connection con = dataSource.getConnection();

        String sql = "delete from tb_brand where id = ?";


        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setInt(1,id);

        int count = pstmt.executeUpdate();

        System.out.println(count > 0);


        pstmt.close();
        con.close();

    }
    @Test
    public void testUpdate() throws Exception {

        //模拟接收页面提交的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1000;
        String description = "绕地球三圈";
        int status = 1;
        int id = 4;


        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        Connection con = dataSource.getConnection();

        String sql = "update tb_brand set brand_name = ?, company_name = ?, ordered = ?, description = ?, status = ? where id = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1,brandName);
        pstmt.setString(2,companyName);
        pstmt.setInt(3,ordered);
        pstmt.setString(4,description);
        pstmt.setInt(5,status);
        pstmt.setInt(6,id);

        int count = pstmt.executeUpdate();

        System.out.println(count > 0);


        pstmt.close();
        con.close();

    }

    @Test
    public void testAdd() throws Exception {

        //模拟接收页面提交的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "绕地球一圈";
        int status = 1;


        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        Connection con = dataSource.getConnection();

        String sql = "insert into tb_brand(brand_name, company_name, ordered, description, status) values(?,?,?,?,?)";

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1,brandName);
        pstmt.setString(2,companyName);
        pstmt.setInt(3,ordered);
        pstmt.setString(4,description);
        pstmt.setInt(5,status);

        int count = pstmt.executeUpdate();

        System.out.println(count > 0);


        pstmt.close();
        con.close();

    }


    @Test
    public void testSelectAll() throws Exception {

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        Connection con = dataSource.getConnection();

        String sql = "select * from tb_brand";

        PreparedStatement pstmt = con.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        Brand brand = null;
        List<Brand> brands = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");

            //封装Brand对象
            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            brands.add(brand);
        }

        System.out.println(brands);

        rs.close();
        pstmt.close();
        con.close();

    }
}
