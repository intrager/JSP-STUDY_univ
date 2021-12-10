package iducs.jsp.final201712046.repository;

import iducs.jsp.final201712046.model.Writer;
import iducs.jsp.final201712046.util.Pagination;

import java.util.List;

// crud : create read update delete
// http method : post, get, put, delete
public interface WriterDAO {
    int create(Writer member);
    Writer read(Writer member);
    List<Writer> readList();
    int update(Writer member);
    int delete(Writer member);

    int readTotalRows();
    List<Writer> readListPagination(Pagination pagination);
}
