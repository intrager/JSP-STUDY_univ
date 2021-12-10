package iducs.jsp.final201712046.util;


import iducs.jsp.final201712046.model.Blog;

import java.util.Comparator;

public class AscByBlogEmail implements Comparator<Blog> {
    @Override
    public int compare(Blog o1, Blog o2) {
        return o1.getEmail().compareTo(o2.getEmail());
    }
}
