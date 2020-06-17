package com.ofek.rickandmortyexcercise.domain.objects;

/**
 * A wrapper class which represents a pagination result
 * created in order to easily keep the result synchronised with the page on the presentation side
 * @param <T> the result object type
 */
public class PagingResult<T> {
    private T result;
    private int page;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
