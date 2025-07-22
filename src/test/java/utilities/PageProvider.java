package utilities;

import com.microsoft.playwright.Page;

public class PageProvider {
    private static final ThreadLocal<Page> threadLocal = new ThreadLocal<>();

    private void set(Page page) {
        threadLocal.set(page);
    }

    private Page get() {
        return threadLocal.get();
    }

    public static void setPage(Page page) {
        new PageProvider().set(page);
    }

    public static Page getPage() {
        return new PageProvider().get();
    }
}
