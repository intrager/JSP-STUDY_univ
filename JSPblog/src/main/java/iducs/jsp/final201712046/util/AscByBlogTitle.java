package iducs.jsp.final201712046.util;



import iducs.jsp.final201712046.model.Blog;

import java.util.Comparator;

public class AscByBlogTitle implements Comparator<Blog> {
    @Override
    public int compare(Blog o1, Blog o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
