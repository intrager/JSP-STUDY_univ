package iducs.jsp.final201712046.repository;

import iducs.jsp.final201712046.model.Writer;
import iducs.jsp.final201712046.util.Pagination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriterDAOImpl extends DAOImplOracle implements WriterDAO {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public WriterDAOImpl() { conn = getConnection(); }

    @Override
    public int create(Writer Writer) {
        String query = "insert into writer201712046 values(seq_writer201712046.nextval, ?, ?, ?, ?, ?)";
        int rows = 0;   // 질의 처리 결과 영향 받은 행의 개수
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Writer.getEmail());
            pstmt.setString(2, Writer.getPw());
            pstmt.setString(3, Writer.getName());
            pstmt.setString(4, Writer.getPhone());
            pstmt.setString(5, Writer.getAddress());
            rows = pstmt.executeUpdate();   // 영향 받은 행의 숫자. 1 이상이면 정상, 0 이하면 오류
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public Writer read(Writer writer) {
        Writer retWriter = null;
        String sql = "select * from writer201712046 where email=? and pw=?";  // 유일키(unique key) 조회
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, writer.getEmail());
            pstmt.setString(2, writer.getPw());
            rs = pstmt.executeQuery();
            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다른 요소로 접근
                retWriter = new Writer();
                retWriter.setId(rs.getLong("id"));
                retWriter.setEmail(rs.getString("email"));
                retWriter.setPw(rs.getString("pw"));
                retWriter.setName(rs.getString("name"));
                retWriter.setPhone(rs.getString("phone"));
                retWriter.setAddress(rs.getString("address"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retWriter;
    }

    public Writer readByEmail(Writer writer) {
        Writer retWriter = null;
        // email 조건에서 id 조건으로 조회하도록 변경
        String sql = "select * from writer201712046 where email=?";  // 유일키(unique key) 조회
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, writer.getEmail());
            rs = pstmt.executeQuery();
            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다른 요소로 접근
                retWriter = new Writer();
                retWriter.setId(rs.getLong("id"));
                retWriter.setEmail(rs.getString("email"));
                retWriter.setPw(rs.getString("pw"));
                retWriter.setName(rs.getString("name"));
                retWriter.setPhone(rs.getString("phone"));
                retWriter.setAddress(rs.getString("address"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retWriter;
    }

    @Override
    public List<Writer> readList() {
        ArrayList<Writer> writerList = null;
        String sql = "select * from writer201712046 where email not in('csb1root@induk.ac.kr')";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만
            // executeUpdate()는 insert, update, delete에 사용 가능
            if((rs = stmt.executeQuery(sql)) != null) { // 질의 결과가 ResultSet 형태로 반환
                writerList = new ArrayList<Writer>();
                while(rs.next()) {
                    Writer writer = new Writer();
                    writer = setWriterRs(rs);
                    writerList.add(writer);
                }
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return writerList;
    }

    private Writer setWriterRs(ResultSet rs) throws SQLException {
        // rs : record 집합, rs.getString(1) : 현재 레코드의 첫 번째 필드 값
        Writer retWriter = new Writer();
        retWriter.setId(rs.getLong("id")); // rs.getLong("id")도 가능함
        retWriter.setEmail(rs.getString("email"));
        retWriter.setPw(rs.getString("pw"));
        retWriter.setName(rs.getString("name"));
        retWriter.setPhone(rs.getString("phone"));
        retWriter.setAddress(rs.getString("address"));
        return retWriter;
    }

    @Override
    public int update(Writer writer) {  // 회원 정보 수정, update문
        int ret = 0;
        conn = this.getConnection();
        String sql = "update writer201712046 set pw=?, name=?, phone=?, address=? where email=? and id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, writer.getPw());
            pstmt.setString(2, writer.getName());
            pstmt.setString(3, writer.getPhone());
            pstmt.setString(4, writer.getAddress());
            pstmt.setString(5, writer.getEmail());
            pstmt.setLong(6, writer.getId());
            ret = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResources(conn, stmt, pstmt, rs);
        }
        return ret;
    }

    @Override
    public int delete(Writer writer) {  // 회원 탈퇴(정보 삭제), delete문
        int ret = 0;
        conn = this.getConnection();
        String sql = "delete from writer201712046 where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, writer.getId());
            ret = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResources(conn, stmt, pstmt, rs);
        }
        return ret;
    }

    @Override
    public int readTotalRows() {
        int rows = 0;
        String sql = "select count(*) as totalRows from writer201712046";
        try {
            stmt = conn.createStatement();
        /*
         execute(sql)는 모든 질의에 사용가능.
         executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
         */
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                rows = rs.getInt("totalRows");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Writer> readListPagination(Pagination pagination) {
        ArrayList<Writer> writerList = null;
        String sql = "select * from (" +
                "select A.*, rownum as rnum from (" +
                "select * from writer201712046 order by id desc) A) where (rnum >= ? and rnum <= ?) and email not in('csb1root@induk.ac.kr')";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pagination.getFirstRow());
            pstmt.setInt(2, pagination.getEndRow());
            rs = pstmt.executeQuery();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            writerList = new ArrayList<Writer>();
            while(rs.next()) {
                Writer writer = new Writer();
                writer.setId(rs.getLong("id"));   // id 값도 dto에 저장
                writer.setName(rs.getString("name"));
                writer.setEmail(rs.getString("email"));
                writer.setPhone(rs.getString("phone"));
                writer.setAddress(rs.getString("address"));
                writerList.add(writer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writerList;
    }
}
