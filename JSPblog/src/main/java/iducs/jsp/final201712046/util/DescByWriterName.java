package iducs.jsp.final201712046.util;

import iducs.jsp.final201712046.model.Writer;

import java.util.Comparator;

public class DescByWriterName implements Comparator<Writer> {

    @Override
    public int compare(Writer o1, Writer o2) {
        // o2 <= o1 : 음수 / o2 > o1 : 1 이상 내림차순
        return o2.getName().compareTo(o1.getName());
        // return o1.getContent().compareTo(o1.getTitle()); // 오름차순

    }
}
