package com.tianl.dianying.common.page;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */
@JsonWriteNullProperties(value = false)
public class PagerHelper<E> {
    private String baseUrl;// 请求路径
    private String content;// 分页内容
    private Pager<E> pager;
    private Map<String, String> params = new HashMap<String, String>();// 分页参数
    private int size = 5; // 连续展现的页数容量

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getContent() {
        content = render();
        return content;
    }

    public Pager<E> getPager() {
        return pager;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public int getSize() {
        return size;
    }

    public void setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setPager(final Pager<E> pager) {
        this.pager = pager;
    }

    public void setParams(final Map<String, String> params) {
        this.params = params;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    private String pieceString(final String paraStr, final int size,
                               String content, final int startPager) {
        for (int i = 0; i < size; i++) {
            if (startPager + i == pager.getPageNumber()) {
                content += "<a href=\"" + baseUrl + "?" + paraStr
                        + "pageNumber=" + (startPager + i) + "&pageSize="
                        + pager.getPageSize()
                        + "\" class=\"btn disabled large primary-bg\">"
                        + (startPager + i) + "</a>";
            } else {
                content += "<a href=\"" + baseUrl + "?" + paraStr
                        + "pageNumber=" + (startPager + i) + "&pageSize="
                        + pager.getPageSize()
                        + "\" class=\"btn large bg-gray\">" + (startPager + i)
                        + "</a>";
            }
        }
        return content;
    }

    private String render() {
        String paraStr = "";
        final Iterator<Map.Entry<String, String>> iterator = params.entrySet()
                .iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, String> entry = iterator.next();
            final String key = entry.getKey();
            final String value = entry.getValue();
            if (!paraStr.equals("")) {
                paraStr += "&" + key + "=" + value;
            } else {
                paraStr += key + "=" + value;
            }
        }

        if (!paraStr.equals("")) {
            paraStr += "&";
        }

        final String pre = "<a href=\"" + baseUrl + "?" + paraStr
                + "pageNumber=" + (pager.getPageNumber() - size) + "&pageSize="
                + pager.getPageSize() + "\" class=\"btn large bg-white\">"
                + "<i class=\"glyph-icon icon-chevron-left\"></i>" + "</a>";
        final String next = "<a href=\"" + baseUrl + "?" + paraStr
                + "pageNumber=" + (pager.getPageNumber() + size) + "&pageSize="
                + pager.getPageSize() + "\" class=\"btn large bg-white\">"
                + "<i class=\"glyph-icon icon-chevron-right\"></i>" + "</a>";
        final String head = "<a href=\"" + baseUrl + "?" + paraStr
                + "pageNumber=1" + "&pageSize=" + pager.getPageSize()
                + "\" class=\"btn large bg-gray\">1</a>";
        final String end = "<a href=\"" + baseUrl + "?" + paraStr
                + "pageNumber=" + pager.getPageCount() + "&pageSize="
                + pager.getPageSize() + "\" class=\"btn large bg-gray\">"
                + pager.getPageCount() + "</a>";
        final String leaveout = "<a href=\"javascript:;\" class=\"btn large bg-gray\">...</a>";
        String content = "";
        String str = "";

        int startPager = (pager.getPageNumber() - (size / 2) < 1 ? 1 : pager
                .getPageNumber() - (size / 2));

        /**
         * 计算连续显示的页面容量在 前，中 ，后 的区间
         */
        if (pager.getPageCount() <= size) {
            for (int i = 0; i < pager.getPageCount(); i++) {
                if (pager.getPageNumber() == (1 + i)) {
                    content += "<a href=\"" + baseUrl + "?" + paraStr
                            + "pageNumber=" + (1 + i) + "&pageSize="
                            + pager.getPageSize()
                            + "\" class=\"btn disabled large primary-bg\">"
                            + (1 + i) + "</a>";
                } else {
                    content += "<a href=\"" + baseUrl + "?" + paraStr
                            + "pageNumber=" + (1 + i) + "&pageSize="
                            + pager.getPageSize()
                            + "\" class=\"btn large bg-gray\">" + (1 + i)
                            + "</a>";
                }
            }
            str = content;
        } else {
            if (startPager < size / 2) {
                content = pieceString(paraStr, size, content, startPager);

                if (startPager == 1) {
                    str = content + leaveout + end;
                } else {
                    str = head + leaveout + content + leaveout + end;
                }

                if (pager.getPageNumber() + size < pager.getPageCount()) {
                    str += next;
                }
            } else if (startPager > (pager.getPageCount() - size)) {
                startPager = pager.getPageCount() - size + 1;
                content = pieceString(paraStr, size, content, startPager);

                if (pager.getPageNumber() - size >= 1) {
                    str = pre;
                }
                str += head + leaveout + content;

            } else {
                content = pieceString(paraStr, size, content, startPager);
                if (pager.getPageNumber() + size > pager.getPageCount()) {
                    str = pre + head + leaveout + content + leaveout + end;
                } else if (pager.getPageNumber() - size < 1) {
                    str = head + leaveout + content + leaveout + end + next;
                } else {
                    str = pre + head + leaveout + content + leaveout + end
                            + next;
                }
            }

        }

        final String input = "&nbsp&nbsp<input id=\"pageNumber\" style=\"width:30px\" data-last-page=\""
                + pager.getPageCount()
                + "\"  placeholder=\""
                + pager.getPageNumber()
                + "\" type=\"text\">"
                + "<input type=\"button\" value=\"确定\" style=\"width: 40px\" onclick=\"if($('#pageNumber').val()<1 || $('#pageNumber').val()>$('#pageNumber').data('lastPage')){alert('超出界限！');return;}location.assign('"
                + baseUrl
                + "?"
                + paraStr
                + "pageNumber='+$('#pageNumber').val()+'&pageSize="
                + pager.getPageSize() + "')\"/>";

        str += input;

        String size_select = "每页显示行数<input id=\"pageSize\" name=\"pager.pageSize\" value=\""+pager.getPageSize()+"\" style=\"width:30px;\" onchange=\"javascript:window.location='"
                + baseUrl
                + "?"
                + paraStr
                + "pageNumber=1&pageSize='+this.value\" />&nbsp&nbsp";
        String totalCount="共"+pager.getTotalCount()+"行,";
        return totalCount+size_select+str;
    }
}

